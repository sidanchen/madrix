package com.madrix.listen;

import com.madrix.job.AutoSwitchLight;
import com.madrix.job.ExecInstruction;
import com.madrix.job.GetLightStatus;
import com.madrix.job.GetNetworkTimeAndWeather;
import com.madrix.util.QuartzManager;
import org.quartz.SchedulerException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.ParseException;

/**
 * Created by sdc on 2018/2/28.
 */
public class SessionListen implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

//        System.out.println("----------定时器任务开启----------");
//        //每天凌晨两点
//        String time = "0 0 2 * * ?";
//        //每天凌晨五点
//        String time1 = "0 0 5 * * ?";
//        //每隔一分钟
//        String time2 = "*/5 * * * * ?";
//        //每隔两秒钟
//        String time3 = "*/2 * * * * ?";
//        //每隔两分钟
//        String time4 = "0 */2 * * * ?";
//        //获取天气状况
//        GetNetworkTimeAndWeather getNetworkTimeAndWeather = new GetNetworkTimeAndWeather();
//        GetNetworkTimeAndWeather getNetworkTimeAndWeather1 = new GetNetworkTimeAndWeather();
//        //获取灯光远程灯光状态
//        GetLightStatus getLightStatus = new GetLightStatus();
//        //执行指令
//        ExecInstruction execInstruction = new ExecInstruction();
//        //自动开关灯
//        AutoSwitchLight autoSwitchLight = new AutoSwitchLight();
//        try {
//            QuartzManager.addJob("getNetworkTimeAndWeather",getNetworkTimeAndWeather,time);
//            QuartzManager.addJob("getNetworkTimeAndWeather1",getNetworkTimeAndWeather1,time1);
//            QuartzManager.addJob("getLightStatus",getLightStatus,time2);
//            QuartzManager.addJob("autoSwitchLight",autoSwitchLight,time4);
//            QuartzManager.addJob("execInstruction",execInstruction,time3);
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
