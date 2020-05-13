package io.github.lh83mail.thallo.gateway.admin.service;

import io.github.lh83mail.thallo.gateway.admin.model.GatewayRoute;
import io.github.lh83mail.thallo.gateway.admin.model.GatewayRouteQueryParam;

import java.util.List;

/**
 * Create At  2020/1/6 17:32
 *
 * @author: Lh
 * @version: 1.0.0
 */
public interface GatewayRouteService {
    /**
     * 获取网关路由
     *
     * @param id
     * @return
     */
    GatewayRoute get(String id);

    /**
     * 新增网关路由
     *
     * @param gatewayRoute
     * @return
     */
    GatewayRoute add(GatewayRoute gatewayRoute);

    /**
     * 查询网关路由
     *
     * @return
     */
    List<GatewayRoute> query(GatewayRouteQueryParam gatewayRouteQueryParam);

    /**
     * 更新网关路由信息
     *
     * @param gatewayRoute
     */
    GatewayRoute update(GatewayRoute gatewayRoute);

    /**
     * 根据id删除网关路由
     *
     * @param id
     */
    boolean delete(String id);

    /**
     * 重新加载网关路由配置到redis
     *
     * @return 成功返回true
     */
    boolean overload();
}
