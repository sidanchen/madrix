package com.madrix.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 用户处理服务器返回信息类 
 * 当用户需要自定义状态码与提示信息时可调用此类中的方法 
 * 该方法返回的是JSON字符串
 * @author sdc
 * @data 2017年10月24日
 */
public class MessageUtil {
	// 失败时返回的状态码
	public static final String STATUS_LOSE = "lose";
	// 成功时返回的状态码
	public static final String STATUS_SUCCESS = "success";
	// 服务器发生异常时返回的信息
	public static final String MSG_SERVICEERROR = "服务器发生异常，请联系管理员！";
	//
	public static final String MSG_SERVICESUCCESS = "请求成功！";

	/**
	 * 用户自定义返回信息
	 * 
	 * @param status
	 *            返回状态
	 * @param msg
	 *            返回信息
	 * @return
	 * @data 2017年10月24日
	 * @author sdc
	 */
	public static String mapToJsonString(String status, String msg) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		map.put("msg", msg);
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 用户自定义返回信息
	 * 
	 * @param status
	 *            状态码 可根据用户自己定义
	 * @param msg
	 *            需要提示的信息
	 * @return
	 * @data 2017年11月7日
	 * @author sdc
	 */
	public static String mapToJsonString(String status, Object msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("msg", msg);
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 
	* @method: mapToJsonStringStyle
	* @Description: TODO
	* @author xv
	* @date 2017年12月12日 下午3:30:09
	*
	* @param keyName_1
	 *            map的第一个key值
	 * @param value_1
	 *            map的第一个value值
	 * @param keyName_2
	 *            map的第二个key值
	 * @param value_2
	 *            map的第二个value值
	 *  @param keyName_3
	 *            map的第三个key值
	 * @param value_3
	 *            map的第三个value值
	 * @param keyName_4
	 *       	  map的第四个key值
	 * @param value_4
	 *            map的第四个value值
	* @return
	 */
	public static String mapToJsonStringStyle(String keyName_1, String value_1,
			String keyName_2, String value_2,String keyName_3, String value_3,String keyName_4, String value_4) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(keyName_1, value_1);
		map.put(keyName_2, value_2);
		map.put(keyName_3, value_3);
		map.put(keyName_4, value_4);
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 
	* @method: mapToJsonStringStyle
	* @Description: TODO
	* @author xv
	* @date 2017年12月12日 下午3:30:09
	*
	* @param keyName_1
	 *            map的第一个key值
	 * @param value_1
	 *            map的第一个value值
	 * @param keyName_2
	 *            map的第二个key值
	 * @param value_2
	 *            map的第二个value值
	 *  @param keyName_3
	 *            map的第三个key值
	 * @param value_3
	 *            map的第三个value值
	* @return
	 */
	public static String mapToJsonStringStyle(String keyName_1, String value_1,
			String keyName_2, String value_2,String keyName_3, String value_3) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(keyName_1, value_1);
		map.put(keyName_2, value_2);
		map.put(keyName_3, value_3);
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 用户自定义返回信息 用户可以自定义map key的值 用户可以自定义map value的值
	 * 该方法返回的status状态为success
	 * @param keyName_1
	 *            map的第一个key值
	 * @param value_1
	 *            map的第一个value值
	 * @param keyName_2
	 *            map的第二个key值
	 * @param value_2
	 *            map的第二个value值
	 * @return
	 * @data 2017年11月24日
	 * @author sdc
	 */
	public static String mapToJsonStringStyle(String keyName_1, Object value_1,
			String keyName_2, Object value_2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put(keyName_1, value_1);
		map.put(keyName_2, value_2);
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 用户自定义返回信息 用户可以自定义map key的值 用户可以自定义map value的值
	 * 
	 * @param keyName_1
	 *            map的第一个key值
	 * @param value_1
	 *            map的第一个value值
	 * @return
	 * @data 2017年11月24日
	 * @author sdc
	 */
	public static String mapToJsonStringStyle(String keyName_1, String value_1) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(keyName_1, value_1);
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 提示用户服务器内部错误
	 * 
	 * @return
	 * @data 2017年10月24日
	 * @author sdc
	 */
	public static String serviceError() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", STATUS_LOSE);
		map.put("msg", MSG_SERVICEERROR);
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 用户请求成功提示信息
	 * 
	 * @return
	 * @data 2017年11月24日
	 * @author sdc
	 */
	public static String serviceSuccess() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", STATUS_SUCCESS);
		map.put("msg", MSG_SERVICESUCCESS);
		return JSONObject.fromObject(map).toString();
	}

	public static String mapToJsonStringStyle(String keyName_1, String value_1, String keyName_2, String value_2,
			String keyName_3, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyName_1", value_1);
		map.put("keyName_2", value_2);
		map.put("keyName_3", obj);
		return JSONObject.fromObject(map).toString();
	}
	
}
