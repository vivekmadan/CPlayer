package com.stackroute.favouriteservice.service;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistException;
import com.stackroute.favouriteservice.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Producer producer;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Player player;
    private List<Player> playerList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User("vivekmadan", "vivekmadan@gmail.com");

        player = getPlayer();
        playerList = new ArrayList<>();
        playerList.add(player);
        user.setPlayerList(playerList);
    }

    @After
    public void tearDown() throws Exception {
        user = null;
        playerList = null;
        player = null;
    }

    @Test
    public void testSaveUser() throws UserAlreadyExistException {
        when(userRepository.save(user)).thenReturn(user);
        User fetchedUser = userService.saveUser(user);
        Assert.assertEquals(fetchedUser.getUsername(), user.getUsername());
        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void testSavePlayerToFavourites() throws PlayerAlreadyExistException {
        player = getPlayer();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser = userService.savePlayerToFavourites(user.getUsername(), player);
        Assert.assertEquals(fetchedUser.getUsername(), user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeletePlayersFromFavourites() throws PlayerNotFoundException {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser = userService.deletePlayerFromFavourites(user.getUsername(), player.getPid());
        Assert.assertEquals(fetchedUser.getUsername(), user.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllPlayersFromFavorites() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        List<Player> list = userService.getAllPlayersFromFavourites(user.getUsername());
        Assert.assertEquals(list, playerList);
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    private Player getPlayer(){
        Player player = new Player();
        player.setName("Vivek Madan");
        player.setBattingStyle("Right Hand");
        player.setBowlingStyle("Right Hand");
        player.setCountry("India");
        player.setImageUrl("Image Url");
        player.setPid(UUID.randomUUID().toString());
        player.setProfile("Profile");

        return player;
    }
}
