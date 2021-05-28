package com.madrix.util;

import com.madrix.pojo.OpenSchedule;
import com.madrix.service.LightControllService;
import com.madrix.service.OpenScheduleService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取网络上的数据
 * Created by sdc on 2018/2/28.
 */
public class GetNetworkData {

    //获取日出日落的网络链接
    public static final String SUNRAISEANDSUNDOWNURL = "https://www.timeanddate.com/sun/usa/annapolis";
    public static final String WEATHERURL = "https://www.timeanddate.com/worldclock/usa/annapolis";


    private static OpenScheduleService openScheduleService = SpringContextUtil.getBean("openScheduleServiceImpl");
    
    /**
     * 获取网页数据并且转换为Document类型
     * @param url
     * @return
     * @throws IOException
     */
    public static Document getDoc(String url) throws IOException {
        return (Document) Jsoup.connect(url).get();
    }

    /**
     * 获取日出时间
     * 1.格式为 yyyy-MM-dd HH:mm
     * @param doc
     * @return
     */
    public static String getSunraise(Document doc){
        OpenSchedule openSchedule = openScheduleService.findById(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sunrise = doc.getElementsByClass("dn-mob").get(1).text().split(" – ")[0];
        String dateStr = sdf.format(new Date()) + " " + convert(sunrise);
        try {
            //判断用户是否设置了提前开关灯的操作
            int i = 0;
            if (openSchedule.getBeforeSunrise() != 0) {
                i= 0-openSchedule.getBeforeSunrise();
            }
            if (openSchedule.getAfterSunrise() != 0) {
                i= openSchedule.getAfterSunrise();
            }
            return addMinutesDate(dateStr, i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取日落时间
     * 1.格式为 yyyy-MM-dd HH:mm
     * @param doc
     * @return
     */
    public static String getSunDown(Document doc){
        OpenSchedule openSchedule = openScheduleService.findById(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sunset = doc.getElementsByClass("dn-mob").get(1).text().split(" – ")[1].substring(0,5);
        String dateStr = sdf.format(new Date()) + " " + convert(sunset);
        try {
            //判断用户是否设置了提前开关灯的操作
            int i = 0;
            if (openSchedule.getBeforeSunset() != 0) {
                i= 0-openSchedule.getBeforeSunset();
            }
            if (openSchedule.getAfterSunset() != 0) {
                i= openSchedule.getAfterSunset();
            }
            return addMinutesDate(dateStr, i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 获取天气状况
     * @param doc
     * @return
     */
    public static String getWeather(Document doc){
        String html = doc.getElementById("curwt").html();
        String weather = "";
        //天气情况列表   晴朗  晴天  阴天 小雪  小雨 雨 多云  雾霾  雾天  雪天
        String[] weathers = {"clear","sunny","overcast","lightsnow","lightrain","rain","cloud","haze","fog","snow"};
        //循环遍历如果取到天气情况则返回数据
        for(String w : weathers){
            if(html.toLowerCase().contains(w)){
                return w;
            }
        }
        return weather;
    }


    /**
     * 转换时间
     * @param dateStr
     * @return
     */
    public static String convert(String dateStr) {
        int i = dateStr.indexOf(":");
        String s = dateStr.substring(0, i);
        int hour = Integer.parseInt(s);
        if (hour >= 12) {
            return dateStr;
        } else {
            String sHour = hour + 12 + "";
            return sHour
                    + dateStr.substring(dateStr.indexOf(":"));

        }
    }
    
    private static String addMinutesDate(String dateStr, int count) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(dateStr));
        calendar.add(Calendar.MINUTE, count);
        return simpleDateFormat.format(calendar.getTime());
    }
    
    public static void main(String[] args) {
        try {
            Document document = GetNetworkData.getDoc(SUNRAISEANDSUNDOWNURL);
            String s = getSunDown(document);
            System.out.println(s);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
