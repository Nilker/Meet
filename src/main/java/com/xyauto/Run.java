package com.xyauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

/**
 * 启动类
 * 
 * @author qiaom@xingyuanauto.com
 * @version 创建时间：2017年9月29日 下午5:16:24
 */
@Slf4j
@EnableCaching
@SpringBootApplication
@EnableScheduling
public class Run extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Run.class);
	}
	
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
		log.debug("CacheConfiguration.ehCacheCacheManager()");
		return new EhCacheCacheManager(bean.getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		log.debug("CacheConfiguration.ehCacheManagerFactoryBean()");
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/ehcache.xml"));
		cacheManagerFactoryBean.setShared(false);
		return cacheManagerFactoryBean;
	}
}
