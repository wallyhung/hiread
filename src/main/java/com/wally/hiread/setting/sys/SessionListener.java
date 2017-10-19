package com.wally.hiread.setting.sys;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	@Autowired
    Environment env;
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String timeout = env.getProperty("hiread.timeout");
		int timeoutInt = Integer.parseInt(timeout);
		se.getSession().setMaxInactiveInterval(timeoutInt*60);
		
//		Date now=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		log.info("现在时间是:"+sdf.format(now));
//		log.info("session是否是新创建的:"+se.getSession().isNew());
//		Date sessionCreatTime=new Date(se.getSession().getCreationTime());
//		log.info("session的创建时间是:"+sdf.format(sessionCreatTime));
//		log.info("session的无操作最大间隔为:"+se.getSession().getMaxInactiveInterval()+"秒");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

//		log.info("session destroyed");
	}

}
