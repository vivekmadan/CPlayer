package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.dto.PlayerDto;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistException;
import com.stackroute.favouriteservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private Producer producer;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) throws UserAlreadyExistException
    {
        User fetchedUser = userRepository.findByUsername(user.getUsername());
        if(fetchedUser != null)
            throw new UserAlreadyExistException();

        return userRepository.save(user);
    }

    @Override
    public User savePlayerToFavourites(String username, Player player) throws PlayerAlreadyExistException {
        User user = userRepository.findByUsername(username);
        List<Player> playerList = user.getPlayerList();

        if(playerList != null && !playerList.isEmpty())
        {
            for(Player p : playerList)
            {
                if(player.getPid().equals(p.getPid()))
                    throw new PlayerAlreadyExistException();
            }
        }
        else
        {
            playerList = new ArrayList<>();
        }
        playerList.add(player);
        user.setPlayerList(playerList);
        userRepository.save(user);

        PlayerDto playerDto = getPlayerDto(player, "ADD");
        producer.sendPlayerToRabbitMq(playerDto);
        return user;
    }

    @Override
    public User deletePlayerFromFavourites(String username, String playerId) throws PlayerNotFoundException {
        User user = userRepository.findByUsername(username);
        List<Player> playerList = user.getPlayerList();

        if(playerList != null && !playerList.isEmpty())
        {
            for(Player player : playerList)
            {
                if(playerId.equals(player.getPid()))
                {
                    playerList.remove(player);
                    user.setPlayerList(playerList);
                    userRepository.save(user);

                    PlayerDto playerDto = getPlayerDto(player, "DELETE");
                    producer.sendPlayerToRabbitMq(playerDto);
                    break;
                }
            }
        }
        else
        {
            throw new PlayerNotFoundException();
        }


        return user;
    }

    @Override
    public List<Player> getAllPlayersFromFavourites(String username)
    {
        User user = userRepository.findByUsername(username);
        return user.getPlayerList();
    }

    private PlayerDto getPlayerDto(Player player, String operation){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPid(player.getPid());
        playerDto.setName(player.getName());
        playerDto.setCountry(player.getCountry());
        playerDto.setBattingStyle(player.getBattingStyle());
        playerDto.setBowlingStyle(player.getBowlingStyle());
        playerDto.setProfile(player.getProfile());
        playerDto.setImageUrl(player.getImageUrl());
        playerDto.setOperation(operation);
        return playerDto;
    }
}
