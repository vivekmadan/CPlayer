package com.stackroute.playerrecommendationservice.service;

import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player deletePlayer(Player player) {
        Player p = playerRepository.findByPid(player.getPid());

        if(p != null)
        {
            if(p.getCount() > 1)
            {
                player.setCount(p.getCount() - 1);
                playerRepository.save(player);
            }
            else
            {
                playerRepository.delete(player);
            }
        }
        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        Player p = playerRepository.findByPid(player.getPid());
        if(p != null)
            player.setCount(p.getCount() + 1);
        else
            player.setCount(1);

        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAllRecommendedPlayers() {
        return playerRepository.findAll();
    }
}
