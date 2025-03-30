package com.example.Spring_MyBatis_Thymeleaf.mapper;

import com.example.Spring_MyBatis_Thymeleaf.model.User;

public interface UserMapper {
    User getUserByUsername(String username);
}
