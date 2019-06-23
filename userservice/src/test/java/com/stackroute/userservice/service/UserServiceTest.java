package com.stackroute.userservice.service;

import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private Producer producer;

    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User(1001, "vivekmadan", "vivekmadan@gmail.com", "abcd");
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void testFindByUsernameAndPassword() throws UserNotFoundException {
        when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
        User userObj = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        Assert.assertEquals(userObj, user);
        verify(userRepository, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Test
    public void testRegisterUser() throws UserAlreadyExistException {
        when(userRepository.save(user)).thenReturn(user);
        User fetchUser = userService.registerUser(user);
        Assert.assertEquals(fetchUser, user);
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}
