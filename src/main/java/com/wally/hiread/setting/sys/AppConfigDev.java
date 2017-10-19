package com.wally.hiread.setting.sys;

import com.wally.hiread.util.PropertyReader;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@Profile({"!production"})
public class AppConfigDev {
	
	@Bean
	public Properties serverConfig() {
		Logger log = Logger.getLogger(this.getClass());
		Properties prop=new Properties();
		InputStream in = PropertyReader.class.getResourceAsStream("/server_config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			log.error("properties配置文件读取出错", e);
		}
		return prop;
		
	}
}
