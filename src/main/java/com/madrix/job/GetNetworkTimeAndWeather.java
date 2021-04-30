package com.madrix.job;

import com.madrix.pojo.LightControll;
import com.madrix.service.LightControllService;
import com.madrix.util.GetNetworkData;
import com.madrix.util.SpringContextUtil;
import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取网络上的日出日落与天气添加到数据库中
 * Created by sdc on 2018/2/28.
 */
public class GetNetworkTimeAndWeather implements Job {
    LightControllService lightControllService = SpringContextUtil.getBean("lightControllServiceImpl");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //获取指定URL地址的页面数据
            Document doc = GetNetworkData.getDoc(GetNetworkData.SUNRAISEANDSUNDOWNURL);
            //获取日出时间
            String sunraiseTime = GetNetworkData.getSunraise(doc);
            //获取日落时间
            String sunDownTime = GetNetworkData.getSunDown(doc);
            //获取天气
            doc = GetNetworkData.getDoc(GetNetworkData.WEATHERURL);
            String weather = GetNetworkData.getWeather(doc);

            //将网络上获取的数据添加到数据库
            LightControll lightControll = new LightControll();
            lightControll.setGetTime(new Date());
            lightControll.setRunraiseTime(sdf.parse(sunraiseTime));
            lightControll.setSundownTime(sdf.parse(sunDownTime));
            lightControll.setWeather(weather);
            lightControllService.insert(lightControll);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
