package com.wally.hiread.controller.user;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserStudyLog;
import com.wally.hiread.service.user.UserBadgeService;
import com.wally.hiread.service.user.UserStudyLogService;
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

import java.util.List;


@Controller
@RequestMapping("/user/study")
public class StudyLogController {
	@Autowired
	UserStudyLogService logService;
	@Autowired
	UserBadgeService badgeService;

	@RequestMapping(value = "/log/list", method = RequestMethod.POST)
	public @ResponseBody SR<List<UserStudyLog>> log(String productId, String unitId, String studySection) {
		SR<List<UserStudyLog>> sr=new SR<List<UserStudyLog>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		List<UserStudyLog> userStudyLogs = logService.todayListForUnitSection(userId, productId, unitId, studySection);
		sr.setEntity(userStudyLogs);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/log/update", method = RequestMethod.POST)
	public @ResponseBody SR<UserStudyLog> update(@RequestBody UserStudyLog userStudyLog) {
		SR<UserStudyLog> sr=new SR<UserStudyLog>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		userStudyLog.setUserId(userId);
		if(!StringUtils.isEmpty(userStudyLog.getId())){
			userStudyLog=logService.update(userStudyLog);
		}else{
			userStudyLog=logService.insert(userStudyLog);
		}
		sr.setEntity(userStudyLog);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	

	
}
