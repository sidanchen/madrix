package com.madrix.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpUtil {
	static Logger logger = Logger.getLogger(HttpUtil.class);

	public final static String devUrl = "http://localhost:8080/madrix/";
	public final static String runUrl = "";
	/**
	 * 发送请求并且获取服务器返回的数据并转换为String类型
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @data 2017年10月8日
	 * @author sdc
	 */
	public static String doGetString(String url)
			throws ClientProtocolException, IOException {

		logger.info("url:" + url);
		JSONObject jsonObject = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建httpget
		HttpGet httpGet = new HttpGet(url);

		// 发起请求
		CloseableHttpResponse response = client.execute(httpGet);
		// 获得请求结果
		HttpEntity entity = response.getEntity();
		// 将请求结果转为string类型
		String result = EntityUtils.toString(entity, "utf-8");
		return result;
	}

	/**
	 * 发送请求并且获取服务器返回的数据并转换为二进制
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @data 2017年10月8日
	 * @author sdc
	 */
	public static byte[] doGetByte(String url) throws ClientProtocolException,
			IOException {

		logger.info("url:" + url);
		JSONObject jsonObject = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建httpget
		HttpGet httpGet = new HttpGet(url);

		// 发起请求
		CloseableHttpResponse response = client.execute(httpGet);
		// 获得请求结果
		HttpEntity entity = response.getEntity();
		// 将请求结果转为string类型
		byte[] result = EntityUtils.toByteArray(entity);
		return result;
	}

	/**
	 * 发送post请求并且获取服务器返回的数据并转换为json格式
	 * 
	 * @param url
	 *            请求的地址
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @data 2017年10月8日
	 * @author sdc
	 */
	public static JSONObject doPostJson(String url, Map<String, String> map)
			throws ClientProtocolException, IOException {

		logger.info("url:" + url);
		JSONObject jsonObject = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建httpget
		HttpPost httpPost = new HttpPost(url);

		// 设置参数
		List<NameValuePair> param = new ArrayList<NameValuePair>();

		for (Entry<String, String> entry : map.entrySet()) {
			param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(
				param, "UTF-8");
		httpPost.setEntity(encodedFormEntity);

		// 发起请求
		CloseableHttpResponse response = client.execute(httpPost);
		// 获得请求结果
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 将请求结果转为string类型
			String result = EntityUtils.toString(entity, "utf-8");
			// 将string类型转为json类型
			jsonObject = JSONObject.fromObject(result);
		}
		httpPost.releaseConnection();
		return jsonObject;
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		String result = HttpUtil.doGetString("http://madrix.ngrok.io/RemoteCommands/?GetStoragePlaceLeft");
		System.out.println(result);
	}
}
