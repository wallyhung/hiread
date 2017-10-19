package com.wally.hiread.controller.product;

import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.product.*;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.*;
import com.wally.hiread.service.sys.FileService;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.UserProductService;
import com.wally.hiread.service.user.UserStudyLogService;
import com.wally.hiread.setting.sys.AppCons;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/product/class")
public class ClassController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	UnitMapper unitMapper;
	@Autowired
	FileService fileService;
	@Autowired
	LogService logService;
	@Autowired
	UnitService unitService;
	@Autowired
	PractiseUserService puService;
	@Autowired
	PractiseService pService;
	@Autowired
	LoginService lService;
	@Autowired
	PractiseService practiseService;
	@Autowired
	UserProductService userProductService;
	@Autowired
	LearningProgressService lpService;
	@Autowired
	UserFreeTalkService talkService;
	@Autowired
	UserStudyLogService studyLogService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "product/class";

	}

	@RequestMapping(value = "/unit/load", method = RequestMethod.GET)
	public @ResponseBody
	SR<Unit> load(String unitId,String type){
		SR<Unit> sr=new SR<Unit>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		Unit unit=unitService.load(unitId,true,false,true,true,true);
		List<Practise> practises=pService.listByUnitId(unitId,true,type,true);
		for(Practise p:practises){
			pService.setExt(userId,p,type);
		}
		unit.setPractises(practises);
		sr.setEntity(unit);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/testPractises", method = RequestMethod.GET)
	public @ResponseBody
	SR<List<Practise>> testPractises(String productId,String type){
		SR<List<Practise>> sr=new SR<List<Practise>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		String[] applyType=null;
		if(type.equals(Practise.APPLY_TYPE_PRETEST_PRACTISE)){
			applyType=new String[]{type};
		}else if(type.equals(Practise.APPLY_TYPE_POSTTEST_PRACTISE)){
			applyType=new String[]{Practise.APPLY_TYPE_PRETEST_PRACTISE,type};
		}else{
			sr.setMsg("不支持的类型");
			return sr;
		}
		List<Practise> practises=pService.listByProductId(productId,true,applyType,true);
		for(Practise p:practises){
			pService.setExt(userId,p,type);
		}
		sr.setEntity(practises);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/freeTalk", method = RequestMethod.POST)
	public @ResponseBody
	SR<UserFreeTalk> freeTalk(String productId,String type) {
		SR<UserFreeTalk> sr = new SR<UserFreeTalk>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user = (User) auth.getDetails();
		String userId = user.getId();
		UserFreeTalk talk=new UserFreeTalk();
		talk.setUserId(userId);
		talk.setProductId(productId);
		talk.setType(type);
		talk = talkService.insert(talk);
		sr.setEntity(talk);
		sr.setStatus(SR.SUCCESS);
		return sr;

	}
	@RequestMapping(value = "/practiseUser/init", method = RequestMethod.POST)
	public @ResponseBody SR<PractiseUser> practiseUserInit(@RequestBody PractiseUser pu) {
		SR<PractiseUser> sr=new SR<PractiseUser>();
		SR<User> checkLogin = lService.checkLogin();
		if(checkLogin.getStatus().equals(SR.FAIL)){
			sr.setMsg(checkLogin.getMsg());
			return sr;
		}
		User user=checkLogin.getEntity();
		Date now=new Date();
		SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
		pu.setId(UUID.randomUUID().toString());
		pu.setDatecreated(now);
		pu.setDatemodified(now);
		pu.setUserId(user.getId());
		boolean firstTime = puService.isFirstTime(user.getId(), pu.getPractiseId(), pu.getType());
		pu.setFirstTime(firstTime?"1":"0");
		puService.insert(pu);
		sr.setStatus(SR.SUCCESS);
		sr.setEntity(pu);
		return sr;
	}
	@RequestMapping(value = "/practiseUser/notCorrect", method = RequestMethod.GET)
	public @ResponseBody SR<PractiseUser> practiseUserInit(String practiseId,String type) {
		SR<PractiseUser> sr=new SR<PractiseUser>();
		SR<User> checkLogin = lService.checkLogin();
		if(checkLogin.getStatus().equals(SR.FAIL)){
			sr.setMsg(checkLogin.getMsg());
			return sr;
		}
		User user=checkLogin.getEntity();
		PractiseUser notCorrectPractiseUser = puService.getNotCorrectPractiseUser(user.getId(), practiseId, type);
		sr.setStatus(SR.SUCCESS);
		sr.setEntity(notCorrectPractiseUser);
		return sr;
	}

	@RequestMapping(value = "/practiseUser/use", method = RequestMethod.POST)
	public @ResponseBody SR practiseUserHelp(String practiseUserId,boolean help,boolean trans) {
		SR sr=new SR();
		SR<User> checkLogin = lService.checkLogin();
		if(checkLogin.getStatus().equals(SR.FAIL)){
			sr.setMsg(checkLogin.getMsg());
			return sr;
		}
		PractiseUser practiseUser = puService.load(practiseUserId);
		if (practiseUser == null) {
			sr.setMsg("request failed");
			return sr;
		}
		if(help){
			practiseUser.setUseHelp("1");
		}
		if(trans){
			practiseUser.setUseTranslation("1");
		}
		puService.update(practiseUser);
		sr.setStatus(SR.SUCCESS);
		sr.setEntity(practiseUser);
		return sr;
	}
	@RequestMapping(value = "/practiseUser/update", method = RequestMethod.POST)
	public @ResponseBody SR<List<Practise>> practiseSubmit(@RequestBody PractiseUser pu) {
		SR<List<Practise>> sr=new SR<List<Practise>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		pu.setUserId(userId);
		puService.update(pu);
		List<Practise> practises=null;
		String type=pu.getType();
		if(pu.getType().equals(Practise.APPLY_TYPE_PRETEST_PRACTISE)||pu.getType().equals(Practise.APPLY_TYPE_POSTTEST_PRACTISE)){
			String[] applyType=null;
			if(type.equals(Practise.APPLY_TYPE_PRETEST_PRACTISE)){
				applyType=new String[]{type};
			}else if(type.equals(Practise.APPLY_TYPE_POSTTEST_PRACTISE)){
				applyType=new String[]{Practise.APPLY_TYPE_PRETEST_PRACTISE,type};
			}else{
				sr.setMsg("不支持的类型");
				return sr;
			}
			practises=pService.listByProductId(pu.getProductId(),true,applyType,true);
		}else{
			practises=pService.listByUnitId(pu.getUnitId(),true,type,true);
		}
		for(Practise p:practises){
			boolean completedForStudyLog=puService.practiseCompletedForStudyLog(p.getId(),pu.getUserStudyLogId());
			boolean correctForStudyLog=puService.practiseCorrectForStudyLog(p.getId(),pu.getUserStudyLogId());
			p.setCompletedForStudyLog(completedForStudyLog);
			p.setCorrectForStudyLog(correctForStudyLog);
		}
		sr.setEntity(practises);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/userProduct/status", method = RequestMethod.POST)
	public @ResponseBody SR userProductStatus(String userProductId,String unitId,String status) {
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		UserProduct userProduct = userProductService.myUserProduct(userId, userProductId,true);
		if(userProduct==null){
			sr.setMsg("课程不存在");
			return sr;
		}
		String unitIdForUpdate = userProductService.unitIdForUpdate(userProduct);
		String statusForUpdate = userProductService.statusForUpdate(userProduct);
		if(StringUtils.isEmpty(statusForUpdate)||!statusForUpdate.equals(status)){
			sr.setMsg("我的课程进度更新不合法");
			return sr;
		}
		if(!StringUtils.isEmpty(unitId)&&(StringUtils.isEmpty(unitIdForUpdate)||!unitIdForUpdate.equals(unitId))){
			sr.setMsg("我的课程进度更新不合法");
			return sr;
		}
		if(!StringUtils.isEmpty(unitId)){
			userProduct.setUnitId(unitId);
		}
		userProduct.setStatus(status);
		userProductService.update(userProduct);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/userProduct/status", method = RequestMethod.GET)
	public @ResponseBody SR<String> userProductStatusGet(String userProductId,String unitId) {
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		UserProduct userProduct = userProductService.myUserProduct(userId, userProductId,true);
		if(userProduct==null){
			sr.setMsg("课程不存在");
			return sr;
		}
		String status = userProduct.getStatus();
		sr.setEntity(status);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	/*
	插入学习进度(开始时间)
	 */
	@RequestMapping(value = "/learningProgress/start", method = RequestMethod.POST)
	public @ResponseBody SR lpStart(String productId,String unitId,String type) {
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		LearningProgress load = lpService.load(userId, productId, unitId, type);
		if(load!=null){
			sr.setMsg("课程进度已存在");
			return sr;
		}
		LearningProgress lp=new LearningProgress();
		lp.setUserId(userId);
		lp.setProductId(productId);
		lp.setUnitId(unitId);
		lp.setType(type);
		lpService.insert(lp);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	/*
	更新学习进度(结束时间)
	 */
	@RequestMapping(value = "/learningProgress/end", method = RequestMethod.POST)
	public @ResponseBody SR lpEnd(String productId,String unitId,String type) {
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		LearningProgress lp = lpService.load(user.getId(), productId, unitId, type);
		if(lp==null){
			sr.setMsg("课程进度不存在");
			return sr;
		}
		String endTime = lp.getEndTime();
		if(!StringUtils.isEmpty(endTime)){
			sr.setMsg("课程进度已结束");
			return sr;
		}
		try {
			SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
			lp.setEndTime(dtf.format(new Date()));
			lpService.update(lp);
		} catch (ParseException e) {
			sr.setMsg("课程进度更新失败");
			return sr;
		}
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/learningProgress/updateTotalTime", method = RequestMethod.POST)
	public @ResponseBody SR updateTotalTime(String productId,String unitId,String type) {
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		LearningProgress lp = lpService.load(user.getId(), productId, unitId, type);
		if(lp==null){
			sr.setMsg("课程进度不存在");
			return sr;
		}
		try {
			long sec = studyLogService.totalTimeForUnitSection(user.getId(), productId, unitId, type,true);
			String min=new BigDecimal(sec/60.0).setScale(0,BigDecimal.ROUND_CEILING).toString();
			lp.setTotalTime(min);
			lpService.update(lp);
		} catch (ParseException e) {
			sr.setMsg("课程进度更新失败");
			return sr;
		}
		sr.setStatus(SR.SUCCESS);
		return sr;
	}


}
