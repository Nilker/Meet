package com.xyauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author qiaom@xingyuanauto.com
 * @version	创建时间：2017年9月29日 下午5:16:24
 */
@SpringBootApplication
public class Run extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Run.class);
	}
}
