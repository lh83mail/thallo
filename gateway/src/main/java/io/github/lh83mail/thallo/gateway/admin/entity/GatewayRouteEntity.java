package io.github.lh83mail.thallo.gateway.admin.entity;

import io.github.lh83mail.thallo.gateway.admin.model.GatewayRoute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.github.lh83mail.thallo.common.entity.UuidBaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Create At  2020/1/7 11:39
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Entity
@Table(name = "gateway_route")
@Data
@EqualsAndHashCode(callSuper=true)
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public class GatewayRouteEntity extends UuidBaseEntity<GatewayRoute>  {
    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    private Integer orders = 0;
    private String status = "Y";
}
