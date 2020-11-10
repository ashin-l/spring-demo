package com.example.springdemo.service;

import com.example.springdemo.model.SysRolePermission;

public interface RolePermissionService {

  SysRolePermission getByRoleid(Long roleid);
  int save(SysRolePermission sysRolePermission);
}