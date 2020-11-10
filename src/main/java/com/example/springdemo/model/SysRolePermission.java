package com.example.springdemo.model;

import lombok.Data;

@Data
public class SysRolePermission {

  private Long roleId;
  private Integer[] permissionId;

}