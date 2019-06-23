package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistException;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws UserAlreadyExistException;
    public User savePlayerToFavourites(String username, Player player) throws PlayerAlreadyExistException;
    public User deletePlayerFromFavourites(String username, String playerId) throws PlayerNotFoundException;
    public List<Player> getAllPlayersFromFavourites(String username);
}
