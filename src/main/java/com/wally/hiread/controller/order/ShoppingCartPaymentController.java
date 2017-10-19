package com.wally.hiread.controller.order;

import com.wally.hiread.model.order.Coupon;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.order.ShoppingCartOrderService;
import com.wally.hiread.service.order.ShoppingCartService;
import com.wally.hiread.service.user.UserAddressService;
import com.wally.hiread.service.user.UserPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/order/shoppingCartPayment")
public class ShoppingCartPaymentController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	ShoppingCartOrderService cartOrderService;
	@Autowired
	UserPointService upService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserAddressService addrService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "order/shopping-cart-payment";
	}
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String success() {
		return "order/shopping-cart-payment-success";
	}
	@RequestMapping(value = "/success/giveCoupon", method = RequestMethod.GET)
	public @ResponseBody
	SR<Coupon> giveGoupon(String orderId) {
		SR<Coupon> sr=new SR<Coupon>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		Coupon giveCoupon = couponService.getGiveCoupon(user.getId(), orderId, Coupon.GETTYPE_PO);
		sr.setEntity(giveCoupon);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}


}


