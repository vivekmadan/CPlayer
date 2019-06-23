package com.stackroute.favouriteservice.repository;

import com.stackroute.favouriteservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("vivekmadan", "vivekmadan@gmail.com");
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void testSaveUser() {
        userRepository.save(user);
        User fetchedUser = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(fetchedUser.getUsername(), user.getUsername());
    }
}
