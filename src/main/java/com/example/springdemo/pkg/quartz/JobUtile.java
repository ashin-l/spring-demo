package com.example.springdemo.pkg.quartz;

import java.util.Objects;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.example.springdemo.dao.JobModelDao;
import com.example.springdemo.model.JobModel;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by EalenXie on 2018/6/4 16:12
 */
@Slf4j
@Component
public class JobUtile {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private DynamicJobService jobService;

    @Autowired
    private JobModelDao dao;

    // 初始化启动所有的Job
    @PostConstruct
    public void initialize() {
        try {
            reStartAllJobs();
            log.info("init success");
        } catch (SchedulerException e) {
            log.error("printStackTrace ", e);
        }
    }

    public void createJob(JobModel jm) {
        if (!CronExpression.isValidExpression(jm.getCron()))
            return;
        dao.insert(jm);
        System.out.println(jm);
        synchronized (log) {
            if (jm.getStatus() == 1) {
                try {
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();
                    JobDataMap map = jobService.getJobDataMap(jm);
                    JobKey jobKey = jobService.getJobKey(jm);
                    JobDetail jobDetail = jobService.getJobDetail(jobKey, jm.getDescription(), map);
                    if (jm.getStatus() == 1) {
                        scheduler.scheduleJob(jobDetail, jobService.getTrigger(jm));
                    }
                } catch (Exception e) {
                    log.error("printStackTrace", e);
                }
            } else {
                log.info("Job jump name : {} , Because {} status is {}", jm.getName(), jm.getBeanName(),
                        jm.getStatus());
                dao.insert(jm);
            }
        }
    }

    // 根据ID重启某个Job
    public String refresh(Integer id) throws SchedulerException {
        String result;
        JobModel jm = jobService.getJobModelById(id);
        if (jm == null) {
            return "error: id is not exist ";
        }
        synchronized (log) {
            JobKey jobKey = jobService.getJobKey(jm);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(jm);
            JobDetail jobDetail = jobService.getJobDetail(jobKey, jm.getDescription(), map);
            if (jm.getStatus() == 1) {
                scheduler.scheduleJob(jobDetail, jobService.getTrigger(jm));
                result = "Refresh Job : " + jm.getName() + "\t jarPath: " + jm.getBeanName() + " success !";
            } else {
                result = "Refresh Job : " + jm.getName() + "\t jarPath: " + jm.getBeanName() + " failed ! , "
                        + "Because the Job status is " + jm.getStatus();
            }
        }
        return result;
    }

    // 重启数据库中所有的Job
    public String refreshAll() {
        String result;
        try {
            reStartAllJobs();
            result = "success";
        } catch (SchedulerException e) {
            result = "exception : " + e.getMessage();
        }
        return "refresh all jobs : " + result;
    }

    /**
     * 重新启动所有的job
     */
    private void reStartAllJobs() throws SchedulerException {
        synchronized (log) { // 只允许一个线程进入操作
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            scheduler.pauseJobs(GroupMatcher.anyGroup()); // 暂停所有JOB
            for (JobKey jobKey : set) { // 删除从数据库中注册的所有JOB
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            for (JobModel job : jobService.loadJobs()) { // 从数据库中注册的所有JOB
                log.info("Job register name : {} , group : {} , cron : {}", job.getName(), job.getJobGroup(),
                        job.getCron());
                JobDataMap map = jobService.getJobDataMap(job);
                JobKey jobKey = jobService.getJobKey(job);
                JobDetail jobDetail = jobService.getJobDetail(jobKey, job.getDescription(), map);
                if (job.getStatus() == 1)
                    scheduler.scheduleJob(jobDetail, jobService.getTrigger(job));
                else
                    log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(),
                            job.getStatus());
            }
        }
    }

    // 修改某个Job执行的Cron
    public String modifyJob(JobModel jm) {
        if (!CronExpression.isValidExpression(jm.getCron()))
            return "cron is invalid !";
        synchronized (log) {
            JobModel job = jobService.getJobModelById(jm.getId());
            if (job.getStatus() == 1) {
                try {
                    JobKey jobKey = jobService.getJobKey(job);
                    TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
                    Scheduler scheduler = schedulerFactoryBean.getScheduler();
                    CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    String oldCron = cronTrigger.getCronExpression();
                    if (!oldCron.equalsIgnoreCase(jm.getCron())) {
                        job.setCron(jm.getCron());
                        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jm.getCron());
                        CronTrigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity(jobKey.getName(), jobKey.getGroup()).withSchedule(cronScheduleBuilder)
                                .usingJobData(jobService.getJobDataMap(job)).build();
                        scheduler.rescheduleJob(triggerKey, trigger);
                        dao.updateById(job);
                    }
                } catch (Exception e) {
                    log.error("printStackTrace", e);
                }
            } else {
                log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(), job.getStatus());
                return "modify failure , because the job is closed";
            }
        }
        return "modify success";
    }

}