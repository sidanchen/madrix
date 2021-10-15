package com.madrix.controller;

import com.madrix.pojo.LightInstruction;
import com.madrix.pojo.LightStatus;
import com.madrix.pojo.Operator;
import com.madrix.pojo.OperatorLog;
import com.madrix.service.LightInstructionService;
import com.madrix.service.LightStatusService;
import com.madrix.service.OperatorLogService;
import com.madrix.util.MadrixUtil;
import com.madrix.util.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 指令下达控制器test
 * 用于下达的灯光操控指令都经由该控制器
 * Created by sdc on 2018/2/28.
 */
@Controller
public class LightInstrucationController {
    Logger logger = Logger.getLogger(LightInstrucationController.class);
    @Autowired
    LightInstructionService lightInstructionService;
    @Autowired
    LightStatusService lightStatusService;
    @Autowired
    OperatorLogService operatorLogService;

    /**
     * 接收用户下达的灯光控制指令
     * 1.从session中取登录用户的信息,没有就提示用户没有登录并且前台跳转到login.html页面中
     * 2.判断数据库中是否有未执行的相同的指令，如果有那么本次指令不储存到数据库中，并且返回success给用户,如果没有
     * 那么就储存指令到数据库中
     * 3.随着指令的下达更新灯光状态，如果数据库中有灯光状态信息那么就更新，没有的话首先从远程获取灯光状态并且设置
     * 指令更改的状态之后添加到数据库
     *
     * @param order
     * @param value
     * @param session
     * @return
     */
    @RequestMapping("/gi")
    @ResponseBody
    public String giveInstruction(@RequestParam("order") String order, @RequestParam(value = "value", required = false) String value, HttpSession session) {

        try {
//            Operator o = new Operator();
//            o.setLoginName("csd");
//            session.setAttribute("user",o);
            //从session中获取登录用户的信息
            Object obj = session.getAttribute("user");
                if (obj == null || "".equals(obj)) {
                return MessageUtil.mapToJsonString("notLogin", "user no login!");
            }
            Operator operator = (Operator) obj;
            if (operator.getLoginName() == null || "".equals(operator.getLoginName())) {
                return MessageUtil.mapToJsonString("notLogin", "user no login!");
            }

            //判断数据库中是否有未执行的相同的指令
            LightInstruction lightInstruction = new LightInstruction();
            lightInstruction.setOrder(order);
            lightInstruction.setValue(value);
            List<LightInstruction> lightInstructionList = lightInstructionService.findNoExec(lightInstruction);
            //数据库中还有未执行的相同的指令
            if (lightInstructionList != null && lightInstructionList.size() > 0) {
                //返回一个成功标志给用户
                return MessageUtil.mapToJsonString("success", "");
            }
            //设置操作员名称
            lightInstruction.setOperator(operator.getLoginName());


            //插入日志
            if("1".equals(order)){
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(),"Light trun on","",new Date());
                operatorLogService.insert(operatorLog);
            }else if("2".equals(order)){
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(),"Light trun off","",new Date());
                operatorLogService.insert(operatorLog);
            }else if("3".equals(order)){
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(),"Change Color",value,new Date());
                operatorLogService.insert(operatorLog);
            }else if("4".equals(order)){
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(),"Change Brightness",MadrixUtil.brightnessToPercent(value),new Date());
                operatorLogService.insert(operatorLog);
            }


            //将指令储存到数据库中
            lightInstructionService.insert(lightInstruction);
            //查询最新的灯光状态
            LightStatus lightStatus = lightStatusService.findMaxId();
            if(lightStatus != null) {
                //根据指令更新灯光状态
                if ("1".equals(order)) {
                    lightStatus.setOnOff("0");
                } else if ("2".equals(order)) {
                    lightStatus.setOnOff("1");
                } else if ("3".equals(order)) {
                    if(value != null) {
                        String[] s = value.split("P");
                        if(s.length >=1) {
                            lightStatus.setColor(s[1]);
                        }
                    }else{
                        lightStatus.setColor(value);
                    }
                } else {
                    lightStatus.setBrightness(value);
                }
                //更新到数据库中
                if (lightStatusService.update(lightStatus) > 0) {
                    return MessageUtil.mapToJsonString("success", "");
                } else {
                    return MessageUtil.serviceError();
                }
            }else{
                lightStatus = new LightStatus();
                lightStatus.setStatusTime(new Date());
                lightStatus.setBrightness(MadrixUtil.getFadeValue());
                lightStatus.setColor(MadrixUtil.getStoragePlaceLeft());
                lightStatus.setOnOff(MadrixUtil.getBlackOut());
                //添加至数据库
                lightStatusService.insert(lightStatus);
                return MessageUtil.mapToJsonString("success","");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
            return MessageUtil.serviceError();
        }
    }
}
