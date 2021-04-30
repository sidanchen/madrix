package com.madrix.util;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


/**
 * 定时器管理类
 * @author sdc
 * @data 2017年8月4日
 */
public class QuartzManager {
	public static SchedulerFactory sf = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "group1";
	private static String TRIGGER_GROUP_NAME = "trigger1";

	public static void addJob(String jobName, Job job, String time) throws SchedulerException, ParseException {
		Scheduler scheduler = sf.getScheduler();

		// 初始化任务
		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());

		// 初始化触发器
		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组

		trigger.setCronExpression(time);

		scheduler.scheduleJob(jobDetail, trigger);
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}

	}
	
	/**
	 * 可携带Integer类型的参数
	 * @param jobName
	 * @param job
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 * @data 2017年8月10日
	 * @author sdc
	 */
	public static void addJob(String jobName, Job job, String time,Integer prame) throws SchedulerException, ParseException {
		Scheduler scheduler = sf.getScheduler();

		// 初始化任务
		JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());
		//携带参数
		jobDetail.getJobDataMap().put("prame", prame);
		jobDetail.getJobDataMap().put("scheduler", scheduler);
		// 初始化触发器
		CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组

		trigger.setCronExpression(time);

		scheduler.scheduleJob(jobDetail, trigger);
		if (!scheduler.isShutdown()) {
			scheduler.start();
		}
	}
}
