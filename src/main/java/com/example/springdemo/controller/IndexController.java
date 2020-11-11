package com.example.springdemo.controller;

import com.example.springdemo.common.Result;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "hi man *";
  }

  @GetMapping("/p1")
  public String p1() {
    return "in p1";
  }

  @GetMapping("/p1/hi")
  public String hip1() {
    return "hi in p1";
  }

  @GetMapping("/p2")
  public String p2() {
    return "in p2";
  }

}