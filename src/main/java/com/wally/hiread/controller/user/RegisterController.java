package com.wally.hiread.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.*;
import com.wally.hiread.service.sys.CaptchaService;
import com.wally.hiread.service.sys.SmsService;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.RegisterService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.service.user.WxService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.MD5Util;
import com.wally.hiread.util.RegExpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by eric on 25/05/2017.
 */
@Controller
@RequestMapping(value = "/user/register")
public class RegisterController {
    @Autowired
    WxService wxService;
    @Autowired
    RegisterService registerService;
    @Autowired
    UserService userService;
    @Autowired
    CaptchaService captchaService;
    @Autowired
    SmsService smsService;
    @Autowired
    LoginService loginService;

    private String smsCaptchaKey = AppCons.SESSION_KEY_SMS_CAPTCHA_REGISTER;
    private String captchaType = AppCons.CAPTCHA_TYPE_SMS;

    /**
     * 注册
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody
    SR<ThirdLoginInfo> register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ThirdLoginInfo thirdLoginInfo = wxService.initLoginInfo();

        SR<ThirdLoginInfo> sr = new SR<ThirdLoginInfo>(AppCons.SR_FAIL, "初始化登录信息出错");

        if (thirdLoginInfo != null) {
            String state = session.getId();
            thirdLoginInfo.setState(state);
            String redirectUri = thirdLoginInfo.getRedirectUri();
            thirdLoginInfo.setRedirectUri(redirectUri);
            sr.setEntity(thirdLoginInfo);
            sr.setStatus(AppCons.SR_SUCCESS);
            sr.setMsg(AppCons.SR_MSG_OK);

            session.setAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO, thirdLoginInfo);
        }

        return sr;
    }

    @RequestMapping(value = "step1", method = RequestMethod.GET)
    public String registerStep1() {
        return "user/register-step1";
    }

    @RequestMapping(value = "step2", method = RequestMethod.GET)
    public String registerStep2() {
        return "user/register-step2";
    }

    /**
     * 获取微信用户信息
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/wx-userinfo", method = RequestMethod.GET)
    public @ResponseBody
    SR<User> wxUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        SR sr = new SR();

        /* 网站应用 */
        ThirdLoginInfo thirdLoginInfo = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
        if ((thirdLoginInfo != null) && (thirdLoginInfo.getUserinfo() != null)) {
            ThirdLoginUserinfo thirdLoginUserinfo = thirdLoginInfo.getUserinfo();
            sr.setStatus(AppCons.SR_SUCCESS);
            sr.setEntity(thirdLoginUserinfo);
            return sr;
        }

        /* 服务号 */
        SessionOperation opt = (SessionOperation) session.getAttribute(SessionOperation.KEY);
        WxmpUserInfo wxUser = null;
        if (opt != null) {
            wxUser = opt.getWxUser();
        }
        if (wxUser != null) {
            String unionid = wxUser.getUnionid();
            User user = new User();
            user.setAvatar(wxUser.getHeadimgurl());
            user.setWxNickname(wxUser.getNickname());
            sr.setStatus(AppCons.SR_SUCCESS);
            sr.setEntity(user);
            return sr;
        }
        return null;
    }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param session
     * @return
     */
    @RequestMapping(value = "/sms-captcha/send", method = RequestMethod.GET)
    public @ResponseBody
    SR verifyCodeSend(String mobile, HttpSession session) {
        SR sr = new SR();
        if (!RegExpValidator.isMobile(mobile)) {
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("手机号码不正确");
            return sr;
        }
        sr = captchaService.codeGenValidate(smsCaptchaKey, session, captchaType);
        if (sr.getStatus().equals(AppCons.SR_FAIL)) {
            return sr;
        }
        String code = captchaService.genCaptchaCode(smsCaptchaKey, session, captchaType);
        sr = smsService.sendSms(mobile, new String[]{code}, AppCons.SMS_TYPE_REGISTER);
        return sr;
    }

    /**
     * 绑定手机号
     *
     * @param ruser
     * @param session
     * @return
     */
    @RequestMapping(value = "/mobile/bind", method = RequestMethod.POST)
    public @ResponseBody
    SR<User> bindMobile(RegisterUser ruser, HttpSession session) {
        SR<User> sr = new SR<User>();
        sr.setStatus(AppCons.SR_FAIL);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            if (auth.isAuthenticated()) {
                sr.setMsg("您已登录");
                return sr;
            }
        }
        String mobile = ruser.getMobile();
        if (StringUtils.isEmpty(mobile) || !RegExpValidator.isMobile(mobile)) {
            sr.setMsg("手机格式不正确");
            return sr;
        }
        boolean mobileUnique = registerService.mobileUnique(mobile);
        if (!mobileUnique) {
            sr.setMsg("手机号码已存在");
            return sr;
        }
        String mobileVerifyCode = ruser.getMobileVerifyCode();
        if (StringUtils.isEmpty(mobileVerifyCode) || !RegExpValidator.isMobileVerifyCode(mobileVerifyCode)) {
            sr.setMsg("手机验证码格式不正确");
            return sr;
        }
        SR<String> validateSr = captchaService.validateCode(smsCaptchaKey, mobileVerifyCode, session);
        if (validateSr.getStatus().equals(AppCons.SR_FAIL)) {
            sr.setMsg(validateSr.getMsg());
            return sr;
        }

        String password = ruser.getPassword();
