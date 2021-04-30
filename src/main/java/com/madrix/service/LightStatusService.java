package com.madrix.service;

import com.madrix.pojo.LightStatus;

/**
 * Created by sdc on 2018/2/28.
 */
public interface LightStatusService extends BaseService<LightStatus> {
    LightStatus findMaxId();
}
