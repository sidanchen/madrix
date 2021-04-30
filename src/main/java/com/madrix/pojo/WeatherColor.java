package com.madrix.pojo;

/**
 *
 * Created by sdc on 2018/3/2.
 */
public class WeatherColor {
    private int id;
    private String weather;
    private String color;
    private String brightness;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }
}
