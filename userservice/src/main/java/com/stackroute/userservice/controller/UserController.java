package com.stackroute.userservice.controller;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.service.SecurityTokenGenerator;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserAlreadyExistException{
        ResponseEntity<User> responseEntity = null;
        try{
            user = userService.registerUser(user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch(UserAlreadyExistException e){
            throw new UserAlreadyExistException();
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException{
        ResponseEntity<?> responseEntity = null;
        Map<String, String> map = null;

        try{
            User fetchedUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if(fetchedUser.getUsername().equals(user.getUsername())){
                map = securityTokenGenerator.generateToekn(user);
                responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
            }
        }
        catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        
        for(int i = 0; i < 1000000; i++){
            System.out.println("Hello");
        }
        return responseEntity;
    }
}
