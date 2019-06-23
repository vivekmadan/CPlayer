package com.stackroute.playerrecommendationservice.controller;

import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RecommendedPlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    private Player player;
    private List<Player> players;

    @InjectMocks
    private RecommendedPlayerController controller;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        player = new Player("1001", "Dhoni", "India", "Right Hand", "Right Hand", "Profile", "Url", 2);
        players = new ArrayList<>();
        players.add(player);
    }

    @Test
    public void testGetAllRecommendedPlayer() throws Exception{
        when(playerService.getAllRecommendedPlayers()).thenReturn(players);
        mockMvc.perform(get("/api/v1/players"))
                .andExpect(status().isOk()).andDo(print());
        verify(playerService, times(1)).getAllRecommendedPlayers();
    }
}
