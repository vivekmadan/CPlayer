package com.stackroute.playerrecommendationservice.controller;

import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RecommendedPlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllRecommendedPlayers(){
        List<Player> playerList = playerService.getAllRecommendedPlayers();
        return new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
    }
}
