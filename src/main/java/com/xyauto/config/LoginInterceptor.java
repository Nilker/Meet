package com.xyauto.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyauto.pojo.User;
import com.xyauto.util.Constants;
import com.xyauto.util.CookieUtil;
import com.xyauto.util.HttpUtil;
import com.xyauto.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录过滤器
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年9月29日 下午5:21:00
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		log.debug(url);
		if (url.endsWith("/***/***")) {
			return true;
		}
		HttpSession session = request.getSession();
		long preTime = System.currentTimeMillis();
		User user = getLoginUser(request);
		if (null == user) {
			session.invalidate();
			response.sendRedirect(Constants.OA_LOGIN_URL);
			return false;
		} else {
			session.setAttribute(Constants.SESSION_USER, user);
			log.debug("login " + user.toString());
		}
		long postTime = System.currentTimeMillis();
		log.debug("Login Interceptor time:" + (postTime - preTime) + "ms");
		return true;
	}

	private User getLoginUser(HttpServletRequest request)
			throws InterruptedException, ExecutionException, ParseException, IOException {

		String cookieValue = CookieUtil.getCookieValue(request, Constants.COOKIE_KEY);
		if (null == cookieValue || StringUtil.isEmpty(cookieValue)) {
			log.debug("cookie not found");
			return null;
		}
		log.debug("cookie:" + cookieValue);
		HttpGet httpGet = new HttpGet(Constants.GET_OA_USER);
		httpGet.addHeader("Cookie", Constants.COOKIE_KEY + "=" + cookieValue);
		CloseableHttpAsyncClient client = HttpUtil.getClient();
		Future<HttpResponse> future = client.execute(httpGet, null);
		HttpResponse httpResponse = future.get();
		String res = EntityUtils.toString(httpResponse.getEntity());
		log.debug(res);

		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		HashMap<String, HashMap<String, Object>> resMap = objectMapper.readValue(res, HashMap.class);
		HashMap<String, Object> dataMap = resMap.get("Data");

		User user = new User();
		user.setUserId((Integer) dataMap.get("EmpId"));
		user.setUserName((String) dataMap.get("DomainNum"));
		user.setEmployeeId((String) dataMap.get("EmpNumber"));
		user.setEmployeeName((String) dataMap.get("Name"));
		user.setDepartmentId(((Integer) dataMap.get("DepartmentId")).toString());
		user.setDepartmentName((String) dataMap.get("DepartmentName"));
		user.setIcon((String) dataMap.get("Icon"));
		user.setIsLogin(true);

		return user;
	}
}
