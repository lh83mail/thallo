package io.github.lh83mail.thallo.common.model;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Create At  2020/1/7 17:17
 *
 * @author: Lh
 * @version: 1.0.0
 */
public abstract class JpaQueryFilter<T> implements Specification<T> {
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<>();
        apply(list, root, query, cb);
        if (list.isEmpty()) {
            return cb.conjunction();
        }
        return cb.and(list.toArray(new Predicate[list.size()]));
    }

    abstract void apply(List<Predicate> list, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
}
