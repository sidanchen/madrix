package com.madrix.util;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

/**
 * 对madrix服务器的访问工具类
 * Created by sdc on 2018/3/1.
 */
public class MadrixUtil {
    //开发环境的主机名
    private static String devHost = "http://madrix.ngrok.io:8080/";
    //运行环境的主机名
    private static String runHost = "http://localhost/";

    /**
     * 获取颜色块的位置
     * @return
     * @data 2018年2月24日
     * @author sdc
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String getStoragePlaceLeft() throws ClientProtocolException, IOException
    {
        //拼接请求路径
        String url = runHost + "RemoteCommands/?GetStoragePlaceLeft";
        return HttpUtil.doGetString(url);
    }

    /**
     * 获取色块的二进制流
     * @return
     * @throws IOException
     */
    public static byte[] getStoragePlaceLeftByte(String imgName) throws IOException {
        //拼接请求路径
        String url = runHost + "RemoteCommands/GetStoragePlaceThumb.jpg=" + imgName;
        return HttpUtil.doGetByte(url);
    }
    /**
     * 获取灯光强度
     * @return
     * @data 2018年2月24日
     * @author sdc
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String getFadeValue() throws ClientProtocolException, IOException{
        //拼装请求
        String url = runHost + "RemoteCommands/?GetFadeValue";
        return HttpUtil.doGetString(url);
    }

    /**
     * 获取灯光的开关状态
     * @return
     * @data 2018年2月24日
     * @author sdc
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String getBlackOut() throws ClientProtocolException, IOException{
        //拼装请求
        String url = runHost + "RemoteCommands/?GetBlackout";
        return HttpUtil.doGetString(url);
    }

    /**
     * 开光灯请求
     * @throws IOException
     */
    public static void switchLight() throws IOException {
        //拼装请求
        String url = runHost + "RemoteCommands/?SetBlackoutToggle";
        HttpUtil.doGetString(url);
    }


    /**
     * 设置灯光的颜色
     * @param imgName
     * @throws IOException
     */
    public static void setColor(String imgName) throws IOException {
        String url = runHost + "RemoteCommands/?SetStorageLeft="+imgName;
        HttpUtil.doGetString(url);
    }

    public static void setFadeValue(String value) throws IOException {
        String url = runHost + "RemoteCommands/?SetFadeValue=" + value;
        HttpUtil.doGetString(url);
    }


    /**
     * 灯光强度参数转变为百分比
     * @return
     */
    public static String brightnessToPercent(String value){
        if("0".equals(value)){
            return "100%";
        }else if("30".equals(value)){
            return "90%";
        }else if("55".equals(value)){
            return "80%";
        }else if("80".equals(value)){
            return "70%";
        }else if("105".equals(value)){
            return "60%";
        }else if("130".equals(value)){
            return "50%";
        }else if("155".equals(value)){
            return "40%";
        }else if("180".equals(value)){
            return "30%";
        }else if("215".equals(value)){
            return "20%";
        }else if("250".equals(value)){
            return "10%";
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFadeValue());
    }
}
