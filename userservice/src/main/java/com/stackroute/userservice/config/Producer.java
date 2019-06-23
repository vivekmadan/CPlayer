package com.stackroute.userservice.config;

import com.stackroute.userservice.dto.UserDto;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserDetailsToRabbitMQ(UserDto userDto){
        rabbitTemplate.convertAndSend(directExchange.getName(), "user_routing", userDto);
    }
}
