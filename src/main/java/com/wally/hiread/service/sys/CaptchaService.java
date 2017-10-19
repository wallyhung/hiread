package com.wally.hiread.service.sys;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.wally.hiread.model.sys.CaptchaInfo;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.CaptchaUtil;

@Service
public class CaptchaService {
	
	private long codeGenIntervalMinImg=100;//图片验证码最小生成间隔
	private long codeGenIntervalMinSms=1000*90;//短信验证码最小生成间隔
	private long codeValidTime=1000*60*5;//验证码有效时间
	
	private CaptchaUtil cutil=new CaptchaUtil();
	
	public SR<String> codeGenValidate(String sessionKey,HttpSession session,String captchaType){
		SR<String> sr=new SR<String>(AppCons.SR_FAIL,"验证码生成校验未通过");
		CaptchaInfo info=(CaptchaInfo)session.getAttribute(sessionKey);
		if(info==null){
			sr.setStatus(AppCons.SR_SUCCESS);
			return sr;
		}
		if(!sessionKey.equals(info.getSessionKey())){
			sr.setMsg("校验码key不匹配");
			return sr;
		}
		Date lastGenTime = info.getLastGenTime();
		Date now=new Date();
		long codeGenIntervalMin=0;
		if(captchaType.equals(AppCons.CAPTCHA_TYPE_IMG)){
			codeGenIntervalMin=codeGenIntervalMinImg;
		}else if(captchaType.equals(AppCons.CAPTCHA_TYPE_SMS)){
			codeGenIntervalMin=codeGenIntervalMinSms;
		}
		long interval=now.getTime()-lastGenTime.getTime();
		if(interval<codeGenIntervalMin){
			sr.setMsg("验证码最小生成间隔为"+codeGenIntervalMin/1000+"秒,请稍后再试");
			return sr;
		}
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
	
	public String genCaptchaCode(String sessionKey,HttpSession session,String captchaType){
		String code="";
		if(captchaType.equals(AppCons.CAPTCHA_TYPE_IMG)){
			code = cutil.getCaptchaCode();
		}else if(captchaType.equals(AppCons.CAPTCHA_TYPE_SMS)){
			code = cutil.getMobileVerifyCode();
		}
		CaptchaInfo info=new CaptchaInfo();
		info.setCaptcha(code);
		info.setLastGenTime(new Date());
		info.setSessionKey(sessionKey);
		session.setAttribute(sessionKey, info);
		return code;
		
	}
	
	public SR<String> validateCode(String sessionKey,String code,HttpSession session){
		SR<String> sr=new SR<String>(AppCons.SR_FAIL,"验证码校验失败");
		CaptchaInfo info=(CaptchaInfo)session.getAttribute(sessionKey);
		if(info==null){
			return sr;
		}
		Date lastGenTime = info.getLastGenTime();
		Date now=new Date();
		long interval=now.getTime()-lastGenTime.getTime();
		if(interval>codeValidTime){
			sr.setMsg("验证码有效时间已过，请使用重新生成的验证码");
			return sr;
		}
		String codeInSession = info.getCaptcha();
		if(!codeInSession.toLowerCase().equals(code.toLowerCase())){
			return sr;
		}
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
	
}
