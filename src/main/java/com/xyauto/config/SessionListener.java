package com.xyauto.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session 管理器
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年11月2日 下午3:32:42
 */
public class SessionListener implements HttpSessionListener {

	final Integer SESSION_TIMEOUT = 60;// 单位s

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("==== Session is created ====");
		event.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("==== Session is destroyed ====");
	}
}