package com.wally.hiread.model.sys;

import java.util.Date;

public class CaptchaInfo {
	
	private String sessionKey;
	private String captcha;
	private Date lastGenTime;
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Date getLastGenTime() {
		return lastGenTime;
	}
	public void setLastGenTime(Date lastGenTime) {
		this.lastGenTime = lastGenTime;
	}
}
