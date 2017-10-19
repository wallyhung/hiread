package com.wally.hiread.controller.order;

import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.order.ShoppingCartService;
import com.wally.hiread.service.user.UserPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/order/cooperation")
public class CooperationController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	UserPointService upService;
	@Autowired
	CouponService couponService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "order/cooperation";
	}


}


