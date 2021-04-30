package com.madrix.job;

import com.madrix.pojo.LightStatus;
import com.madrix.service.LightStatusService;
import com.madrix.util.MadrixUtil;
import com.madrix.util.SpringContextUtil;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import java.util.Date;

/**
 * 获取madrix上的灯光状态
 * Created by sdc on 2018/3/1.
 */
public class GetLightStatus implements StatefulJob {
    Logger logger = Logger.getLogger(GetLightStatus.class);
    LightStatusService lightStatusService = SpringContextUtil.getBean("lightStatusServiceImpl");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            //获取最新的状态
            LightStatus lightStatus = lightStatusService.findMaxId();
            if(lightStatus != null){
                lightStatus.setStatusTime(new Date());
                lightStatus.setBrightness(MadrixUtil.getFadeValue());
                lightStatus.setColor(MadrixUtil.getStoragePlaceLeft());
                lightStatus.setOnOff(MadrixUtil.getBlackOut());
                //更新至数据库
                lightStatusService.update(lightStatus);
            }else{
                lightStatus = new LightStatus();
                lightStatus.setStatusTime(new Date());
                lightStatus.setBrightness(MadrixUtil.getFadeValue());
                lightStatus.setColor(MadrixUtil.getStoragePlaceLeft());
                lightStatus.setOnOff(MadrixUtil.getBlackOut());
                //添加至数据库
                lightStatusService.insert(lightStatus);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
        }
    }
}
