package com.example.springdemo.model;

import lombok.Data;

@Data
public class SysPermission {

  private int id;
  private int parentId;
  private String name;
  private String css;
  private String href;
  private int type;
  private String permission;
  private int sort;

}