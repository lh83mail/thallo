package io.github.lh83mail.thallo.gateway.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import io.github.lh83mail.thallo.gateway.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.synchronizedMap;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Create At  2020/1/6 15:56
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Component
@Slf4j
public class RouteServiceImpl implements RouteService {
    private final static String ROUTE_DEF_PREFIX = "gateway_routes::";

    private Map<String, RouteDefinition> routeDefinitionMap = synchronizedMap(new HashMap<>());

    @CreateCache(name = ROUTE_DEF_PREFIX, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    private void loadRouteDefinition() {
        log.info("loadRouteDefinition, 从redis加载路由");
        Set<String> gatewayKeys = redisTemplate.keys(ROUTE_DEF_PREFIX + "*");
        if (isEmpty(gatewayKeys)) {
            return;
        }
        log.info("预计初使化路由, gatewayKeys：{}", gatewayKeys);

        Set<String> gatewayKeyIds = gatewayKeys.stream().map(key -> key.replace(ROUTE_DEF_PREFIX, StringUtils.EMPTY))
                .collect(Collectors.toSet());

        Map<String, RouteDefinition> allRoutes = gatewayRouteCache.getAll(gatewayKeyIds);
        log.info("gatewayKeys：{}", allRoutes);

        // 以下代码原因是，jetcache将RouteDefinition返序列化后，uri发生变化，未初使化，导致路由异常，以下代码是重新初使化uri
        allRoutes.values().forEach(routeDefinition -> {
            try {
                routeDefinition.setUri(new URI(routeDefinition.getUri().toASCIIString()));
            } catch (URISyntaxException e) {
                log.error("网关加载RouteDefinition异常：", e);
            }
        });

        routeDefinitionMap.putAll(allRoutes);
        log.info("共初使化路由信息：{}", routeDefinitionMap.size());
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMap.values();
    }

    @Override
    public void save(RouteDefinition routeDefinition) {
        routeDefinitionMap.put(routeDefinition.getId(), routeDefinition);
        log.info("新增路由1条：{},目前路由共{}条", routeDefinition, routeDefinitionMap.size());
    }

    @Override
    public void delete(String id) {
        routeDefinitionMap.remove(id);
        log.info("删除路由1条：{},目前路由共{}条", id, routeDefinitionMap.size());
    }

}
