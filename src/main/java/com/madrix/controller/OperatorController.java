package com.madrix.controller;

import com.madrix.pojo.Operator;
import com.madrix.pojo.OperatorLog;
import com.madrix.service.OperatorLogService;
import com.madrix.service.OperatorService;
import com.madrix.util.*;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;


/**
 * 用户控制处理类
 * 该控制器用于专门接收处理有关用户操作的请求
 * Created by csd on 2018/2/26.
 */
@Controller
public class OperatorController {

    @Autowired
    OperatorService operatorService;
    @Autowired
    OperatorLogService operatorLogService;

    Logger logger = Logger.getLogger(OperatorController.class);

    /**
     * 用户登录方法
     * 用户提交用户名、密码、图形验证码等信息到后台之后，先判断用户名、密码、
     * 图形验证码是否为空且是否正确之后将密码加密成md5串，然后判断用户的身份
     * 是否为admin管理员账号，如果是那么去数据库匹配，如果用户名与加密后的密码
     * 能在数据库检测到那么将跳到后台管理界面，否则提示账号或者密码错误，如果
     * 不是admin账号那么就判断是否是操作员账号如果是那么跳转到灯光操作界面否则
     * 提示账号或者密码错误。
     *
     * @param userName
     * @param password
     * @param rCode
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, @RequestParam(value = "code") String rCode, HttpSession session) {
        //用户是否登录成功标记
        boolean flag = false;

        //判断用户提交的用户名或者密码是否为空
        if (userName == null || password == null || "".equals(userName) || "".equals(password)) {
            return MessageUtil.mapToJsonString("faild", "Username or password cannot be empty!");
        }
        //使用md5加密用户传递过来的密码
        String md5Password = MD5Util.getMD5(password);
        //获取图片验证码
        String code = session.getAttribute("code") != null ? session.getAttribute("code").toString() : "";

        //判断用户提交的验证码是否规范
        if (rCode == null || "".equals(rCode) || !code.equalsIgnoreCase(rCode)) {
            return MessageUtil.mapToJsonString("faild", "code cannot be empty!");
        }

        Operator operator = new Operator();
        operator.setLoginName(userName);
        operator.setPassword(md5Password);

        try {


            //管理员登录判断
            if ("admin".equals(userName)) {


                flag = operatorService.findByProperty(operator).size() > 0;
                if (flag) {
                    //添加登录日志
                    OperatorLog operatorLog = new OperatorLog();
                    operatorLog.setOperatingTime(new Date());
                    operatorLog.setOperator("Admin");
                    operatorLog.setOrder("Admin Login");
                    operatorLogService.insert(operatorLog);

                    session.setAttribute("user", operator);
                    return MessageUtil.mapToJsonString("successa", "login success");
                } else {
                    return MessageUtil.mapToJsonString("faild", "The user name or password is incorrect! \nPlease enter the correct user name and password,\n"
                            + "and if you forget the password. Please reset it!");
                }

            } else {
                flag = operatorService.findByProperty(operator).size() > 0;
                if (flag) {
                    //添加登录日志
                    OperatorLog operatorLog = new OperatorLog();
                    operatorLog.setOperatingTime(new Date());
                    operatorLog.setOperator(userName);
                    operatorLog.setOrder("Operator Login");
                    operatorLogService.insert(operatorLog);

                    session.setAttribute("user", operator);
                    return MessageUtil.mapToJsonString("success", "login success");
                } else {
                    return MessageUtil.mapToJsonString("faild", "The user name or password is incorrect! \nPlease enter the correct user name and password,\n"
                            + "and if you forget the password. Please reset it!");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addOperator(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("email") String email) {

        try {
            //判断用户提交的信息的真实性
            // 提示用户 用户名 密码 email 不能为空
            if (userName == null || password == null || email == null
                    || userName.equals("") || password.equals("")
                    || email.equals("")) {
                return MessageUtil.mapToJsonString("faild", "Username or password or email cannot be empty!");
            }
            //设置用户信息实体
            Operator operator = new Operator();
            operator.setLoginName(userName);
            operator.setPassword(MD5Util.getMD5(password));
            operator.setEmail(email.trim());

            //判断邮箱或者用户名是否存在数据库中，如果存在就提示用户
            List<Operator> operatorList = operatorService.userNameOrEmailIsExist(userName, email);
            if (operatorList != null && operatorList.size() > 0) {
                return MessageUtil.mapToJsonString("faild", "Username or email already exists!");
            }

            //添加用户信息到数据库
            if (operatorService.insert(operator) > 0) {

                OperatorLog operatorLog = new OperatorLog();
                operatorLog.setOrder("Add Operator");
                operatorLog.setOperator("Admin");
                operatorLog.setOperatingTime(new Date());
                operatorLog.setValue(userName);
                operatorLogService.insert(operatorLog);
                //发送邮件给新用户
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            MailUtil.sendMail(email.trim(), "This a mail from G5smart<br/>Congratulations!<br/>"
                                    + "  You've successfully become a member of G5smart!<br/>"
                                    + "Your username:<font style='font-size:20px;'>"
                                    + userName
                                    + "</font><br/>"
                                    + "Your password:<font style='font-size:20px;'>"
                                    + password
                                    + "</font><br/>"
                                    + "Please keep your username and password,<br/>"
                                    + "don’t release any information to others.<br/>"
                                    + "If you forget the password, please reset by sending an e-mail to the following mailbox", "Registration Successful!");
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();


                return MessageUtil.mapToJsonString("success", "The user account has been added successfully!\n "
                        + "The user name and password have been sent to the mailbox: "
                        + email.trim()
                        + ",If you do not find the mail in the inbox, please go to the trash to see, thank you!");
            } else {
                return MessageUtil.mapToJsonString("faild", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("OperatorController.addOperator:" + ex.toString());
        }
        return "";
    }

    @RequestMapping("/fubp")
    @ResponseBody
    public String findUserByPage(@RequestParam("page") int page) {
        try {
            return MessageUtil.mapToJsonString("success", operatorService.findUserByPage(page, PageUtil.SHOW_NUMBER));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("OperatorController.findUserByPage:" + ex.toString());
            return MessageUtil.serviceError();
        }
    }

    /**
     * 获取总页数
     *
     * @return
     */
    @RequestMapping("/totalpages")
    @ResponseBody
    public String totalPages() {
        try {
            int totalRow = operatorService.count();
            int page = PageUtil.countPage(totalRow);
            return MessageUtil.mapToJsonString("success", page);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("OperatorController.totalPages:" + ex.toString());
            return MessageUtil.serviceError();
        }
    }

