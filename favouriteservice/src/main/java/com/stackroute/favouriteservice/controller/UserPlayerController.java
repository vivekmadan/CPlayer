package com.stackroute.favouriteservice.controller;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favouriteservice")
public class UserPlayerController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/{username}/player")
    public ResponseEntity<?> savePlayerToFavourites(@PathVariable("username") String username, @RequestBody Player player) throws PlayerAlreadyExistException
    {
        ResponseEntity<?> responseEntity = null;
        try
        {
            User user = userService.savePlayerToFavourites(username, player);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch(PlayerAlreadyExistException e)
        {
            throw new PlayerAlreadyExistException();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @DeleteMapping("/user/{username}/player")
    public ResponseEntity<?> deletePlayerFromFavourite(@PathVariable("username") String username, @RequestBody Player player) throws PlayerNotFoundException
    {
        ResponseEntity<?> responseEntity = null;

        try {
            User user = userService.deletePlayerFromFavourites(username, player.getPid());
            responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        }
        catch(PlayerNotFoundException e){
            throw new PlayerNotFoundException();
        }
        catch(Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/user/{username}/players")
    public ResponseEntity<?> getAllPlayersFromFavourite(@PathVariable("username") String username)
    {
        ResponseEntity<?> responseEntity = null;
        try{
            List<Player> playerList = userService.getAllPlayersFromFavourites(username);
            responseEntity = new ResponseEntity<>(playerList, HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
