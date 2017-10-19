package com.wally.hiread.service.sys;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wally.hiread.setting.sys.AppCons;

@Service
public class LogService {
	private Logger log = Logger.getLogger(this.getClass());
	
	public void systemError(String errorType,String errorMsg,Exception ex){
		if(AppCons.SYSTEM_ERROR_SETTING.equals(errorType)){
			log.info(AppCons.SYSTEM_ERROR_SETTING+":"+errorMsg);
		}else if(AppCons.SYSTEM_ERROR_SERVICE.equals(errorType)){
			log.info(AppCons.SYSTEM_ERROR_SERVICE+":"+errorMsg);
		}else if(AppCons.SYSTEM_ERROR_RUNTIME.equals(errorType)){
			log.error(AppCons.SYSTEM_ERROR_RUNTIME+":"+errorMsg,ex);
		}else{
			log.info(errorType+"是不支持的系统异常类型");
		}
		
	}
	
	public void info(String info){
		log.info(info);
	}
	
}
