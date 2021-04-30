package com.madrix.pojo;

import java.util.Date;

/**
 * 自动灯光实体类
 * Created by sdc on 2018/2/28.
 */
public class LightControll {
    private int id;
    private Date getTime;
    private Date runraiseTime;
    private Date sundownTime;
    private String weather;

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRunraiseTime() {
        return runraiseTime;
    }

    public void setRunraiseTime(Date runraiseTime) {
        this.runraiseTime = runraiseTime;
    }

    public Date getSundownTime() {
        return sundownTime;
    }

    public void setSundownTime(Date sundownTime) {
        this.sundownTime = sundownTime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
