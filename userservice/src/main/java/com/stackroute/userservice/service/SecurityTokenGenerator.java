package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String, String> generateToekn(User user);

}
