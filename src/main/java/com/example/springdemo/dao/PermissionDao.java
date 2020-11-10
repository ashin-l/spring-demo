package com.example.springdemo.dao;

import java.util.List;

import com.example.springdemo.model.SysPermission;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDao {

  SysPermission getById(int id);
  List<SysPermission> listByUserId(Long userId);

}