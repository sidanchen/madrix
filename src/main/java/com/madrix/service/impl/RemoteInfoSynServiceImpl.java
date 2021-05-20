package com.madrix.service.impl;
/*
*
* COPYRIGHT. Shenzhen Qianhai Dianjiang Financial Tech Co., Ltd. 2017. 
* ALL RIGHTS RESERVED.
** No part of this publication may be reproduced, stored in a retrieval system, or transmitted,
* on any form or by any means, electronic, mechanical, photocopying, recording, 
 * or otherwise, without the prior written permission of Shenzhen Qianhai Dianjiang Financial Tech Co., Ltd.
*
* Amendment History:
* 
* Date                   By              Description
* -------------------    -----------     -------------------------------------------
* 2021/5/4    stan.c         Create the class
*/

import com.madrix.pojo.Operator;
import com.madrix.service.RemoteInfoSynService;
import com.madrix.util.HttpUtil;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Properties;

/**
 *@ClassName RemoteServiceImpl
 *@Description
 *@Author stan.c
 *@Date2021/5/4
 **/
@Service
public class RemoteInfoSynServiceImpl implements RemoteInfoSynService {

    private static String address;
    public static String sendEmail;
    public static String updateRemote;

    static {
        try {
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = RemoteInfoSynServiceImpl.class.getClassLoader().getResourceAsStream("remoteurl.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            address = properties.getProperty("address");
            sendEmail = properties.getProperty("sendEmail");
            updateRemote = properties.getProperty("updateRemote");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remoteInsertUserInfo(Operator operator) {
        if ("0".equals(updateRemote)) {
            return;
        }
        try {
            HttpUtil.doGetString(address + "/add?userName=" + operator.getLoginName() + "&password=" + operator.getPassword() + "&email=" + operator.getEmail());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void remoteUpdateUserInfo(String email, String password) {
        if ("0".equals(updateRemote)) {
            return;
        }
        try {
            
            HttpUtil.doGetString(address + "/remoteUpdatePassword?email=" + email + "&password=" + password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
