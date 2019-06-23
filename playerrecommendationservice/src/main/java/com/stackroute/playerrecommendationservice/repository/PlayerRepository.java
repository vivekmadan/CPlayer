package com.stackroute.playerrecommendationservice.repository;

import com.stackroute.playerrecommendationservice.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
    public Player findByPid(String pid);
}
