package com.wally.hiread.controller.user;

import com.sun.org.apache.regexp.internal.RE;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.sys.CaptchaService;
import com.wally.hiread.service.sys.SmsService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.MD5Util;
import com.wally.hiread.util.RegExpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eric on 02/06/2017.
 */
@Controller
@RequestMapping(value = "/user/reset/password")
public class ResetPasswordController {
    @Autowired
    CaptchaService captchaService;
    @Autowired
    SmsService smsService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/step1", method = RequestMethod.GET)
    public String step1(){
        return "/user/reset-password-step1";
    }

    @RequestMapping(value = "/step2", method = RequestMethod.GET)
    public String step2(){
        return "/user/reset-password-step2";
    }

    @RequestMapping(value = "send-sms-captcha")
    public @ResponseBody SR sendSMSCaptcha(String mobile, HttpSession session){
        SR sr = new SR();

        if(!RegExpValidator.isMobile(mobile)){
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("手机号码格式错误");

            return sr;
        }

        sr = captchaService.codeGenValidate(AppCons.SESSION_KEY_SMS_CAPTCHA_RESET_PASSWORD, session, AppCons.CAPTCHA_TYPE_SMS);
        if(sr.getStatus().equals(AppCons.SR_FAIL)){
            return sr;
        }

        String  captcha = captchaService.genCaptchaCode(AppCons.SESSION_KEY_SMS_CAPTCHA_RESET_PASSWORD, session, AppCons.CAPTCHA_TYPE_SMS);
        sr = smsService.sendSms(mobile, new String[]{captcha}, AppCons.SMS_TYPE_FIND_PASS);

        return sr;
    }

    @RequestMapping(value = "validate-sms-captcha", method = RequestMethod.POST)
    public @ResponseBody SR validateCaptcha(String mobile, String smsCaptcha, HttpSession session){
        SR sr = new SR();

        if(StringUtils.isEmpty(smsCaptcha)||!RegExpValidator.isMobileVerifyCode(smsCaptcha)){
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("手机验证码格式不正确");
            return sr;
        }

        SR<String> validateSr = captchaService.validateCode(AppCons.SESSION_KEY_SMS_CAPTCHA_RESET_PASSWORD, smsCaptcha, session);
        if(validateSr.getStatus().equals(AppCons.SR_FAIL)){
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg(validateSr.getMsg());
            return sr;
        }

        Map map = new HashMap();
        map.put("mobile", mobile);
        map.put("smsCaptcha", smsCaptcha);

        sr.setStatus(AppCons.SR_SUCCESS);
        sr.setMsg(AppCons.SR_MSG_OK);
        sr.setEntity(map);

        session.removeAttribute(AppCons.SESSION_KEY_SMS_CAPTCHA_RESET_PASSWORD);
        session.setAttribute(AppCons.SESSION_KEY_RESET_PASSWORD, sr);

        return sr;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody SR resetPassword(String newPassword, String newPasswordRepeat, HttpSession session){
        SR sr = new SR();

        SR resetPasswordSession = (SR) session.getAttribute(AppCons.SESSION_KEY_RESET_PASSWORD);
        if(resetPasswordSession == null){
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("重置密码 Session 不存在");
            return sr;
        }

        if((newPassword == null) || !newPassword.equals(newPasswordRepeat)){
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("两次输入的密码不一样");
            return sr;
        }

        String mobile = ((Map) resetPasswordSession.getEntity()).get("mobile").toString();
        String encryptedPassword = MD5Util.string2MD5(newPassword);
        User user = userService.loadUserByMobile(mobile);
        user.setPassword(encryptedPassword);
        userService.updateByPrimaryKey(user);

        sr.setStatus(AppCons.SR_SUCCESS);
        sr.setMsg(AppCons.SR_MSG_OK);

        session.removeAttribute(AppCons.SESSION_KEY_RESET_PASSWORD);

        return sr;
    }
}
