package org.halo.thallo.gateway.events;

import lombok.extern.slf4j.Slf4j;
import org.halo.thallo.gateway.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusReceiver {
    public static final int ACTION_SAVE = 1;
    public static final int ACTION_DELETE = 2;

    @Autowired
    private RouteService routeService;

    public void handleMessage(RouteDefinition routeDefinition) {
        log.info("Received Message:<{}, action: {}>", routeDefinition);
        int action = 1;
        switch (action) {
            case ACTION_SAVE:
                routeService.save(routeDefinition);
                break;
            case ACTION_DELETE:
                routeService.delete(routeDefinition.getId());
                break;
        }
    }
}