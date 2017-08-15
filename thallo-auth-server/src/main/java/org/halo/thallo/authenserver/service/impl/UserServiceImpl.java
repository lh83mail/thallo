package org.halo.thallo.authenserver.service.impl;

import org.halo.thallo.authenserver.dao.UserRepository;
import org.halo.thallo.authenserver.entity.UserEntity;
import org.halo.thallo.authenserver.global.Validator;
import org.halo.thallo.authenserver.global.ValidtionManager;
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

import java.util.List;
import java.util.UUID;

/**
 * Created by lihong on 17-5-24.
 */
@Service
public class UserServiceImpl implements UserService, ApplicationEventPublisherAware {

    private UserRepository userRepository;
    private ApplicationEventPublisher applicationEventPublisher;
    private SchemaService schemaService;
    private ValidtionManager validtionManager;

    @Override
    public User getUser(String uid) {
        return null;
    }


    @Override
    public User saveUser(User user) throws UserException {

        Schema schema = schemaService.getSchema(User.schemaId);
        if (schema == null) {
            throw new UserException("没有定义用户属性。请先定义用户属性");
        }

        // Process
        List<Validator> validatorList = validtionManager.getValidators(schema.getId());
        for (Validator validator : validatorList) {
            validator.validate(user);
        }

        // save user
        if (user.getUid() == null) {
            UserEntity entity = UserEntity.copyFrom(user);
            entity.setUid(UUID.randomUUID().toString());
            entity = userRepository.save(entity);

            applicationEventPublisher.publishEvent(new UserEvent(user, schema, Operation.add));

//            schema.
        }
        else {
            UserEntity old = userRepository.findOne(user.getUid());
            if (old == null) { // new user
                old = UserEntity.copyFrom(user);
                old = userRepository.save(old);
            }
            else { // edit user
                old = userRepository.save(old);
            }
        }

       // dispatchEvent(entity);
        return null;//entity;
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Autowired
    public void setSchemaService(SchemaService schemaService) {
        this.schemaService = schemaService;
    }
}
