package com.example.springdemo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.example.springdemo.service.UserService;


/**
 * @author shuang.kou
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

//    private final UserService userService;
    @Autowired
    UserService userService;

//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Map<String, String> registerUser) {
        //userService.saveUser(registerUser);
        return ResponseEntity.ok().build();
    }
}
