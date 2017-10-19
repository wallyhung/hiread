package com.wally.hiread.controller.user;

import com.wally.hiread.dao.user.UserTestingMapper;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.sys.Testing;
import com.wally.hiread.model.sys.TestingReport;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserTesting;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.sys.TestingService;
import com.wally.hiread.service.user.UserPointService;
import com.wally.hiread.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping(value = "/user/testing/readingLevel")
public class TestingReadingLevelController {
    @Autowired
    UserService userService;
    @Autowired
    CouponService couponService;
    @Autowired
    UserPointService userPointService;
    @Autowired
    TestingService testingService;
    @Autowired
    UserTestingMapper userTestingMapper;

    @RequestMapping(value = "")
    public String index() {
        return "user/testing/reading-level";
    }

    @RequestMapping(value = "/testings")
    public @ResponseBody
    SR<List<Testing>> testings(String paperLevel) {
        SR<List<Testing>> sr=new SR<List<Testing>>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();
        if(StringUtils.isEmpty(paperLevel)||paperLevel.equals("undefined")){
            String point=user.getQuestionnairePoint();
            if(StringUtils.isEmpty(point)){
                sr.setMsg("问卷调查分数为空");
                return sr;
            }
            int pointI=0;
            try{
                pointI=Integer.parseInt(point);
            }catch (Exception e){
                sr.setMsg("问卷调查分数不合法");
                return sr;
            }
            paperLevel = testingService.getPaperLevel(pointI);
            if(StringUtils.isEmpty(paperLevel)){
                sr.setMsg("问卷调查分数不合法");
                return sr;
            }
        }
        List<Testing> list = testingService.list(paperLevel);
        sr.setEntity(list);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    @RequestMapping(value = "/testing/report")
    public @ResponseBody
    SR<TestingReport> testingReport(String paperLevel) {
        SR<TestingReport> sr=new SR<TestingReport>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();

        int pl=0;
        try{
            pl=Integer.parseInt(paperLevel);
        }catch (Exception e){
            sr.setMsg("参数不合法");
            return sr;
        }

        TestingReport r = testingService.getTestingReport(userId,pl);
        testingService.setTestingResult(userId,r.getPaperLevel(),r.getCorrectPercent());
        sr.setEntity(r);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    @RequestMapping(value = "/testing/submit", method = RequestMethod.POST)
    public @ResponseBody
    SR<List<Testing>> testings(String testingId,String answer,String correct) {
        SR sr=new SR();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user=(User)auth.getDetails();
        String userId=user.getId();

        UserTesting ut = testingService.findUserTesting(userId, testingId);
        Date now=new Date();
        if(ut==null){
            ut=new UserTesting();
            ut.setId(UUID.randomUUID().toString());
            ut.setDatecreated(now);
            ut.setDatemodified(now);
            ut.setUserId(userId);
            ut.setTestingId(testingId);
            ut.setAnswer(answer);
            ut.setCorrect(correct);
            userTestingMapper.insert(ut);
        }else{
            ut.setDatemodified(now);
            ut.setUserId(userId);
            ut.setTestingId(testingId);
            ut.setAnswer(answer);
            ut.setCorrect(correct);
            userTestingMapper.updateByPrimaryKey(ut);
        }
        sr.setStatus(SR.SUCCESS);
        return sr;
    }


}
