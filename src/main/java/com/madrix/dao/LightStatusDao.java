package com.madrix.dao;

import com.madrix.pojo.LightStatus;

/**
 * Created by sdc on 2018/2/28.
 */
public interface LightStatusDao extends BaseDao<LightStatus>{
    LightStatus findMaxId();
}
