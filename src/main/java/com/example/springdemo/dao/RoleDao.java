package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.model.SysRole;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {

  List<SysRole> getAll();

  SysRole getById(Long roleid);

  public List<SysRole> findByCondition(SysRole sysRole);
}