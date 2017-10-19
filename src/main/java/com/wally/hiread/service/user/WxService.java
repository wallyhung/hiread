package com.wally.hiread.service.user;

import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.ThirdLoginInfo;
import com.wally.hiread.model.user.ThirdLoginUserinfo;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserExample;
import com.wally.hiread.model.user.UserExample.Criteria;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.HttpUtil;

@Service
public class WxService {
	@Autowired
	Properties serverConfig;
	@Autowired 
	LogService logService;
	@Autowired
	UserMapper userMapper;
	//初始化
	public ThirdLoginInfo initLoginInfo(){
		ThirdLoginInfo info=new ThirdLoginInfo();
		info.setType(AppCons.THIRD_LOGIN_TYPE_WX);
		info.setAuthCodeUrl("https://open.weixin.qq.com/connect/qrconnect");
		info.setApiUrl("https://api.weixin.qq.com");
		info.setAppid(serverConfig.getProperty("app.wx.appid"));
		info.setSecret(serverConfig.getProperty("app.wx.secret"));
		info.setRedirectUri(serverConfig.getProperty("app.wx.redirect_uri"));
		return info;
	}
	
	//第一步：请求CODE(临时票据)
	public SR<String> requestLoginUrl(ThirdLoginInfo info){
		SR<String> sr=new SR<String>(AppCons.SR_FAIL,"微信登录请求出错");
		String appid=info.getAppid();
		String redirect_uri=info.getRedirectUri();
		String state=info.getState();
		
		String url="";
		try{
		    url=info.getAuthCodeUrl()+"?"
				+"appid="+appid+"&"
				+"redirect_uri="+URLEncoder.encode(redirect_uri,"utf-8")+"&"
				+"response_type=code&"
				+"scope=snsapi_login&"
				+"state="+URLEncoder.encode(state,"utf-8")+"#wechat_redirect";
		}catch(Exception ex){
			logService.systemError(AppCons.SYSTEM_ERROR_RUNTIME, ex.getMessage(), ex);
			sr.setMsg("微信登录请求code出错:"+ex.getMessage());
			return sr;
		}
		sr.setEntity(url);
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
				
	}
	//第二步：通过code获取并设置access_token
	public SR setAccessTokenByCode(ThirdLoginInfo info){
		SR sr=new SR(AppCons.SR_FAIL,"微信登录获取token出错");
		String url=info.getApiUrl()+"/sns/oauth2/access_token"
		                             +"?appid="+info.getAppid()
		                             + "&secret="+info.getSecret()
		                             + "&code="+info.getCode()
		                             + "&grant_type=authorization_code";
		try{
			String result = HttpUtil.get(url);
			JSONObject json= JSON.parseObject(result);
			if(!json.containsValue("access_token")||
					!json.containsValue("expires_in")||
					!json.containsValue("refresh_token")||
					!json.containsValue("openid")||
					!json.containsValue("unionid")){
				return sr;
			}
			info.setAccessToken(json.getString("access_token"));
			info.setExpiresIn(json.getString("expires_in"));
			info.setRefreshToken(json.getString("refresh_token"));
			info.setOpenId(json.getString("openid"));
			info.setUnionId(json.getString("unionid"));
		}catch(Exception ex){
			logService.systemError(AppCons.SYSTEM_ERROR_SERVICE, "微信登录获取token出错", ex);
			return sr;
		}
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
	
	//第三步：通过token获取并设置userinfo
	public SR setUserInfo(ThirdLoginInfo info){
		SR sr=new SR(AppCons.SR_FAIL,"微信登录获取个人信息出错");
		String url=info.getApiUrl()+"/sns/userinfo"
		                             +"?access_token="+info.getAccessToken()
		                             + "&openid="+info.getOpenId();
		try{
			String result = HttpUtil.get(url);
			JSONObject json= JSON.parseObject(result);
			if(!json.containsValue("nickname")||
					!json.containsValue("headimgurl") ||
					!json.containsValue("openid") ||
					!json.containsValue("unionid")){
				return sr;
			}
			ThirdLoginUserinfo userinfo=new ThirdLoginUserinfo();
			userinfo.setType(info.getType());
			userinfo.setNickname(json.getString("nickname"));
			userinfo.setHeadimgurl(json.getString("headimgurl"));
			userinfo.setOpenid(json.getString("openid"));
			userinfo.setUnionid(json.getString("unionid"));
			info.setUserinfo(userinfo);
		}catch(Exception ex){
			logService.systemError(AppCons.SYSTEM_ERROR_SERVICE, "微信登录获取个人信息出错", ex);
			return sr;
		}
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
	
	public User getUser(String unionid){
		UserExample e=new UserExample();
		Criteria c = e.createCriteria();
		c.andWxUnionidEqualTo(unionid);
		List<User> users = userMapper.selectByExample(e);
		if(users!=null&&users.size()==1){
			return users.get(0);
		}
		return null;
	}
	
	public SR thirdLoginInfoBind(User user,ThirdLoginInfo info){
		SR sr=new SR(AppCons.SR_FAIL,"微信登录信息绑定出错");
		String openId = info.getOpenId();
		String unionId=info.getUnionId();
		if(StringUtils.isEmpty(openId)||
				StringUtils.isEmpty(unionId)){
			sr.setMsg("信息不完整");
			return sr;
		}
		user.setWxOpenid(openId);
		user.setWxUnionid(unionId);
		
		userMapper.updateByPrimaryKeySelective(user);
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
	
	public SR thirdLoginInfoUnbind(User user){
		SR sr=new SR(AppCons.SR_FAIL,"微信登录信息解除绑定出错");
		user.setWxOpenid("");
		user.setWxUnionid("");
		user.setWxNickname("");
		
		userMapper.updateByPrimaryKeySelective(user);
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
}
