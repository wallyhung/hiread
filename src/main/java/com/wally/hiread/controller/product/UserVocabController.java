package com.wally.hiread.controller.product;

import com.wally.hiread.model.product.UserVocab;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.UserVocabService;
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
@RequestMapping("/product/userVocab")
public class UserVocabController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	UserVocabService userVocabService;


	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public @ResponseBody
	SR<List<UserVocab>> myList() {
		SR<List<UserVocab>> sr=new SR<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		List<UserVocab> userVocabs = userVocabService.myList(userId);
		sr.setEntity(userVocabs);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/userVocab", method = RequestMethod.GET)
	public @ResponseBody
	SR<UserVocab> userVocab(String practiseId) {
		SR<UserVocab> sr=new SR<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		UserVocab userVocab = userVocabService.getUserVocab(userId, practiseId);
		sr.setEntity(userVocab);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	SR<UserVocab> add(String practiseId) {
		SR<UserVocab> sr=new SR<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		UserVocab userVocab = userVocabService.add(userId, practiseId);
		sr.setEntity(userVocab);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public @ResponseBody
	SR delete(String userVocabIds) {
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		String[] ids=userVocabIds.split(";");
		for(String id:ids){
			UserVocab userVocab = userVocabService.delete(id);
		}
		sr.setStatus(SR.SUCCESS);
		return sr;
	}



}
