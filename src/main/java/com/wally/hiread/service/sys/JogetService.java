package com.wally.hiread.service.sys;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.HttpUtil;
import com.wally.hiread.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogetService {
	@Autowired
	SetupService setupService;
	@Autowired
	LogService logService;
	public SR<String> encrypt(String password){
		return jogetWs("encrypt","password",password);
	}
	public SR<String> decrypt(String password){
		return jogetWs("decrypt","password",password);
	}
	private SR<String> jogetWs(String serviceName,String paramName,String paramValue){
		SR<String> sr=new SR<String>();
		Setup setup = setupService.loadSetup();
		if(setup==null){
			logService.systemError(AppCons.SYSTEM_ERROR_SETTING,"系统设置未找到",null);
			sr.setStatus(AppCons.SR_FAIL);
			sr.setMsg("系统异常");
			return sr;
		}
		String validateKey = setup.getJogetWsKey();
		validateKey= MD5Util.string2MD5(validateKey);
		String url=setup.getJogetWsUrl();
		JSONObject params=new JSONObject();
		try {
			params.put("validateKey", validateKey);
			params.put("serviceName", serviceName);
			params.put(paramName, paramValue);
			SR<JSONObject> postResult = HttpUtil.jogetWsPost(url, params.toString());
			JSONObject entity = postResult.getEntity();
			if(entity==null||!entity.containsValue("status")){
				sr.setStatus(AppCons.SR_FAIL);
				logService.systemError(AppCons.SYSTEM_ERROR_SERVICE,"joget服务异常",null);
				sr.setMsg("系统异常");
				return sr;
			}
			String status=entity.getString("status");
			if(status.equals("fail")){
				sr.setStatus(AppCons.SR_FAIL);
				logService.systemError(AppCons.SYSTEM_ERROR_SERVICE,sr.getMsg(),null);
				sr.setMsg("系统异常");
				return sr;
			}
			String encryptedPassword=entity.getString("msg");
			sr.setStatus(AppCons.SR_SUCCESS);
			sr.setEntity(encryptedPassword);
		} catch (JSONException e) {
			sr.setStatus(AppCons.SR_FAIL);
			logService.systemError(AppCons.SYSTEM_ERROR_RUNTIME,e.getMessage(),e);
			sr.setMsg("系统异常");
			return sr;
		}
		return sr;
		
	}
}
