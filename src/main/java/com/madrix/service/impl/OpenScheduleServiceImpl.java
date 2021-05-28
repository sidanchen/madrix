package com.madrix.service.impl;

import com.madrix.dao.OpenScheduleDao;
import com.madrix.pojo.LightControll;
import com.madrix.pojo.OpenSchedule;
import com.madrix.service.LightControllService;
import com.madrix.service.OpenScheduleService;
import com.madrix.util.GetNetworkData;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sdc on 2018/2/28.
 */
@Service
public class OpenScheduleServiceImpl implements OpenScheduleService {
    
    @Autowired
    LightControllService lightControllService;

    @Autowired
    OpenScheduleDao openScheduleDao;

    @Override
    public int insert(OpenSchedule openSchedule) {
        return openScheduleDao.insert(openSchedule);
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
    public int update(OpenSchedule openSchedule) {
        int i = openScheduleDao.update(openSchedule);
        lightControllService.getNetworkAndWeather();
        return i;
    }

    @Override
    public OpenSchedule findById(Integer id) {
        return openScheduleDao.findById(id);
    }

    @Override
    public List<OpenSchedule> findByProperty(OpenSchedule openSchedule) {
        return null;
    }

}
