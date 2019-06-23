package com.stackroute.playerrecommendationservice.service;
import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.repository.PlayerRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    private Player player;
    private List<Player> playerList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        player = new Player();
        player.setPid("1001");
        player.setBattingStyle("Right Hand");
        player.setBowlingStyle("Right Hand");
        player.setCount(1);
        player.setCountry("India");
        player.setName("Virat");
        player.setProfile("Profile");
        player.setImageUrl("Image URL");
        playerList = new ArrayList<>();
        playerList.add(player);
    }

    @After
    public void tearDown() throws Exception {
        playerRepository.deleteAll();
        player = null;
        playerList = null;
    }

    @Test
    public void testDeletePlayerCount1() {
        when(playerRepository.findByPid(player.getPid())).thenReturn(player);
        Player fetchedPlayer = playerService.deletePlayer(player);
        Assert.assertEquals(fetchedPlayer.getPid(), player.getPid());
        verify(playerRepository, times(1)).findByPid(player.getPid());
        verify(playerRepository, times(1)).delete(player);

    }

    @Test
    public void testDeletePlayerCount2() {
        player.setCount(2);
        when(playerRepository.findByPid(player.getPid())).thenReturn(player);
        Player fetchedPlayer = playerService.deletePlayer(player);
        Assert.assertEquals(fetchedPlayer.getPid(), player.getPid());
        verify(playerRepository, times(1)).findByPid(player.getPid());
        verify(playerRepository, times(1)).save(player);

    }

    @Test
    public void testSavePlayer() {
        when(playerRepository.save(player)).thenReturn(player);
        Player fetchedUser = playerService.savePlayer(player);
        Assert.assertEquals(fetchedUser.getPid(), player.getPid());
        verify(playerRepository, times(1)).findByPid(player.getPid());
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void testGetAllRecommendedPlayers() {
        when(playerRepository.findAll()).thenReturn(playerList);
        List<Player> list = playerService.getAllRecommendedPlayers();
        Assert.assertEquals(playerList, list);
        verify(playerRepository, times(1)).findAll();
    }
}
