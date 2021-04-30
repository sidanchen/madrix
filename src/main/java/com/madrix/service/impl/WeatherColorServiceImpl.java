package com.madrix.service.impl;

import com.madrix.dao.WeatherColorDao;
import com.madrix.pojo.WeatherColor;
import com.madrix.service.WeatherColorService;
import com.madrix.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdc on 2018/3/2.
 */
@Service
public class WeatherColorServiceImpl implements WeatherColorService {
    @Autowired
    WeatherColorDao weatherColorDao;
    @Override
    public int insert(WeatherColor weatherColor) {
        return weatherColorDao.insert(weatherColor);
    }

    @Override
    public int delete(int id) {
        return weatherColorDao.delete(id);
    }

    @Override
    public int delete(int[] id) {
        return 0;
    }

    @Override
    public int update(WeatherColor weatherColor) {
        return 0;
    }

    @Override
    public WeatherColor findById(Integer id) {
        return weatherColorDao.findById(id);
    }

    @Override
    public List<WeatherColor> findByProperty(WeatherColor weatherColor){
        return weatherColorDao.findByProperty(weatherColor);
    }

    @Override
    public int findTotalPage() {
        return PageUtil.countPage(weatherColorDao.findTotal());
    }

    @Override
    public List<WeatherColor> findByPage(int page, int showNumber) {
        return weatherColorDao.findByPage(PageUtil.startRow(PageUtil.checkPage(page)),showNumber);
    }

    @Override
    public List<WeatherColor> findByWeather(String weather) {
        return weatherColorDao.findByWeather(weather);
    }
}
