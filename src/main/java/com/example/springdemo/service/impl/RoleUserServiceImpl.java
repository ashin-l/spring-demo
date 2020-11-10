package com.example.springdemo.service.impl;

import com.example.springdemo.dao.RoleUserDao;
import com.example.springdemo.model.SysRoleUser;
import com.example.springdemo.service.RoleUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl implements RoleUserService {

  @Autowired
  private RoleUserDao roleuserDao;

  @Override
  public void addRoleUser(SysRoleUser roleuser) {
    roleuserDao.save(roleuser);
  }

}