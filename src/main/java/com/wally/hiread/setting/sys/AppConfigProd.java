package com.wally.hiread.setting.sys;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class AppConfigProd {
	
	@Bean
	public Properties serverConfig() {
		Logger log = Logger.getLogger(this.getClass());
		Properties prop=new Properties();
		try {
			String serverConfigPath = System.getProperty("hiread.server.config");
			log.info("server config path:"+serverConfigPath);
			InputStream in = new BufferedInputStream(new FileInputStream(serverConfigPath));
			prop.load(in);
		} catch (IOException e) {
			log.error("properties配置文件读取出错", e);
		}
		return prop;
		
	}
}
