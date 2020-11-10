package com.example.springdemo.service.impl;

import java.util.List;
import java.util.Map;

import com.example.springdemo.common.PageQuery;
import com.example.springdemo.dao.RoleDao;
import com.example.springdemo.model.SysRole;
import com.example.springdemo.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDao roleDao;

  @Override
  public List<SysRole> getAll() {
    return roleDao.getAll();
  }

  @Override
  public SysRole getById(Long roleid) {
    return roleDao.getById(roleid);
  }

  @Override
  public List<SysRole> findByCondition(SysRole sysRole) {
    return roleDao.findByCondition(sysRole);
  }

}