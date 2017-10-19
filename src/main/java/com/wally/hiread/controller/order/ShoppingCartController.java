package com.wally.hiread.controller.order;

import com.wally.hiread.model.order.Coupon;
import com.wally.hiread.model.order.ShoppingCart;
import com.wally.hiread.model.order.ShoppingCartSubmit;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.order.ShoppingCartService;
import com.wally.hiread.service.user.UserPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/order/shoppingCart")
public class ShoppingCartController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	UserPointService upService;
	@Autowired
	CouponService couponService;

	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String process() {
		return "order/shopping-cart-process";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "order/shopping-cart";
	}
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    SR<List<ShoppingCart>> list(HttpSession session){
        SR<List<ShoppingCart>> sr=new SR<List<ShoppingCart>>();
        List<ShoppingCart> list = cartService.list(session);
        sr.setEntity(list);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	SR<List<ShoppingCart>> add(HttpSession session, String productId){
		SR<List<ShoppingCart>> sr=new SR<List<ShoppingCart>>();
		if(cartService.has(session,productId)){
			sr.setMsg("该课程已加入到购物车了");
			sr.setStatus(SR.FAIL);
			return sr;
		}
		List<ShoppingCart> list = cartService.add(session, productId);
		sr.setEntity(list);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody
	SR<List<ShoppingCart>> remove(HttpSession session, String cartId){
		SR<List<ShoppingCart>> sr=new SR<List<ShoppingCart>>();
		List<ShoppingCart> list=cartService.remove(session,cartId);
		sr.setEntity(list);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public @ResponseBody
    SR empty(HttpSession session){
        SR sr=new SR();
        cartService.empty(session);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	SR<String> submit(HttpSession session, @RequestBody ShoppingCartSubmit req ){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();

		Coupon couponSelected = req.getCouponSelected();
		String couponId=couponSelected.getId();
		if(!StringUtils.isEmpty(couponId)){
			SR sr1 = couponService.validateCoupon(couponSelected.getId(), user.getId());
			if(sr1.getStatus().equals(SR.FAIL)){
				return sr1;
			}
		}

		Integer usePoint = req.getUsePoint();
		int sum = upService.myUserPointsSum(user.getId());
		if(usePoint>sum){
			sr.setMsg("积分不足");
			return sr;
		}

		double couponPrice = req.getCouponPrice();
		String amount = couponSelected.getAmount();
		if(!StringUtils.isEmpty(amount)){
			if(Integer.parseInt(amount)>couponPrice&&usePoint>0){
				sr.setMsg("使用积分超额");
				return sr;
			}
		}

		couponSelected.setUseAmount(new BigDecimal(couponPrice).setScale(2,BigDecimal.ROUND_HALF_UP).toString());

		List<ShoppingCart> cartSelectedList = req.getCartSelectedList();
		boolean validList=cartService.validSelectedList(session,cartSelectedList);
		if(!validList){
			sr.setMsg("购物车失效");
			return sr;
		}

		Double total = req.getTotal();
		double calcTotal = cartService.calculateTotal(session, cartSelectedList, couponPrice, usePoint);
		if(total!=calcTotal){
			sr.setMsg("价格不正确");
			return sr;
		}

		if(calcTotal==0){
			req.setNoPayment(true);
		}else{
			req.setNoPayment(false);
		}
		cartService.cartSubmit(session, req);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}


}


