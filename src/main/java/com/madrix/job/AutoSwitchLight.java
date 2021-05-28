package com.madrix.job;

import com.madrix.pojo.*;
import com.madrix.service.*;
import com.madrix.util.MadrixUtil;
import com.madrix.util.SpringContextUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 自动开关灯工作类
 * Created by sdc on 2018/3/2.
 */
public class AutoSwitchLight implements StatefulJob{
    LightControllService lightControllService = SpringContextUtil.getBean("lightControllServiceImpl");
    OperatorLogService operatorLogService = SpringContextUtil.getBean("operatorLogServiceImpl");
    WeatherColorService weatherColorService = SpringContextUtil.getBean("weatherColorServiceImpl");
    LightInstructionService lightInstructionService = SpringContextUtil.getBean("lightInstructionServiceImpl");
    private static boolean onFlag = true;
    private static boolean offFlag = true;
    
    @Autowired
    OpenScheduleService openScheduleService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LightControll lightControll = lightControllService.isNight();
            String lightStatus = MadrixUtil.getBlackOut();
            //String lightStatus = "0";
            if (lightControll != null){
                if(!offFlag){
                    return;
                }
                //白天
                if("0".equals(lightStatus)){
                    //关灯操作下达至指令表
                    LightInstruction lightInstruction = new LightInstruction();
                    lightInstruction.setOrder("2");
                    lightInstruction.setOperator("System");
                    lightInstructionService.insert(lightInstruction);

                    //插入日志
                    OperatorLog operatorLog = new OperatorLog("System","Light trun off","",new Date());
                    operatorLogService.insert(operatorLog);
                }
                offFlag = false;
                onFlag = true;
            }else{
                if(!onFlag){
                    return;
                }
                //黑夜
                if("1".equals(lightStatus)){
                    //开灯操作
                    //关灯操作下达至指令表
                    LightInstruction lightInstruction = new LightInstruction();
                    lightInstruction.setOrder("1");
                    lightInstruction.setOperator("System");
                    lightInstructionService.insert(lightInstruction);

                    //插入日志
                    OperatorLog operatorLog = new OperatorLog("System","Light trun on","",new Date());
                    operatorLogService.insert(operatorLog);

                    //根据天气状况更改颜色亮度
                    lightControll = lightControllService.findMaxId();

                    if(lightControll != null) {
                        //查询用户定义的天气参数
                        List<WeatherColor> weatherColorList = weatherColorService.findByWeather(lightControll.getWeather());
                        if(weatherColorList != null && weatherColorList.size() > 0){
                            String color = weatherColorList.get(0).getColor();
                            String brightness = weatherColorList.get(0).getBrightness();

                            //插入指令
                            lightInstruction.setOrder("3");
                            lightInstruction.setValue(color);
                            lightInstruction.setOperator("System");
                            lightInstructionService.insert(lightInstruction);

                            //插入指令
                            lightInstruction.setOrder("4");
                            lightInstruction.setValue(brightness);
                            lightInstruction.setOperator("System");
                            lightInstructionService.insert(lightInstruction);


                            //插入日志
                            operatorLog = new OperatorLog("System","Change Color",color,new Date());
                            operatorLogService.insert(operatorLog);

                            operatorLog = new OperatorLog("System","Change Brightness",MadrixUtil.brightnessToPercent(brightness),new Date());
                            operatorLogService.insert(operatorLog);
                        }
                    }

                }
                offFlag = true;
                onFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
