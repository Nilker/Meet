package com.xyauto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 过滤器队列
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年9月29日 下午5:27:22
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(localInterceptor());
//	}
}
