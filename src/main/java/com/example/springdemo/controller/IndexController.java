package com.example.springdemo.controller;

import com.example.springdemo.common.Result;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IndexController {

  @Autowired
  private UserDetailsService userDetailService;

  @GetMapping("/logout")
  public Result logout() {
    return Result.ok("已经退出");
  }

  @GetMapping("/home")
  public Result home() {
    return Result.ok("in home");
  }

  @GetMapping("/hi")
  public String hi() {
    try {
      var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (username != null) {
        var user = userDetailService.loadUserByUsername((String) username);
        System.out.println(user);
        return "hi man " + username;
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    return "hi man *";
  }
}