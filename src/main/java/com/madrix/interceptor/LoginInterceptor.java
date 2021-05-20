package com.madrix.interceptor;

import com.madrix.controller.OperatorController;
import com.madrix.pojo.Operator;
import com.madrix.pojo.OperatorLog;
import com.madrix.service.OperatorLogService;
import com.madrix.service.OperatorService;
import com.madrix.util.MD5Util;
import com.madrix.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by sdc on 2018/2/26.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    OperatorService operatorService;
    
    @Autowired
    OperatorLogService operatorLogService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("interceptor method...");
        //String requestType = httpServletRequest.getHeader("X-Requested-With");
        HttpSession session = httpServletRequest.getSession();
        Operator operator = new Operator();
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        operator.setLoginName(userName);
        operator.setPassword(password);
        boolean flag = operatorService.findByProperty(operator).size() > 0;
        if (userName == null || userName.equals("") || password == null || password.equals("")) {
            flag = false;
        }
        if (flag) {
            //添加登录日志
            OperatorLog operatorLog = new OperatorLog();
            operatorLog.setOperatingTime(new Date());
            operatorLog.setOperator(userName);
            operatorLog.setOrder("Operator Login");
            operatorLogService.insert(operatorLog);
            Cookie uaerNameCookie = new Cookie("userName",userName);//创建一个cookie对象，name为cookie名字，value是cookie内容
            uaerNameCookie.setMaxAge(60*10);//设置cookie有效期，不设置有效期的话默认有效期为本次会话
            uaerNameCookie.setPath("/");//设置cookie保存路径
            httpServletResponse.addCookie(uaerNameCookie);//存入cookie
            Cookie passwordCookie = new Cookie("password",password);//创建一个cookie对象，name为cookie名字，value是cookie内容
            passwordCookie.setMaxAge(60*10);//设置cookie有效期，不设置有效期的话默认有效期为本次会话
            passwordCookie.setPath("/");//设置cookie保存路径
            httpServletResponse.addCookie(passwordCookie);//存入cookie
//                    session.setAttribute("password",password);
//                    session.setAttribute("userName",userName);
            session.setAttribute("user", operator);
        } 
//        Cookie[] cookies = httpServletRequest.getCookies();
//        String userName = "";
//        String password = "";
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("userName")) {
//                    userName = cookie.getValue();
//                }
//                if (cookie.getName().equals("password")) {
//                    password = cookie.getValue();
//                }
//            }
//        }
        
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user == null || "".equals(user)) {
//            if(requestType != null && "XMLHttpRequest".equals(requestType)){
//                httpServletResponse.setHeader("sessionstatus","timeout");
//                httpServletResponse.sendError(518, "session timeout.");
//                return false;
//            }

            httpServletResponse.sendRedirect("login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
