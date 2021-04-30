package com.madrix.controller;

import com.madrix.pojo.Operator;
import com.madrix.pojo.OperatorLog;
import com.madrix.service.OperatorLogService;
import com.madrix.util.MessageUtil;
import com.madrix.util.PageUtil;
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
 * 操作日志控制器
 * Created by sdc on 2018/3/1.
 */
@Controller
public class OperatorLogController {
    Logger logger = Logger.getLogger(OperatorLogController.class);
    @Autowired
    OperatorLogService operatorLogService;

    @RequestMapping("/admin_log")
    public String adminLog(){
        OperatorLog operatorLog = new OperatorLog("Admin","View Log","",new Date());
        operatorLogService.insert(operatorLog);
        return "admin_log.html";
    }
    @RequestMapping("/operator_log")
    public String operatorLog(HttpSession session){
        try {
            Operator operator = (Operator) session.getAttribute("user");
            if(operator != null) {
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(), "View Log", "", new Date());
                operatorLogService.insert(operatorLog);
            }
            return "operator_log.html";
        }catch (Exception ex){
            return  MessageUtil.serviceError();
        }
    }

    /**
     * 管理员查看日志
     * @param page
     * @return
     */
    @RequestMapping("/albp")
    @ResponseBody
    public String adminLogByPage(@RequestParam("page") int page){
        try{

            List<OperatorLog> operatorLogList = operatorLogService.adminFindLogByPage(page, PageUtil.SHOW_NUMBER);
            return MessageUtil.mapToJsonString("success",operatorLogList);
        }catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.toString());
            return MessageUtil.serviceError();
        }
    }


    /**
     * 普通操作员查看日志
     * @param page
     * @param session
     * @return
     */
    @RequestMapping("/olbp")
    @ResponseBody
    public String operLogByPage(@RequestParam("page") int page,HttpSession session){
        try{
            Operator operator = (Operator) session.getAttribute("user");

            List<OperatorLog> operatorLogList = operatorLogService.operatorFindLogByPage(page, PageUtil.SHOW_NUMBER,operator.getLoginName());
            return MessageUtil.mapToJsonString("success",operatorLogList);
        }catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.toString());
            return MessageUtil.serviceError();
        }
    }



    /**
     * 管理员浏览日志的总页数
     * @return
     */
    @RequestMapping("/altp")
    @ResponseBody
    public String adminLogTotalPage(){
        try {
            int page = operatorLogService.adminLogTotalPage();
            return MessageUtil.mapToJsonString("success",page);
        }catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.toString());
            return MessageUtil.serviceError();
        }
    }

    /**
     * 操作员浏览日志的总页数
     * @return
     */
    @RequestMapping("/oltp")
    @ResponseBody
    public String operLogTotalPage(HttpSession session){
        try {
            Operator operator = (Operator) session.getAttribute("user");
            int page = operatorLogService.operatorLogTotalPage((operator.getLoginName()));
            if(operator != null) {
                OperatorLog operatorLog = new OperatorLog(operator.getLoginName(), "View Log", "", new Date());
                operatorLogService.insert(operatorLog);
            }
            return MessageUtil.mapToJsonString("success",page);
        }catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.toString());
            return MessageUtil.serviceError();
        }
    }
}
