package org.halo.thallo.common.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.halo.thallo.common.entity.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create At  2020/1/7 11:15
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="创建时间范围查询")
public class QueryFilter<T extends BaseEntity> extends JpaQueryFilter<T> {
    private Date createdTimeStart;
    private Date createdTimeEnd;

    public void apply(List<Predicate> list, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (this.createdTimeStart != null) {
            list.add(cb.greaterThanOrEqualTo(root.get("createAt"), this.createdTimeStart));
        }
        if (this.createdTimeEnd != null) {
            list.add(cb.lessThan(root.get("createAt"), this.createdTimeEnd));
        }
    }
}
