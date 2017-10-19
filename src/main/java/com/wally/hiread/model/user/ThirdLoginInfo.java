package com.wally.hiread.model.user;

public class ThirdLoginInfo {
	/*
	 * config
	 */
	private String type;
	private String authCodeUrl;
	private String apiUrl;
	private String appid;
	private String secret;
	private String redirectUri;
	/*
	 * authorize
	 */
	private String state;
	private String code;
	/*
	 * token info
	 */
	private String accessToken;
	private String expiresIn;
	private String refreshToken;
	private String openId;
	private String unionId;
	/*
	 * jsapi
	 */
	private String timeStamp;
	private String nonceStr;
	private String signature;
	private String jsApiAccessToken;
	private String jsApiTokenExpiresIn;
	private String jsApiTicket;
	private String jsApiTicketExpiresIn;
	//userinfo
	private ThirdLoginUserinfo userinfo;
	
	public String getJsApiTokenExpiresIn() {
		return jsApiTokenExpiresIn;
	}
	public void setJsApiTokenExpiresIn(String jsApiTokenExpiresIn) {
		this.jsApiTokenExpiresIn = jsApiTokenExpiresIn;
	}
	public String getJsApiTicketExpiresIn() {
		return jsApiTicketExpiresIn;
	}
	public void setJsApiTicketExpiresIn(String jsApiTicketExpiresIn) {
		this.jsApiTicketExpiresIn = jsApiTicketExpiresIn;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getJsApiAccessToken() {
		return jsApiAccessToken;
	}
	public void setJsApiAccessToken(String jsApiAccessToken) {
		this.jsApiAccessToken = jsApiAccessToken;
	}
	public String getJsApiTicket() {
		return jsApiTicket;
	}
	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}
	public String getAuthCodeUrl() {
		return authCodeUrl;
	}
	public void setAuthCodeUrl(String authCodeUrl) {
		this.authCodeUrl = authCodeUrl;
	}
	public ThirdLoginUserinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(ThirdLoginUserinfo userinfo) {
		this.userinfo = userinfo;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
}
