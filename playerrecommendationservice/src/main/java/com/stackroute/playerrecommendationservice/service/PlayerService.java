package com.stackroute.playerrecommendationservice.service;

import com.stackroute.playerrecommendationservice.domain.Player;

import java.util.List;

public interface PlayerService {
    public Player savePlayer(Player player);
    public Player deletePlayer(Player player);
    public List<Player> getAllRecommendedPlayers();
}
