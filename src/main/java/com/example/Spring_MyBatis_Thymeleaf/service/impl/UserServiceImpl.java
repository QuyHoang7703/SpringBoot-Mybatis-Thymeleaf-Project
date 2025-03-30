package com.example.Spring_MyBatis_Thymeleaf.service.impl;

import com.example.Spring_MyBatis_Thymeleaf.mapper.UserMapper;
import com.example.Spring_MyBatis_Thymeleaf.model.User;
import com.example.Spring_MyBatis_Thymeleaf.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;


    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public User getUserByUsername(String username) {
        return this.userMapper.getUserByUsername(username);
    }
}
