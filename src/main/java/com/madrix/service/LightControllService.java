package com.madrix.service;

import com.madrix.pojo.LightControll;

/**
 * Created by sdc on 2018/2/28.
 */
public interface LightControllService extends BaseService<LightControll> {

    /**
     * 判断当前时间是否为夜晚
     * @return
     */
    LightControll isNight();

    LightControll findMaxId();
    
    void getNetworkAndWeather();
}
