package com.wally.hiread.controller.sys;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.sys.CaptchaService;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.service.sys.SetupService;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.service.user.WxService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/sys/setup")
public class SetupController {
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
	SetupService setupService;
	private String imageCaptchaKey=AppCons.SESSION_KEY_IMAGE_CAPTCHA_LOGIN;
	private String captchaType=AppCons.CAPTCHA_TYPE_IMG;
	private CaptchaUtil cutil=new CaptchaUtil();
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody SR<Setup> get() {
		SR<Setup> sr=new SR<Setup>();
		Setup setup = setupService.loadSetup();
		sr.setEntity(setup);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	

	
}
