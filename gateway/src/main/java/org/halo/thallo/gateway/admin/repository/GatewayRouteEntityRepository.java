package org.halo.thallo.gateway.admin.repository;

import org.halo.thallo.gateway.admin.entity.GatewayRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Create At  2020/1/7 11:51
 *
 * @author: Lh
 * @version: 1.0.0
 */
public interface GatewayRouteEntityRepository  extends JpaRepository<GatewayRouteEntity, String>, JpaSpecificationExecutor<GatewayRouteEntity> {
}
