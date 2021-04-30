package com.example.springdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class JobModel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; // job名称
    private String jobGroup = "default"; // job组名
    private String cron; // 执行的cron
    private String beanName; // job的执行具体任务Bean名称
    private String description; // job描述信息
    private Integer status; // job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job
}
