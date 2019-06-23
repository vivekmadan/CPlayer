package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;
    public User registerUser(User user) throws UserAlreadyExistException;
}
