package com.example.springdemo.controller;

import com.example.springdemo.common.Result;
import com.example.springdemo.model.JobModel;
import com.example.springdemo.pkg.quartz.JobUtile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quartz")
public class QuartzController {
    @Autowired
    JobUtile ju;

    @GetMapping("/add")
    public Result add() {
        var jm = new JobModel();
        jm.setName("l001");
        jm.setCron("20 23 9 * * ?");
        jm.setBeanName("demoJob");
        jm.setStatus(1);
        ju.createJob(jm);
        System.out.println("job id:" + jm.getId());
        return Result.ok();
    }
}
