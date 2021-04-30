package com.madrix.job;

import com.madrix.pojo.LightInstruction;
import com.madrix.service.LightInstructionService;
import com.madrix.util.MadrixUtil;
import com.madrix.util.SpringContextUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import java.util.Date;
import java.util.List;

/**
 * 执行数据库中的指令
 * Created by sdc on 2018/3/1.
 */
public class ExecInstruction implements StatefulJob {
    LightInstructionService lightInstructionService = SpringContextUtil.getBean("lightInstructionServiceImpl");
    //测试用的灯光开关属性
    //public static String status = "0";
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            //获取数据库中的指令
            List<LightInstruction> lightInstructionList = lightInstructionService.findNoExecNoProperty();
            //执行指令
            for (LightInstruction lightInstruction : lightInstructionList) {
                if (lightInstruction != null) {
                    if ("1".equals(lightInstruction.getOrder())) {
                        //发送开关灯指令
                        try {
                            //获取灯光状态
                            String status = MadrixUtil.getBlackOut();
                            if(!"0".equals(status)) {
                                //开灯
                                MadrixUtil.switchLight();
                                System.out.println("开灯");
                                //status = "0";
                            }

                            //更新指令执行状态
                            lightInstruction.setExecTime(new Date());
                            lightInstructionService.update(lightInstruction);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else if("2".equals(lightInstruction.getOrder())){
                        //获取灯光状态
                        String status = MadrixUtil.getBlackOut();
                        if(!"1".equals(status)) {
                            //关灯
                            MadrixUtil.switchLight();
                            System.out.println("关灯");
                            //status = "1";

                        }

                        //更新指令执行状态
                        lightInstruction.setExecTime(new Date());
                        lightInstructionService.update(lightInstruction);

                    } else if ("3".equals(lightInstruction.getOrder())) {
                        try {

                            MadrixUtil.setColor(lightInstruction.getValue());
                            System.out.println("更改颜色为:" + lightInstruction.getValue());
                            //更新指令执行状态
                            lightInstruction.setExecTime(new Date());
                            lightInstructionService.update(lightInstruction);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if ("4".equals(lightInstruction.getOrder())) {
                        try {

                            MadrixUtil.setFadeValue(lightInstruction.getValue());
                            System.out.println("更改亮度为:" + lightInstruction.getValue());
                            //更新指令执行状态
                            lightInstruction.setExecTime(new Date());
                            lightInstructionService.update(lightInstruction);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
