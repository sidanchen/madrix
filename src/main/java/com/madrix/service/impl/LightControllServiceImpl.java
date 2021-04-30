package com.madrix.service.impl;

import com.madrix.dao.LightControllDao;
import com.madrix.pojo.LightControll;
import com.madrix.service.LightControllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdc on 2018/2/28.
 */
@Service
public class LightControllServiceImpl implements LightControllService {
    @Autowired
    LightControllDao lightControllDao;
    @Override
    public int insert(LightControll lightControll) {
        return  lightControllDao.insert(lightControll);
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
    public int update(LightControll lightControll) {
        return 0;
    }

    @Override
    public LightControll findById(Integer id) {
        return null;
    }

    @Override
    public List<LightControll> findByProperty(LightControll lightControll) {
        return null;
    }


    @Override
    public LightControll isNight() {
        return lightControllDao.isNight();
    }
    @Override
    public LightControll findMaxId(){
        return  lightControllDao.findMaxId();
    }
}
