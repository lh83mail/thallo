package org.halo.thallo.authenserver.dao;

import com.netflix.discovery.converters.Auto;
import org.halo.thallo.authenserver.model.User;
import org.halo.thallo.authenserver.service.UserException;
import org.halo.thallo.authenserver.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by lihong on 17-5-24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value = {
        "spring.cloud.discovery.enabled=false"
})
public class UserServiceTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() throws UserException {
        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        user.put("name", "张三");
        user.put("age", 20);
        user.put("isBoss", true);

        user = userService.saveUser(user);
        Assert.assertNotNull("用户不能是NULL", user);
    }
}
