package com.wally.hiread.controller.user;

import com.wally.hiread.model.order.Coupon;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserAddress;
import com.wally.hiread.model.user.UserPoint;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.sys.CaptchaService;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.service.user.*;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/user/info")
public class InfoController {
	@Autowired
	LogService logService;
	@Autowired
	LoginService loginService;
	@Autowired
	UserService userService;
	@Autowired
	CaptchaService capService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	WxService wxService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserPointService upService;
	@Autowired
	UserAddressService addressService;
	private String imageCaptchaKey=AppCons.SESSION_KEY_IMAGE_CAPTCHA_LOGIN;
	private String captchaType=AppCons.CAPTCHA_TYPE_IMG;
	private CaptchaUtil cutil=new CaptchaUtil();
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody SR<User> refreshLoginUser() {
		SR<User> sr=new SR<User>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setEntity(null);
			sr.setStatus(SR.SUCCESS);
			return sr;
		}
		User user=(User)auth.getDetails();
		List<Coupon> coupons = couponService.myCoupons(user.getId());
		List<UserPoint> userPoints = upService.myUserPoints(user.getId());
		int userPointsSum=upService.myUserPointsSum(user.getId());
		double userPointsSumRmb = upService.myUserPointsSumRmb(user.getId());
		List<UserAddress> userAddresses = addressService.myAddresses(user.getId());
		user.setCoupons(coupons);
		user.setUserPoints(userPoints);
		user.setUserAddresses(userAddresses);
		user.setUserPointsSum(userPointsSum);
		user.setUserPointsSumRmb(userPointsSumRmb);
		sr.setEntity(user);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	

	
}
