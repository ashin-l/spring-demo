package com.example.springdemo.service;

import java.util.List;
import java.util.Map;

import com.example.springdemo.model.SysRole;

public interface RoleService {

  List<SysRole> getAll();
  SysRole getById(Long roleid);

  public List<SysRole> findByCondition(SysRole sysRole);

}