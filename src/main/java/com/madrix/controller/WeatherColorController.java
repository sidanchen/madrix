package com.madrix.controller;

import com.madrix.pojo.Operator;
import com.madrix.pojo.OperatorLog;
import com.madrix.pojo.WeatherColor;
import com.madrix.service.OperatorLogService;
import com.madrix.service.WeatherColorService;
import com.madrix.util.MessageUtil;
import com.madrix.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by sdc on 2018/3/2.
 */
@Controller
public class WeatherColorController {

    @Autowired
    WeatherColorService weatherColorService;

    @Autowired
    OperatorLogService operatorLogService;

    /**
     * 查询所有页数
     * @return
     */
    @RequestMapping("/wtp")
    @ResponseBody
    public String wTotalPage(){
        try{
            return MessageUtil.mapToJsonString("success",weatherColorService.findTotalPage());
        }catch(Exception ex){
            ex.printStackTrace();
            return MessageUtil.serviceError();
        }
    }

    @RequestMapping("/wfbp")
    @ResponseBody
    public String wFindByPage(@RequestParam("page") int page){
        try{
            return MessageUtil.mapToJsonString("success",weatherColorService.findByPage(page, PageUtil.SHOW_NUMBER));
        }catch(Exception ex){
            ex.printStackTrace();
            return MessageUtil.serviceError();
        }
    }

    @RequestMapping("/wdbi")
    @ResponseBody
    public String wDelete(@RequestParam("id") int id,HttpSession session){
        try{
            WeatherColor weatherColor = weatherColorService.findById(id);

            //增加日志
            Operator operator = (Operator) session.getAttribute("user");
            if(operator != null) {
                if(weatherColor != null) {
                    OperatorLog operatorLog = new OperatorLog(operator.getLoginName(), "Delete Light Parameters", weatherColor.getWeather(), new Date());
                    operatorLogService.insert(operatorLog);
                }
            }
            //根据id删除
            weatherColorService.delete(id);

            return MessageUtil.mapToJsonString("success","Delete success!");
        }catch(Exception ex){
            ex.printStackTrace();
            return MessageUtil.serviceError();
        }
    }

    @RequestMapping("/weca")
    @ResponseBody
    public String wAdd(@RequestParam("weather") String weather, @RequestParam("color") String color, @RequestParam("brightness") String brightness, HttpSession session){
        try{
            WeatherColor weatherColor = new WeatherColor();
            weatherColor.setWeather(weather);

            List<WeatherColor> weatherColorList = weatherColorService.findByProperty(weatherColor);
            if(weatherColorList != null && weatherColorList.size() > 0){
                return MessageUtil.mapToJsonString("faild","Weather data already exist!");
            }

            weatherColor.setBrightness(brightness);
            weatherColor.setColor(color);
            Operator operator = (Operator) session.getAttribute("user");
            if(weatherColorService.insert(weatherColor) > 0){
                //增加日志
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(),"Add Light Parameters",weather,new Date());
                operatorLogService.insert(operatorLog);
                return MessageUtil.mapToJsonString("success","Add success!");
            }else {
                return  MessageUtil.serviceError();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return MessageUtil.serviceError();
        }
    }
}
