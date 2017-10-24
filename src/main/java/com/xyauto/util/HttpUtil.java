package com.xyauto.util;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * http工具类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年10月9日 下午2:28:16
 */
@Slf4j
public class HttpUtil {

	private static Object lock = new Object();
	private static CloseableHttpAsyncClient client;

	public static CloseableHttpAsyncClient getClient() {
		if (client == null) {
			synchronized (lock) {
				if (client == null) {
					client = HttpAsyncClients.custom().setMaxConnTotal(5).disableCookieManagement().build();
					log.debug(">> get new CloseableHttpAsyncClient");
				}
			}
		}
		if (!client.isRunning()) {
			client.start();
		}
		return client;
	}

	public static void closedHttp() {
		if (client != null) {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.debug(">> CloseableHttpAsyncClient closed");
		}
	}

	public static String httpForm(String url,Map<String, String> map)
			throws ParseException, IOException, InterruptedException, ExecutionException {
		StringBuilder sb = new StringBuilder();
		ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
			pairList.add(pair);
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		HttpPost post = new HttpPost(url);
		// HttpParams params = new BasicHttpParams();//
		// params = new BasicHttpParams();
		// HttpConnectionParams.setConnectionTimeout(params, 25000); // 连接超时
		// HttpConnectionParams.setSoTimeout(params, 25000); // 响应超时
		// post.setParams(params);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		HttpEntity entity = new UrlEncodedFormEntity(pairList, "UTF-8");
		post.setEntity(entity);
		CloseableHttpAsyncClient client = getClient();
		Future<HttpResponse> future = client.execute(post, null);
		HttpResponse tHttpResponse = null;
		tHttpResponse = future.get();
		return EntityUtils.toString(tHttpResponse.getEntity());
	}
}
