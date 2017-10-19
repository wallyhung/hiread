package com.wally.hiread.setting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//	private CustomAuthenticationProvider customAuthenticationProvider;
//	@Autowired
//	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(customAuthenticationProvider);
//	}
	@Autowired
	private Environment environment;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/index","/wx/**",
				 "/product/product/**",
			     "/product/products/**",
				 "/product/readShare/**",
				 "/order/shoppingCart/**",
				 "/order/cooperation/**",
				 "/order/payment/notify/**",
				 "/sys/file/**",
				 "/sys/setup/**",
				 "/sys/config/url/base",
			     "/sys/session/**",
			     "/user/info/**",
			     "/user/login/**",
			     "/user/register/**",
			     "/user/reset/**",
			     "/user/wxmp/**").permitAll().anyRequest().authenticated()
		.and().logout().logoutSuccessUrl("/")
		.and().csrf().disable();

		String[] activeProfiles = environment.getActiveProfiles();
		boolean needHttps=true;
		for(String s:activeProfiles){
			if(s.equals("dev-nohttps")){
				needHttps=false;
			}
		}
		if(needHttps){
			http.requiresChannel().anyRequest().requiresSecure();
		}

	}
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring()
						.antMatchers("/MP_verify_wFkKro3YlQNdj7Mb.txt")//微信公众号JS接口安全域验证需要(该功能暂未使用，在页面需要使用jssdk时使用)
						.antMatchers("/controller/**")
				        .antMatchers("/app/**")
						.antMatchers("/service/**")
						.antMatchers("/js/**")
						.antMatchers("/css/**")
						.antMatchers("/audio/**")
						.antMatchers("/dist/**")
						.antMatchers("/images/**")
						.antMatchers("/img/**")
				        .antMatchers("/hi_img/**")
						.antMatchers("/layui/**")
						.antMatchers("/test/**")
						.antMatchers("/order/prodOrder/downloadPDF")//放在这里不会进行CSRF token检查,security里的信息为空
						.antMatchers(HttpMethod.POST,"/order/prodOrder/downloadPDF");
						
	}
	
}
