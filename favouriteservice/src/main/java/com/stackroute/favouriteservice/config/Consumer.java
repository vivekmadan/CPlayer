package com.stackroute.favouriteservice.config;

import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.dto.UserDto;
import com.stackroute.favouriteservice.exception.UserAlreadyExistException;
import com.stackroute.favouriteservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "user_queue")
    public void getUserFromRabbitMq(UserDto userDto) throws UserAlreadyExistException
    {
        System.out.println("User DTO --> " + userDto);
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userService.saveUser(user);
    }
}
