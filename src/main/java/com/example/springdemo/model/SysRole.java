package com.example.springdemo.model;

import lombok.Data;

@Data
public class SysRole {

  private Long id;
  private String name;
  private String description;
  private Integer createTime;
  private Integer updateTime;

}