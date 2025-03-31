package com.example.Spring_MyBatis_Thymeleaf.logic;

import com.example.Spring_MyBatis_Thymeleaf.form.LoginForm;
import com.example.Spring_MyBatis_Thymeleaf.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class LoginLogic {
    private final UserService userService;

    public LoginLogic(UserService userService) {
        this.userService = userService;
    }

    public void validateLoginInput(LoginForm loginForm, BindingResult bindingResult) {
        if(loginForm.getUsername() == null || loginForm.getUsername().trim().isEmpty()) {
            bindingResult.rejectValue("username", "error.username", "Username is required");
        }
        if(loginForm.getPassword() == null || loginForm.getPassword().trim().isEmpty()) {
            bindingResult.rejectValue("password", "error.password", "Password is required");
        }
    }


}
