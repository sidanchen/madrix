package com.madrix.dao;

import com.madrix.pojo.WeatherColor;

import java.util.List;

/**
 * Created by sdc on 2018/3/2.
 */
public interface WeatherColorDao extends BaseDao<WeatherColor> {

    /**
     * 查询所有的行数
     * @return
     */
    int findTotal();

    /**
     * 分页查询
     * @return
     */
    List<WeatherColor> findByPage(int startRow, int showNumber);

    List<WeatherColor> findByWeather(String weather);
}
