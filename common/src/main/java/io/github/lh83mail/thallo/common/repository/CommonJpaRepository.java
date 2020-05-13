package io.github.lh83mail.thallo.common.repository;

import io.github.lh83mail.thallo.common.entity.BaseEntity;
import io.github.lh83mail.thallo.common.web.UserContextHolder;
import io.github.lh83mail.thallo.common.web.UserContext;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 *
 * 使用方法,需要同时配合 {@link UserContext}
 * <pre>
 * @EnableJpaRepositories(repositoryBaseClass = CommonJpaRepository.class)
 * public class YourApplication {
 *     public static void main(String[] args) {
 *         SpringApplication.run(YourApplication.class, args);
 *     }
 * }
 * </pre>
 * Create At  2020/1/7 15:16
 *
 * @author: Lh
 * @version: 1.0.0
 */
public class CommonJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

    private static final String DEFAULT_USER_NAME = "系统";

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;

    public CommonJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            this.setupCreatedInfo(entity);
            em.persist(entity);
            return entity;
        } else {
            this.setupUpdateInfo(entity);
            return em.merge(entity);
        }
    }

    private <S extends T> void setupCreatedInfo(S entity) {
        if (BaseEntity.class.isAssignableFrom(entity.getClass())) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setCreateAt(new Date());
            baseEntity.setUpdatedBy(getUserName());
            baseEntity.setUpdateAt(baseEntity.getCreateAt());
            baseEntity.setUpdatedBy(baseEntity.getCreatedBy());
        }
    }

    private <S extends T> void setupUpdateInfo(S entity) {
        if (BaseEntity.class.isAssignableFrom(entity.getClass())) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setUpdateAt(new Date());
            baseEntity.setUpdatedBy(getUserName());
        }
    }

    private String getUserName() {
        UserContext context = UserContextHolder.getInstance().getContext();
        return context == null ? DEFAULT_USER_NAME : context.getName();
    }

}
