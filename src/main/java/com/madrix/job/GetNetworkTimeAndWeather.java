package com.madrix.job;

import com.madrix.service.LightControllService;
import com.madrix.util.SpringContextUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 获取网络上的日出日落与天气添加到数据库中
 * Created by sdc on 2018/2/28.
 */
public class GetNetworkTimeAndWeather implements Job {
    LightControllService lightControllService = SpringContextUtil.getBean("lightControllServiceImpl");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        lightControllService.getNetworkAndWeather();
    }
}
