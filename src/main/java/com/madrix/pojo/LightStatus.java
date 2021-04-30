package com.madrix.pojo;

import java.util.Date;

/**
 * 灯光状态类
 * Created by sdc on 2018/2/28.
 */
public class LightStatus {
    private int id;
    private Date statusTime;
    private String brightness;
    private String color;
    private String onOff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }
}
