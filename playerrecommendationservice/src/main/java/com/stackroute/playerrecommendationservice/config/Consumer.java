package com.stackroute.playerrecommendationservice.config;

import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.dto.PlayerDto;
import com.stackroute.playerrecommendationservice.service.PlayerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private PlayerService playerService;

    @RabbitListener(queues = "player_queue")
    public void getPlayerFromRabbitMq(PlayerDto playerDto) {
        System.out.println("Player --> " + playerDto);
        Player player = getPlayer(playerDto);

        if (playerDto.getOperation().equals("ADD"))
            playerService.savePlayer(player);
        else
            playerService.deletePlayer(player);
    }

    private Player getPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setPid(playerDto.getPid());
        player.setBattingStyle(playerDto.getBattingStyle());
        player.setBowlingStyle(playerDto.getBowlingStyle());
        player.setCountry(playerDto.getCountry());
        player.setImageUrl(playerDto.getImageUrl());
        player.setProfile(playerDto.getProfile());
        player.setName(playerDto.getName());
        return player;
    }
}
