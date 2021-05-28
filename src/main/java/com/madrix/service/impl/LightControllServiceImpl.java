package com.madrix.service.impl;

import com.madrix.dao.LightControllDao;
import com.madrix.pojo.LightControll;
import com.madrix.service.LightControllService;
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

    @Override
    public void getNetworkAndWeather() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //获取指定URL地址的页面数据
            Document doc = GetNetworkData.getDoc(GetNetworkData.SUNRAISEANDSUNDOWNURL);
            //获取日出时间
            String sunraiseTime = GetNetworkData.getSunraise(doc);
            //获取日落时间
            String sunDownTime = GetNetworkData.getSunDown(doc);
            //获取天气
            doc = GetNetworkData.getDoc(GetNetworkData.WEATHERURL);
            String weather = GetNetworkData.getWeather(doc);

            //将网络上获取的数据添加到数据库
            LightControll lightControll = new LightControll();
            lightControll.setGetTime(new Date());
            lightControll.setRunraiseTime(sdf.parse(sunraiseTime));
            lightControll.setSundownTime(sdf.parse(sunDownTime));
            lightControll.setWeather(weather);
            insert(lightControll);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