//        if(StringUtils.isEmpty(password)||!RegExpValidator.isPassword(password)){
//            sr.setMsg("密码格式不正确");
//            return sr;
//        }
        String encryptedPassword = MD5Util.string2MD5(password);
        ruser.setPassword(encryptedPassword);
        SessionOperation opt = (SessionOperation) session.getAttribute(SessionOperation.KEY);
        ThirdLoginInfo info = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
        WxmpUserInfo wxUser = null;
        if (opt != null) {
            wxUser = opt.getWxUser();
        }

        int couponQuantity = 0;
        try {
            if (info != null) {// 通过网站注册
                boolean isUserExist = userService.isUserExistByWxOpenid(info.getUserinfo().getOpenid());
                if (!isUserExist) {
                    registerService.registerUser(info.getUserinfo());
                }
                User user = registerService.bindMobile(info.getUserinfo(), ruser);
                sr = loginService.setLoginUser(info.getUserinfo().getUnionid());
                couponQuantity = registerService.giveCoupon(user.getId(), UserConst.USER_TYPE_SELF, null);
                session.removeAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
                JSONObject extraInfo = new JSONObject();
                extraInfo.put("couponQuantity", couponQuantity);
                sr.setExtraInfo(extraInfo);
            } else if (wxUser != null) {// 通过服务号注册
                registerService.registerUserByWxmp(wxUser);
                ThirdLoginUserinfo thirdLoginUserinfo = new ThirdLoginUserinfo();
                thirdLoginUserinfo.setUnionid(wxUser.getUnionid());
                User registeredUser = registerService.bindMobile(thirdLoginUserinfo, ruser);
                sr = loginService.setLoginUser(thirdLoginUserinfo.getUnionid());

                String optType = opt.getType();
                if (!StringUtils.isEmpty(optType) && optType.equals(SessionOperation.TYPE_GET_COUPON)) {
                    // 通过邀请注册 - 朗读秀
                    couponQuantity = registerService.giveCoupon(registeredUser.getId(), UserConst.USER_TYPE_INVITEE, opt.getRefId());
                    registerService.addInviter(registeredUser.getId(), opt.getInitiatorId());
                    int integral = registerService.giveIntegralToInviter(opt.getInitiatorId(), registeredUser.getId());
                    userService.updateUserPoint(opt.getInitiatorId());
                    opt.setPage(SessionOperation.PAGE_READ_SHARE_REGISTER);
                    opt.setMsg("现金券已领取");
                } else { // 自己注册
                    couponQuantity = registerService.giveCoupon(registeredUser.getId(), UserConst.USER_TYPE_SELF, null);
                }

                JSONObject extraInfo = new JSONObject();
                extraInfo.put("couponQuantity", couponQuantity);
                sr.setExtraInfo(extraInfo);
            } else {
                sr.setMsg("请重新登录");
                return sr;
            }
        } finally {
            session.removeAttribute(AppCons.SESSION_KEY_SMS_CAPTCHA_REGISTER);
        }

        return sr;
    }

    /**
     * 重定向
     *
     * @param state
     * @param request
     * @param response
     * @param thirdLoginMsg
     */
    private void redirectTo(String state, HttpServletRequest request, HttpServletResponse response, String thirdLoginMsg) {
        String url = "";
        try {
            String contextPath = request.getContextPath();
            if (StringUtils.isEmpty(contextPath)) {
                url = "/#" + state;
            } else {
                url = contextPath + "/#" + state;
            }
            if (!StringUtils.isEmpty(thirdLoginMsg)) {
                url += "?thirdLoginMsg=" + thirdLoginMsg;
            }
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
