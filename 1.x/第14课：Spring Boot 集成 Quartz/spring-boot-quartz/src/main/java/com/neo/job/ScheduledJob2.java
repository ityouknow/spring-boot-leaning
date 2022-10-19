package com.neo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJob2 implements Job {
  
    @Override  
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("schedule job2 is running ...");
    }  
}  