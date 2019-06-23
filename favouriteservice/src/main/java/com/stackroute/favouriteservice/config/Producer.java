package com.stackroute.favouriteservice.config;

import com.stackroute.favouriteservice.dto.PlayerDto;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer
{
    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPlayerToRabbitMq(PlayerDto playerDto){
        rabbitTemplate.convertAndSend(directExchange.getName(), "player_routing", playerDto);
    }
}
