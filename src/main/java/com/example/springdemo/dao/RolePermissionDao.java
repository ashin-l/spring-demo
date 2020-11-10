package com.example.springdemo.dao;

import com.example.springdemo.model.SysRolePermission;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionDao {

  SysRolePermission getByRoleId(Long roleId);

  int save(SysRolePermission sysRolePermission);

}