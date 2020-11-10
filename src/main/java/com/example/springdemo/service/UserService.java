package com.example.springdemo.service;

import java.util.List;

import com.example.springdemo.model.SysUser;

public interface UserService {

  int save(SysUser user);

  List<SysUser> getUserList();

  int update(SysUser user);

}