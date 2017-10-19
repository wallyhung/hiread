package com.wally.hiread.setting.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.setting.sys.AppCons;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	LoginService loginService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication instanceof UsernamePasswordAuthenticationToken){
			UsernamePasswordAuthenticationToken auth=(UsernamePasswordAuthenticationToken)authentication;
			String name = authentication.getName();
			String password = "";
			if (authentication.getCredentials() == null) {
				return authentication;
			}
			password = authentication.getCredentials().toString();
			SR<User> validateResult = loginService.validate(name, password);
			if(validateResult.getStatus().equals(AppCons.SR_SUCCESS)){
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				grantedAuths.add(new SimpleGrantedAuthority(AppCons.ROLE_USER));
				auth=new UsernamePasswordAuthenticationToken(name,password,grantedAuths);
				auth.setDetails(validateResult.getEntity());
				//auth.setAuthenticated(true);不需要这个语句.Once created you cannot set this token to authenticated. Create a new instance using the constructor which takes a GrantedAuthority list will mark this as authenticated.
				return auth;
			}
		}
		
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
