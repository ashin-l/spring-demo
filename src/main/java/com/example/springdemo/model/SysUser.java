package com.example.springdemo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class SysUser {

  private Integer id;
  private String username;
  private String password;
  private String roles;

  public List<SimpleGrantedAuthority> getRoles() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    Arrays.stream(roles.split(",")).forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
    return authorities;
  }
}