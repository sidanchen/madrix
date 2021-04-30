package com.madrix.dao;

import com.madrix.pojo.LightControll;

/**
 * 自动灯光dao类
 * Created by sdc on 2018/2/28.
 */
public interface LightControllDao extends BaseDao<LightControll>{

    /**
     * 判断当前时间是否为夜晚
     * @return
     */
    LightControll isNight();

    LightControll findMaxId();
}
