package com.wally.hiread.controller.wechat;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.ThirdLoginInfo;
import com.wally.hiread.model.user.ThirdLoginUserinfo;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.RegisterService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.service.user.WxService;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by eric on 01/06/2017.
 */
@Controller
@RequestMapping(value = "/wx/open")
public class WxopenController {
    @Autowired
    RegisterService registerService;
    @Autowired
    WxService wxService;
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    /**
     * 微信登录回调
     *
     * @param request
     * @param response
     * @param session
     * @param code
     * @param state
     * @param operation 有效值 login、register
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void callback(HttpServletRequest request, HttpServletResponse response, HttpSession session, String code, String state, String operation) throws UnsupportedEncodingException {
        ThirdLoginInfo info = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
        String sessionState = info.getState();
        if (StringUtils.isEmpty(state) || StringUtils.isEmpty(sessionState) || !state.equals(sessionState) || StringUtils.isEmpty(code)) {
            redirectTo(state, request, response, URLEncoder.encode("微信回调校验未通过", "utf-8"));
            return;
        }
        //获取token
        info.setCode(code);
        SR sr = wxService.setAccessTokenByCode(info);
        if (sr.getStatus().equals(AppCons.SR_FAIL)) {
            redirectTo(state, request, response, URLEncoder.encode(sr.getMsg(), "utf-8"));
            return;
        }
        //获取用户信息
        sr = wxService.setUserInfo(info);
        if (sr.getStatus().equals(AppCons.SR_FAIL)) {
            redirectTo(state, request, response, URLEncoder.encode(sr.getMsg(), "utf-8"));
            return;
        }

        String redirectUri = request.getContextPath();

        ThirdLoginUserinfo thirdLoginUserinfo = info.getUserinfo();
        String openid = thirdLoginUserinfo.getOpenid();
        String unionid = thirdLoginUserinfo.getUnionid();

        boolean isUserValid = userService.isUserValidByUnionid(unionid);
        if (isUserValid) {
            loginService.setLoginUser(info.getUnionId());
            redirectUri += "/index";
        } else {
            redirectUri += "/user/register/step2";
            session.setAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO, info);
        }

        try {
            response.sendRedirect(redirectUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

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
