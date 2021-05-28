package com.madrix.pojo;
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
* 2021/5/22    stan.c         Create the class
*/

/**
 *@ClassName OpenSchedule
 *@Description 日出日落提前或者延后开关灯实体类
 *@Author stan.c
 *@Date2021/5/22
 **/
public class OpenSchedule {
    
    
    private int id;

    /**
     * 日出前
     */
    private int beforeSunrise;

    /**
     * 日出后
     */
    private int afterSunrise;

    /**
     * 日落前
     */
    private int beforeSunset;

    /**
     * 日落后
     */
    private int afterSunset;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeforeSunrise() {
        return beforeSunrise;
    }

    public void setBeforeSunrise(int beforeSunrise) {
        this.beforeSunrise = beforeSunrise;
    }

    public int getAfterSunrise() {
        return afterSunrise;
    }

    public void setAfterSunrise(int afterSunrise) {
        this.afterSunrise = afterSunrise;
    }

    public int getBeforeSunset() {
        return beforeSunset;
    }

    public void setBeforeSunset(int beforeSunset) {
        this.beforeSunset = beforeSunset;
    }

    public int getAfterSunset() {
        return afterSunset;
    }

    public void setAfterSunset(int afterSunset) {
        this.afterSunset = afterSunset;
    }
}
