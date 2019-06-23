package com.stackroute.userservice.service;

import com.stackroute.userservice.config.Producer;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.dto.UserDto;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Producer producer;

    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if(user == null)
            throw new UserNotFoundException();

        return user;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistException {
        User fetchedUser = userRepository.findByUsername(user.getUsername());
        if(fetchedUser != null){
            throw new UserAlreadyExistException();
        }
        else{
            userRepository.save(user);
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            producer.sendUserDetailsToRabbitMQ(userDto);
        }
        return user;
    }
}
