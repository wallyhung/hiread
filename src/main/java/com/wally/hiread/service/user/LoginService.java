package com.wally.hiread.service.user;

import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserExample;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.MD5Util;
import com.wally.hiread.util.RegExpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class LoginService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserService userService;
	@Autowired
	LogService logService;

	public SR<User> validate(String name,String password){
		SR<User> sr=new SR<User>();
		sr.setStatus(SR.FAIL);
		if(StringUtils.isEmpty(name)||(!RegExpValidator.isMobile(name))){
			sr.setMsg("手机号格式不正确");
			return sr;
		}
//		if(StringUtils.isEmpty(password)||!RegExpValidator.isPassword(password)){
//			sr.setMsg("密码格式不正确");
//			return sr;
//		}
		
		User user=userService.loadUserByMobile(name);
		if(user==null){
			sr.setMsg("该手机号不存在");
			return sr;
		}
		String encryptedPassword = MD5Util.string2MD5(password);
		if(!user.getPassword().equals(encryptedPassword)){
			sr.setMsg("密码不正确");
			return sr;
		}
		
		sr.setStatus(SR.SUCCESS);
		sr.setEntity(user);
		return sr;
	}
	
	public SR<User> setLoginUser(String unionid){
		SR<User> sr=new SR<User>();
		sr.setStatus(SR.FAIL);

		User user = userService.loadUserByUnionId(unionid);

		if(user == null){
			sr.setMsg("该用户不存在");
			return sr;
		} else if (user.getWxUnionid() == null){
			sr.setMsg("该用户未在微信开放平台绑定账号");
			return sr;
		}

		user.setPassword(null);
		
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		grantedAuths.add(new SimpleGrantedAuthority(AppCons.ROLE_USER));
		UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
				user.getWxUnionid(), "",grantedAuths);
		newAuth.setDetails(user);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		//logService.info("Login success:" + user.getWxNickname() + "[" + user.getWxUnionid() + "]");

		sr.setStatus(AppCons.SR_SUCCESS);
		sr.setEntity(user);

		return sr;
	}
	//TO SPECIAL:为了方便测试，带有auto_login标识的用户自动登录
	private void autoLogin(){
		UserExample e=new UserExample();
		UserExample.Criteria c = e.createCriteria();
		c.andInviteByEqualTo("auto_login");
		List<User> users = userMapper.selectByExample(e);
		if(users!=null&&users.size()>0){
			this.setLoginUser(users.get(0).getWxUnionid());
		}
	}

	public SR<User> refreshLoginUser(){
		SR<User> sr=new SR<User>();
		sr.setStatus(AppCons.SR_FAIL);
		autoLogin();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth  instanceof UsernamePasswordAuthenticationToken)){
			sr.setMsg("您尚未登录");
			return sr;
		}
		if(!auth.isAuthenticated()){
			sr.setMsg("您尚未登录");
			return sr;
		}
		User user=(User)auth.getDetails();

		sr=this.setLoginUser(user.getWxUnionid());
		return sr;
		
	}

	public SR<User> checkLogin(){
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		sr.setEntity(user);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
}
