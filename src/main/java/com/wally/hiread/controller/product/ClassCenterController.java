package com.wally.hiread.controller.product;

import com.wally.hiread.model.product.UserProduct;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.user.UserProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/product/classCenter")
public class ClassCenterController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	UserProductService userProductService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "product/class-center";

	}
	@RequestMapping(value = "/hw", method = RequestMethod.GET)
	public String hw() {
		return "product/class-hw";
	}
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String preview() {
		return "product/class-preview";
	}
	@RequestMapping(value = "/testPractise", method = RequestMethod.GET)
	public String testPractise() {
		return "product/class-test-practise";
	}
	@RequestMapping(value = "/freeTalk", method = RequestMethod.GET)
	public String freeTalk() {
		return "product/class-free-talk";
	}

	@RequestMapping(value = "/userProducts", method = RequestMethod.GET)
	public @ResponseBody
	SR<List<UserProduct>> UserProducts(Integer currentPage) {
		SR<List<UserProduct>> sr=new SR<List<UserProduct>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		sr = userProductService.myUserProducts(userId,currentPage);
		return sr;

	}
	@RequestMapping(value = "/userProduct", method = RequestMethod.GET)
	public @ResponseBody
	SR<UserProduct> UserProduct(String id) {
		SR<UserProduct> sr=new SR<UserProduct>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		UserProduct userProduct = userProductService.myUserProduct(userId,id,true);
		sr.setEntity(userProduct);
		sr.setStatus(SR.SUCCESS);

		return sr;

	}



}
