package com.wally.hiread.controller.product;

import com.wally.hiread.model.product.Practise;
import com.wally.hiread.model.product.PractiseUser;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserBadge;
import com.wally.hiread.model.user.UserBadgePanel;
import com.wally.hiread.service.product.PractiseUserService;
import com.wally.hiread.service.product.StudyPointService;
import com.wally.hiread.service.user.UserBadgeService;
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
@RequestMapping("/product/study/reward")
public class StudyRewardController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	StudyPointService spService;
	@Autowired
    PractiseUserService puService;
	@Autowired
    UserBadgeService ubService;

	@RequestMapping(value = "/studyPoint", method = RequestMethod.POST)
	public @ResponseBody SR<Integer> practiseSubmit(String practiseUserId) {
		SR<Integer> sr=new SR<Integer>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
        int point = spService.updateStudyPoint(practiseUserId);
        sr.setEntity(point);
        sr.setStatus(SR.SUCCESS);
        return sr;
	}
    @RequestMapping(value = "/accurateProcess", method = RequestMethod.POST)
    public @ResponseBody SR<List<UserBadge>> accurateNum(String practiseUserId) {
        SR<List<UserBadge>> sr=new SR<List<UserBadge>>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("Please login");
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        PractiseUser pu = puService.load(practiseUserId);
        if(pu==null){
            sr.setMsg("记录不存在");
            return sr;
        }

        List<Practise> practises=puService.practiseListWithAccuateNum(pu);
        List<UserBadge> badges =ubService.accurateBadgeProcess(pu, practises);
        int num=puService.accurateNum(pu,practises);
        UserBadge b=new UserBadge();
        b.setAccurateNum(num+"");
        b.setType(UserBadge.TYPE_ACCURATE_NUM);
        badges.add(b);
        sr.setEntity(badges);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }
    @RequestMapping(value = "/studyTimeProcess", method = RequestMethod.POST)
    public @ResponseBody SR<List<UserBadge>> studyTimeProcess(String productId) {
        SR<List<UserBadge>> sr=new SR<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        List<UserBadge> badges = ubService.studyTimeBadgeProcess(userId, productId);
        sr.setEntity(badges);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }
    @RequestMapping(value = "/recordingProcess", method = RequestMethod.POST)
    public @ResponseBody SR<List<UserBadge>> recordingProcess(String productId,String unitId) {
        SR<List<UserBadge>> sr=new SR<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        List<UserBadge> badges = ubService.recordingBadgeProcess(userId, productId,unitId);
        sr.setEntity(badges);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    @RequestMapping(value = "/unitCompleteProcess", method = RequestMethod.POST)
    public @ResponseBody SR<List<UserBadge>> unitCompleteProcess(String productId,String unitId,String unitSection) {
        SR<List<UserBadge>> sr=new SR<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        List<UserBadge> badges = ubService.unitCompleteBadgeProcess(userId, productId,unitId,unitSection);
        sr.setEntity(badges);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    @RequestMapping(value = "/greatStartProcess", method = RequestMethod.POST)
    public @ResponseBody SR<List<UserBadge>> greatStartProcess(String productId,String unitId,String greatStartType) {
        SR<List<UserBadge>> sr=new SR<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        List<UserBadge> badges = ubService.greatStartBadgeProcess(userId, productId,greatStartType);
        sr.setEntity(badges);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public @ResponseBody SR<UserBadgePanel> panel(String productId) {
        SR<UserBadgePanel> sr=new SR<UserBadgePanel>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("Please login");
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        UserBadgePanel panel = ubService.getPanel(userId, productId);

        sr.setEntity(panel);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }



}
