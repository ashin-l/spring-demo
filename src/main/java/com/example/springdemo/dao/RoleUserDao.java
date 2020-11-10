package com.example.springdemo.dao;

import com.example.springdemo.model.SysRoleUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserDao {
  int save(SysRoleUser sysRoleUser);
}