    /**
     * 根据id删除用户记录
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") int id) {
        try {
            Operator operator = operatorService.findById(id);
            if(operator != null) {

                OperatorLog operatorLog = new OperatorLog("Admin","Delete Operator",operator.getLoginName(),new Date());
                operatorLogService.insert(operatorLog);
            }
            if (operatorService.delete(id) > 0) {

                return MessageUtil.mapToJsonString("success", "User account deletion succeeded!");
            } else {
                return MessageUtil.mapToJsonString("faild", "DELETE FAILED!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("OperatorController.delete:" + ex.toString());
            return MessageUtil.serviceError();
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        //添加登录日志
        OperatorLog operatorLog = new OperatorLog("Admin","Admin Logout","",new Date());
        operatorLogService.insert(operatorLog);
        session.invalidate();
        return "redirect:/login.html";
    }

    @RequestMapping("/oexit")
    public String aexit(HttpSession session) {
        Operator operator = (Operator) session.getAttribute("user");
        if(operator != null) {
            //添加登录日志
            OperatorLog operatorLog = new OperatorLog(operator.getLoginName(), "Operator Logout", "", new Date());
            operatorLogService.insert(operatorLog);
        }
        session.invalidate();
        return "redirect:/login.html";
    }

    /**
     * 判断用户是否登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/il")
    @ResponseBody
    public String isLogin(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null || "".equals(user)) {
            return MessageUtil.mapToJsonString("faild", "no login");
        }
        return MessageUtil.mapToJsonString("success", "already login");
    }


    /**
     * 发送修改密码的邮件到用户邮箱
     * 1.先判断用户提交的用户名是否存在数据库
     * 2.生成uuid和过期时间存到数据库中
     * 3.发送邮件至用户邮箱
     *
     * @param userName
     * @return
     */
    @RequestMapping("/supe")
    @ResponseBody
    public String sendUpdatePasswordEmail(@RequestParam("userName") String userName) {
        try {
            //验证用户是否存在
            List<Operator> operatorList = operatorService.userNameOrEmailIsExist(userName, "");
            if (operatorList == null || operatorList.size() <= 0) {
                return MessageUtil.mapToJsonString("faild", "User name does not exist!");
            }
            Operator operator = operatorList.get(0);

            //生成uuid
            String uuid = UUID.randomUUID();

            //更新uuid和当前时间到数据库便于到时候验证链接
            operator.setUuid(uuid);
            operator.setValidTime(new Date());

            if (operatorService.update(operator) <= 0) {
                //提示用户生成链接失败
                return MessageUtil.serviceError();
            }

            //发送密码修改邮件给用户

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MailUtil.sendMail(operator.getEmail(), "This a mail from G5smart:<br/> "
                                + "  Please reset your password,follow this link:<a href='" + HttpUtil.devUrl + "checkUrl?uuid="
                                + uuid + "&email=" + operator.getEmail() + "'>" + "" + HttpUtil.devUrl + "checkUrl?uuid=" + uuid
                                + "&email=" + operator.getEmail() + "</a>", "Change Password");
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();

            return MessageUtil.mapToJsonString("success", "The link to the reset password has been sent to the mailbox:"
                    + operator.getEmail()
                    + ",If you do not find the mail in the inbox, please go to the trash to see, thank you!");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("OperatorController.sendUpdatePasswordEmail:" + ex.toString());
            return MessageUtil.serviceError();
        }
    }

