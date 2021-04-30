package com.example.springdemo.pkg.quartz;

import java.util.List;

import com.example.springdemo.dao.JobModelDao;
import com.example.springdemo.model.JobModel;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicJobService {

    @Autowired
    private JobModelDao dao;

    // 通过Id获取Job
    public JobModel getJobModelById(Integer id) {
        return dao.selectById(id);
    }

    // 从数据库中加载获取到所有Job
    public List<JobModel> loadJobs() {
        return dao.selectList(null);
    }

    // 获取JobDataMap.(Job参数对象)
    public JobDataMap getJobDataMap(JobModel job) {
        JobDataMap map = new JobDataMap();
        map.put("id", job.getId());
        map.put("name", job.getName());
        map.put("jobGroup", job.getJobGroup());
        map.put("cronExpression", job.getCron());
        map.put("beanName", job.getBeanName());
        map.put("jobDescription", job.getDescription());
        map.put("status", job.getStatus());
        return map;
    }

    // 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class).withIdentity(jobKey).withDescription(description).setJobData(map)
                .storeDurably().build();
    }

    // 获取Trigger (Job的触发器,执行规则)
    public Trigger getTrigger(JobModel job) {
        return TriggerBuilder.newTrigger().withIdentity(job.getName() + job.getId(), job.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron())).build();
    }

    // 获取JobKey,包含Name和Group
    public JobKey getJobKey(JobModel job) {
        return JobKey.jobKey(job.getName() + job.getId(), job.getJobGroup());
    }
}