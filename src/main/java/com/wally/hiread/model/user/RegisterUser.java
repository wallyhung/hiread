package com.wally.hiread.model.user;

public class RegisterUser {
	private String mobile;
	private String mobileVerifyCode;
	private String password;
	private String email;
	public String getMobileVerifyCode() {
		return mobileVerifyCode;
	}
	public void setMobileVerifyCode(String mobileVerifyCode) {
		this.mobileVerifyCode = mobileVerifyCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
