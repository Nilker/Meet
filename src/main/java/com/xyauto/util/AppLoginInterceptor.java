package com.xyauto.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyauto.pojo.User;

import lombok.extern.slf4j.Slf4j;

/**
 * app验证登陆工具类
 * 
 * @author xusy
 */
@Slf4j
public class AppLoginInterceptor {

	public static boolean checkLogin(HttpServletRequest request) throws Exception {
		String url = request.getRequestURL().toString();
		log.debug(url);
		HttpSession session = request.getSession();
		long preTime = System.currentTimeMillis();
		User user = getLoginUser(request);
		if (null == user) {
			return false;
		} else {
			session.setAttribute(Constants.SESSION_APPUSER, user);
			log.debug("login " + user.toString());
		}
		long postTime = System.currentTimeMillis();
		log.debug("Login Interceptor time:" + (postTime - preTime) + "ms");
		return true;
	}

	private static User getLoginUser(HttpServletRequest request)
			throws InterruptedException, ExecutionException, ParseException, IOException {

		String cookieValue = CookieUtil.getCookieValue(request, Constants.COOKIE_KEY);
		if (null == cookieValue || StringUtil.isEmpty(cookieValue)) {
			log.debug("cookie not found");
			return null;
		}
		log.debug("cookie:" + cookieValue);

		HttpGet httpCheckGet = new HttpGet(Constants.GET_OA_CHECK);
		httpCheckGet.addHeader("Cookie", Constants.COOKIE_KEY + "=" + cookieValue);
		CloseableHttpAsyncClient client = HttpUtil.getClient();
		Future<HttpResponse> future = client.execute(httpCheckGet, null);
		HttpResponse httpResponse = future.get();

		String res = EntityUtils.toString(httpResponse.getEntity());
		log.debug("check:" + res);
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> checkMap = objectMapper.readValue(res, HashMap.class);
		Boolean isLogin = (boolean) checkMap.get("Result");
		if (!isLogin)
			return null;

		HttpGet httpUserGet = new HttpGet(Constants.GET_OA_USER);
		httpUserGet.addHeader("Cookie", Constants.COOKIE_KEY + "=" + cookieValue);
		client = HttpUtil.getClient();
		future = client.execute(httpUserGet, null);
		httpResponse = future.get();

		res = EntityUtils.toString(httpResponse.getEntity());
		log.debug("user:" + res);
		if (res.indexOf("{\"Status\":401") == 0)
			return null;
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
