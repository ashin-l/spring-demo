package com.example.springdemo.form;

import lombok.Data;

@Data
public class AddRoleForm {
  private Long roleId;
  private Integer[] permissionId;
}