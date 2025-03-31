package com.example.Spring_MyBatis_Thymeleaf.controller;

import com.example.Spring_MyBatis_Thymeleaf.form.LoginForm;
import com.example.Spring_MyBatis_Thymeleaf.logic.LoginLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final LoginLogic loginLogic;

    public AuthController(LoginLogic loginLogic) {
        this.loginLogic = loginLogic;
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(name="error", defaultValue = "false") boolean error, Model model) {
        if (error) {
            model.addAttribute("errorMessage", "Username or password is incorrect");
        }
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
        this.loginLogic.validateLoginInput(loginForm, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("loginForm", loginForm);
            return "login";
        }
        return "forward:/process-login";
    }
}
