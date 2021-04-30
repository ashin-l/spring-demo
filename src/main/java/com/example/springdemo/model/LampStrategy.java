package com.example.springdemo.model;

import lombok.Data;

@Data
public class LampStrategy {
    private Integer id;
    private String name;
    private String onTime;
    private String offTime;
    private Integer onJobid;
    private Integer offJobid;
    private Integer status;
}
