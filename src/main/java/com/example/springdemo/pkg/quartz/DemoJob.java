package com.example.springdemo.pkg.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DemoJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("========================");
        var map = context.getMergedJobDataMap();
        String beanName = map.getString("beanName");
        log.info("Running Job name : {} ", map.getString("name"));
        log.info("Running Job description : {}", map.getString("jobDescription"));
        log.info("Running Job group: {} ", map.getString("group"));
        log.info(String.format("Running Job cron : %s", map.getString("cronExpression")));
        log.info("Running Job beanName : {} ", beanName);
    }
}
