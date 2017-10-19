package com.wally.hiread.controller.order;

import com.wally.hiread.model.order.ProdOrder;
import com.wally.hiread.model.order.ShoppingCartSubmit;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserAddress;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.order.ProdOrderService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/order/shoppingCartOrder")
public class ShoppingCartOrderController {
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
	@Autowired
	ProdOrderService prodOrderService;
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "order/shopping-cart-order";
	}

	@RequestMapping(value = "/shoppingCartSubmit", method = RequestMethod.GET)
	public @ResponseBody
	SR<ShoppingCartSubmit> shoppingCartSubmit(HttpSession session){
		SR<ShoppingCartSubmit> sr=new SR<ShoppingCartSubmit>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		ShoppingCartSubmit sub=cartService.getCartSubmit(session);
		sr.setEntity(sub);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/prodOrder", method = RequestMethod.GET)
	public @ResponseBody
	SR<ProdOrder> prodOrder(String orderId){
		SR<ProdOrder> sr=new SR<ProdOrder>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		ProdOrder prodOrder = prodOrderService.loadMyOrder(user.getId(), orderId);
		if(prodOrder==null){
			sr.setMsg("订单不存在");
			return sr;
		}

		sr.setEntity(prodOrder);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	SR<String> submit(String channel,String buyerMessage,String addressId,String orderId,HttpSession session){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		sr=cartOrderService.submit(session,channel,buyerMessage,addressId,user.getId(),orderId);
		return sr;
	}

	@RequestMapping(value = "/addr/save", method = RequestMethod.POST)
	public @ResponseBody
	SR<String> addrSave(@RequestBody UserAddress addr){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		SR<String> validate = addrService.validate(addr);
		if(validate.getStatus().equals(SR.FAIL)){
			sr.setMsg(validate.getMsg());
			return sr;
		}
		addrService.add(addr,user.getId());
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/addr/update", method = RequestMethod.POST)
	public @ResponseBody
	SR<String> addrUpdate(@RequestBody UserAddress addr){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();

		UserAddress userAddr=addrService.load(addr.getId(),user.getId());
		if(userAddr==null){
			sr.setMsg("地址不存在");
			return sr;
		}
		userAddr.setName(addr.getName());
		userAddr.setMobile(addr.getMobile());
		userAddr.setProvince(addr.getProvince());
		userAddr.setProvinceId(addr.getProvinceId());
		userAddr.setCity(addr.getCity());
		userAddr.setCityId(addr.getCityId());
		userAddr.setArea(addr.getArea());
		userAddr.setAreaId(addr.getAreaId());
		userAddr.setDetailAddress(addr.getDetailAddress());
		userAddr.setAsDefault(addr.getAsDefault());
		SR<String> validate = addrService.validate(userAddr);
		if(validate.getStatus().equals(SR.FAIL)){
			sr.setMsg(validate.getMsg());
			return sr;
		}
		addrService.update(userAddr,user.getId());
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/addr/delete", method = RequestMethod.POST)
	public @ResponseBody
	SR<String> addrDefault(String addrId){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		addrService.delete(addrId,user.getId());
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/addr/list", method = RequestMethod.GET)
	public @ResponseBody
	SR<List<UserAddress>> addrList(){
		SR<List<UserAddress>> sr=new SR<List<UserAddress>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		List<UserAddress> userAddresses = addrService.myAddresses(user.getId());
		sr.setEntity(userAddresses);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

}


