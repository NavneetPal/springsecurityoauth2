package com.nav.springsecurityoauth2.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {

    @GetMapping("/")
    public String greet(){
        return "Welcome to Navan for you";
    }

    @GetMapping("/login")
    public void login(HttpServletResponse res) throws IOException {
        res.sendRedirect("/oauth2/authorization/google");
    }
}
