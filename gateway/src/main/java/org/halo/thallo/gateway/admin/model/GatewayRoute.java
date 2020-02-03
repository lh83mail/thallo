package org.halo.thallo.gateway.admin.model;

import lombok.*;
import org.halo.thallo.common.model.BaseModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute extends BaseModel<String> {
    private String uri;
    private String routeId;
    private String predicates;
    private String filters;
    private String description;
    @Builder.Default
    private Integer orders = 0;
    @Builder.Default
    private String status = "Y";
}