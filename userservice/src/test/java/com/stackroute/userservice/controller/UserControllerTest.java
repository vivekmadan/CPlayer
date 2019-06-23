package com.stackroute.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.SecurityTokenGenerator;
import com.stackroute.userservice.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private SecurityTokenGenerator securityTokenGenerator;

    @InjectMocks
    private UserController userController;
    
    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User(1001, "vivekmadan", "vivekmadan@gmail.com", "abcd");
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @Test
    public void testRegisterUser() throws Exception{
        when(userService.registerUser(user)).thenReturn(user);
        mockMvc.perform(post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(userService, times(1)).registerUser(user);

    }

    @Test
    public void testLogin() throws  Exception {
        when(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(user)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(userService, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public String jsonToString(User user){
        String result = null;

        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(user);
            result = jsonContent;
        }
        catch(JsonProcessingException e)
        {
            result = "Json Processing Exception";
        }
        return result;
    }
}
