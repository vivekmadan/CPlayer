package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User(1001, "vivekmadan", "vivekmadan@gmail.com", "abcd");

    }

    @After
    public void tearDown() throws Exception {
        user = null;
        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        userRepository.save(user);
        User userObj = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(userObj.getUsername(), user.getUsername());
        userRepository.delete(userObj);

    }

    @Test
    public void testLoginSuccess() {
        userRepository.save(user);
        User userObj = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        Assert.assertEquals(userObj.getUsername(), user.getUsername());
        userRepository.delete(userObj);
    }
}
