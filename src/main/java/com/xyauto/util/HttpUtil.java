package com.xyauto.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;

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
					log.debug("get new CloseableHttpAsyncClient");
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
			log.debug("CloseableHttpAsyncClient closed");
		}
	}
}
