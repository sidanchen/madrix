package com.madrix.interceptor;

import com.madrix.util.MessageUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sdc on 2018/2/26.
 */
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("interceptor method...");
        //String requestType = httpServletRequest.getHeader("X-Requested-With");


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
