package com.stackroute.favouriteservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserPlayerController.class)
public class UserPlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;
    private Player player;
    private List<Player> playerList;

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

    @Before
    public void setUp() throws Exception {
        user = new User("vivekmadan", "vivekmadan@gmail.com");
        playerList = new ArrayList<>();
        player = getPlayer();
        playerList.add(player);
        user.setPlayerList(playerList);
    }

    @After
    public void tearDown() throws Exception {
        player = null;
    }

    @Test
    public void testSavePlayerToFavourites() throws Exception {
        when(userService.savePlayerToFavourites(user.getUsername(), player)).thenReturn(user);
        mockMvc.perform(post("/api/v1/favouriteservice/user/{username}/player", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(player)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(userService, times(1)).savePlayerToFavourites(user.getUsername(), player);

    }

    @Test
    public void testDeletePlayerFromFavourite() throws Exception {
        when(userService.deletePlayerFromFavourites(user.getUsername(), player.getPid())).thenReturn(user);
        mockMvc.perform(delete("/api/v1/favouriteservice/user/{username}/player", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(player)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userService, times(1)).deletePlayerFromFavourites(user.getUsername(), player.getPid());

    }

    @Test
    public void testGetAllPlayersFromFaovourite() throws  Exception{
        when(userService.getAllPlayersFromFavourites(user.getUsername())).thenReturn(playerList);
        mockMvc.perform(get("/api/v1/favouriteservice/user/{username}/players", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userService, times(1)).getAllPlayersFromFavourites(user.getUsername());
    }

    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result = null;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonContent = objectMapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e){
            result = "Json Processing Error";
        }
        return result;
    }
}
