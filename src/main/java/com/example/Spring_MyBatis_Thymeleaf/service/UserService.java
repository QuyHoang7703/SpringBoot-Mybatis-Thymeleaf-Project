package com.example.Spring_MyBatis_Thymeleaf.service;

import com.example.Spring_MyBatis_Thymeleaf.model.User;

public interface UserService {
    User getUserByUsername(String username);
}
