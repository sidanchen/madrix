package com.madrix.service;

import com.madrix.pojo.WeatherColor;

import java.util.List;

/**
 * Created by sdc on 2018/3/2.
 */
public interface WeatherColorService extends BaseService<WeatherColor> {
    /**
     * 查询所有的页数
     * @return
     */
    int findTotalPage();

    /**
     * 分页查询
     * @return
     */
    List<WeatherColor> findByPage(int page, int showNumber);

    List<WeatherColor> findByWeather(String weather);
}
