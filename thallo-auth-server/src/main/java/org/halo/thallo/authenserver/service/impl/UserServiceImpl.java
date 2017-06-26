package org.halo.thallo.authenserver.service.impl;

import org.halo.thallo.authenserver.dao.UserRepository;
import org.halo.thallo.authenserver.entity.UserEntity;
import org.halo.thallo.authenserver.model.Operation;
import org.halo.thallo.authenserver.model.Schema;
import org.halo.thallo.authenserver.model.User;
import org.halo.thallo.authenserver.model.event.UserEvent;
import org.halo.thallo.authenserver.service.SchemaService;
import org.halo.thallo.authenserver.service.UserException;
import org.halo.thallo.authenserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * Created by lihong on 17-5-24.
 */
@Service
public class UserServiceImpl implements UserService, ApplicationEventPublisherAware {

    private UserRepository userRepository;
    private ApplicationEventPublisher applicationEventPublisher;
    private SchemaService schemaService;

    @Override
    public User findUser(String uid) {
        return null;
    }


    @Override
    public User saveUser(User user) throws UserException {

        Schema schema = schemaService.getSchema(User.schemaId);
        if (schema == null) {
            throw new UserException("没有定义用户属性。");
        }

        // TODO 验证用户对象有效性 schema, user
        
        UserEntity old = userRepository.findOne(user.getUid());
        UserEntity entity = null;
        if (old == null) {
            entity = userRepository.save(UserEntity.copyFrom(user));
            applicationEventPublisher.publishEvent(new UserEvent(user, schema, Operation.add));
        } else {
            entity = userRepository.save(UserEntity.copyFrom(user));
        }
       // dispatchEvent(entity);
        return entity;
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
