package io.github.lh83mail.thallo.gateway.admin.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lh83mail.thallo.gateway.admin.events.EventSender;
import io.github.lh83mail.thallo.gateway.admin.repository.GatewayRouteEntityRepository;
import io.github.lh83mail.thallo.gateway.admin.service.GatewayRouteService;
import lombok.extern.slf4j.Slf4j;
import io.github.lh83mail.thallo.gateway.admin.config.BusAdminConfig;
import io.github.lh83mail.thallo.gateway.admin.entity.GatewayRouteEntity;
import io.github.lh83mail.thallo.gateway.admin.model.GatewayRoute;
import io.github.lh83mail.thallo.gateway.admin.model.GatewayRouteQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create At  2020/1/7 11:29
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Service
@Slf4j
public class GatewayRouteServiceImpl implements GatewayRouteService {
    private static final String GATEWAY_ROUTES = "gateway_routes::";

    @CreateCache(name = GATEWAY_ROUTES, cacheType = CacheType.REMOTE)
    private Cache<String, RouteDefinition> gatewayRouteCache;

    @Autowired
    private EventSender eventSender;

    @Autowired
    private GatewayRouteEntityRepository routeEntityRepository;


    @Override
    public GatewayRoute add(GatewayRoute gatewayRoute) {
        GatewayRouteEntity entity = new GatewayRouteEntity();
        entity.apply(gatewayRoute);
        entity = this.routeEntityRepository.save(entity);
        // 转化为gateway需要的类型，缓存到redis, 并事件通知各网关应用
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(entity);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        eventSender.send(BusAdminConfig.ROUTING_KEY, routeDefinition);
        return entity.to(GatewayRoute.class);
    }

    @Override
    public boolean delete(String id) {
        GatewayRouteEntity entity = this.routeEntityRepository.findById(id)
                .orElse(null);
        if (entity == null) {
            return true;
        }
        // 删除redis缓存, 并事件通知各网关应用
        gatewayRouteCache.remove(entity.getRouteId());
        eventSender.send(BusAdminConfig.ROUTING_KEY, gatewayRouteToRouteDefinition(entity));
        this.routeEntityRepository.delete(entity);

        return true;
    }

    @Override
    public GatewayRoute update(GatewayRoute gatewayRoute) {
        GatewayRouteEntity entity = this.routeEntityRepository.findById(gatewayRoute.getId())
                .orElse(null);
        if (entity == null) {
            return null;
        }

        entity.setUri(gatewayRoute.getUri());
        entity.setRouteId(gatewayRoute.getRouteId());
        entity.setPredicates(gatewayRoute.getPredicates());
        entity.setFilters(gatewayRoute.getFilters());
        entity.setDescription (gatewayRoute.getDescription());
        entity.setOrders(gatewayRoute.getOrders());
        entity.setStatus(gatewayRoute.getStatus());
        entity.setDescription(gatewayRoute.getDescription());
        this.routeEntityRepository.save(entity);

        // 转化为gateway需要的类型，缓存到redis, 并事件通知各网关应用
        RouteDefinition routeDefinition = gatewayRouteToRouteDefinition(entity);
        gatewayRouteCache.put(gatewayRoute.getRouteId(), routeDefinition);
        eventSender.send(BusAdminConfig.ROUTING_KEY, routeDefinition);

        return entity.to(GatewayRoute.class);
    }

    /**
     * 将数据库中json对象转换为网关需要的RouteDefinition对象
     *
     * @param gatewayRoute
     * @return RouteDefinition
     */
    private RouteDefinition gatewayRouteToRouteDefinition(GatewayRouteEntity gatewayRoute) {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {
            }));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            }));
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
        return routeDefinition;
    }

    @Override
    public GatewayRoute get(String id) {
        return this.routeEntityRepository.findById(id)
                .map(e -> e.to(GatewayRoute.class))
                .orElse(null);
    }

    @Override
    public List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam) {
        List<GatewayRouteEntity> entities = this.routeEntityRepository.findAll(gatewayRouteQueryParam);
        return entities.stream()
                .map(e -> e.to(GatewayRoute.class))
                .collect(Collectors.toList());
    }

    @Override
    @PostConstruct
    public boolean overload() {
        List<GatewayRouteEntity> entities = this.routeEntityRepository.findAll();
        entities.forEach(entity ->
                gatewayRouteCache.put(entity.getRouteId(), gatewayRouteToRouteDefinition(entity))
        );
        log.info("全局初使化网关路由成功!");

        return true;
    }
}
