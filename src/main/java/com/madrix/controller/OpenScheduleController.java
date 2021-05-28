package com.madrix.controller;

import com.madrix.pojo.OpenSchedule;
import com.madrix.service.OpenScheduleService;
import com.madrix.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 提前开关灯任务表控制器
 * Created by sdc on 2018/2/28.
 */
@Controller
@RequestMapping("/openSchedule")
public class OpenScheduleController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenScheduleController.class);

    @Autowired
    OpenScheduleService openScheduleService;

    @RequestMapping("/query/{id}")
    @ResponseBody
    public String query(@PathVariable("id") int id) {
        try {
            return MessageUtil.mapToJsonString("success",openScheduleService.findById(id));
        } catch (Exception ex) {
            LOGGER.error("查询失败！", ex);
        }
        return MessageUtil.mapToJsonString("error","");
    }
    
    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestParam("id") int id,@RequestParam("beforeSunrise") int beforeSunrise, @RequestParam("afterSunrise") int afterSunrise,
                         @RequestParam("beforeSunset") int beforeSunset,@RequestParam("afterSunset") int afterSunset) {
        try {
            OpenSchedule openSchedule = new OpenSchedule();
            openSchedule.setId(id);
            openSchedule.setBeforeSunrise(beforeSunrise);
            openSchedule.setAfterSunrise(afterSunrise);
            openSchedule.setBeforeSunset(beforeSunset);
            openSchedule.setAfterSunset(afterSunset);
            openScheduleService.update(openSchedule);
            return MessageUtil.mapToJsonString("error","update success!");
        } catch (Exception ex) {
            LOGGER.error("修改失败！", ex);
        }
        return MessageUtil.mapToJsonString("error","update error!");
    }
    
}
