package com.example.springdemo.service.impl;

import com.example.springdemo.dao.RolePermissionDao;
import com.example.springdemo.model.SysRolePermission;
import com.example.springdemo.service.RolePermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionImpl implements RolePermissionService {

  @Autowired
  RolePermissionDao rolePermissionDao;

  @Override
  public SysRolePermission getByRoleid(Long roleid) {
    return rolePermissionDao.getByRoleId(roleid);
  }

  @Override
  public int save(SysRolePermission sysRolePermission) {
    return rolePermissionDao.save(sysRolePermission);
  }

}