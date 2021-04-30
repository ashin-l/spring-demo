package com.example.springdemo;

import com.example.springdemo.model.JobModel;
import com.example.springdemo.pkg.quartz.JobUtile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuartzTest {
    @Autowired
    JobUtile ju;

    @Test
    void test() {
        var jm = new JobModel();
        jm.setName("l001");
        jm.setCron("*/5 * * * * ?");
        jm.setBeanName("DemoJob");
        jm.setStatus(1);
        ju.createJob(jm);
    }
}
