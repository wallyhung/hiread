package com.wally.hiread.setting.sys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionListener;


@Configuration
public class BeanConfig {

	@Bean
	public HttpSessionListener httpSessionListener(){
		HttpSessionListener li=new SessionListener();
		return li;
	}
	


}