    @RequestMapping("/up")
    @ResponseBody
    public String updatePassword(HttpSession session, @RequestParam("password") String passwrd) {
        try {
            if (passwrd == null || "".equals(passwrd)) {
                return MessageUtil.mapToJsonString("faild", "Password cannot be empty!");
            }
            //从session中获取邮箱账号
            Object email = session.getAttribute("email");
            if (email == null || "".equals(email)) {
                return MessageUtil.serviceError();
            }

            //判断用户邮箱是否存在数据库
            List<Operator> operatorList = operatorService.userNameOrEmailIsExist("", email.toString());
            if (operatorList == null || operatorList.size() <= 0) {
                return MessageUtil.mapToJsonString("faild", "Email does not exist!");
            }

            Operator operator = operatorList.get(0);
            operator.setPassword(MD5Util.getMD5(passwrd));

            if (operatorService.update(operator) > 0) {
                return MessageUtil.mapToJsonString("success", "update success!");
            } else {
                return MessageUtil.serviceError();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
            return MessageUtil.serviceError();
        }
    }

    /**
     * 验证修改密码的连接是否有效
     * 1.判断链接中的邮箱是否正确
     * 2.判断链接中的uuid是否和数据库中的匹配
     * 3.判断链接是否过期
     * 4.储存邮箱至session中
     * 5.转到修改密码页面
     *
     * @param uuid
     * @param email
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping("/checkUrl")
    public void checkUrl(@RequestParam("uuid") String uuid, @RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            //判断邮箱是否正确
            List<Operator> operatorList = operatorService.userNameOrEmailIsExist("", email);
            if (operatorList == null || operatorList.size() <= 0) {
                response.getWriter().print("<h1>Invalid link</h1>");
                return;
            }
            Operator operator = operatorList.get(0);
            //验证uuid是否与数据库匹配
            if (uuid == null || "".equals(uuid) || !uuid.equals(operator.getUuid())) {
                response.getWriter().print("<h1>Invalid link</h1>");
                return;
            }
            if (System.currentTimeMillis() - operator.getValidTime().getTime() >= 1000 * 60 * 30) {
                response.getWriter().print("<h1>Invalid link</h1>");
                return;
            }
            //将email存储到session中
            session.setAttribute("email", email);

            request.getRequestDispatcher("WEB-INF/views/find.html").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
            try {
                response.getWriter().print("<h1>Invalid link</h1>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 用于输出登录时的图形验证码
     *
     * @param response
     * @throws IOException
     * @author sdc
     */
    @RequestMapping("/picturecode")
    public void loginPictureCode(HttpServletResponse response,
                                 HttpServletRequest request) throws IOException {
        // 清除网页缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        // 把生成的验证码的字符串存到session中
        session.setAttribute("codeInvalid", System.currentTimeMillis());
        session.setAttribute("code", verifyCode.toLowerCase());
        //System.out.println("code： " + verifyCode.toLowerCase());
        // 生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(),
                verifyCode);
    }
}