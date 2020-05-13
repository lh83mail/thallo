package io.github.lh83mail.thallo.gateway.admin.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.github.lh83mail.thallo.common.model.QueryFilter;
import io.github.lh83mail.thallo.gateway.admin.entity.GatewayRouteEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.util.StringUtils.hasLength;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "网关路由查询条件", parent = QueryFilter.class)
public class GatewayRouteQueryParam extends QueryFilter<GatewayRouteEntity> {
    private String uri;

    @Override
    public void apply(List<Predicate> list, Root<GatewayRouteEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        super.apply(list, root, query, cb);
        if (hasLength(this.uri)) {
            list.add(cb.equal(root.get("uri"), uri));
        }
    }
}
