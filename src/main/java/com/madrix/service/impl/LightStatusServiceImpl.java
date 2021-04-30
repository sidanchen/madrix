package com.madrix.service.impl;

import com.madrix.dao.LightStatusDao;
import com.madrix.pojo.LightStatus;
import com.madrix.service.LightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdc on 2018/2/28.
 */
@Service
public class LightStatusServiceImpl implements LightStatusService {
    @Autowired
    LightStatusDao lightStatusDao;
    @Override
    public int insert(LightStatus lightStatus) {
        return lightStatusDao.insert(lightStatus);
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int delete(int[] id) {
        return 0;
    }

    @Override
    public int update(LightStatus lightStatus) {
        return lightStatusDao.update(lightStatus);
    }

    @Override
    public LightStatus findById(Integer id) {
        return null;
    }

    @Override
    public List<LightStatus> findByProperty(LightStatus lightStatus) {
        return null;
    }

    @Override
    public LightStatus findMaxId() {
        return lightStatusDao.findMaxId();
    }
}
