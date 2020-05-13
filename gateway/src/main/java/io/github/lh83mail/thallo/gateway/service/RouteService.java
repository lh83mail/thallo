package io.github.lh83mail.thallo.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * Create At  2020/1/6 15:53
 *
 * @author: Lh
 * @version: 1.0.0
 */
public interface RouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    void save(RouteDefinition routeDefinition);

    void delete(String id);
}
