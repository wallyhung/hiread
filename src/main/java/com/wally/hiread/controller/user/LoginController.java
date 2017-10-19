package com.wally.hiread.controller.user;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.ThirdLoginInfo;
import com.wally.hiread.model.user.ThirdLoginUserinfo;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.sys.CaptchaService;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.service.user.WxService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.CaptchaUtil;
import com.wally.hiread.util.RegExpValidator;


@Controller
@RequestMapping("/user/login")
public class LoginController {
    @Autowired
    LogService logService;
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    CaptchaService capService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    WxService wxService;

    private String imageCaptchaKey = AppCons.SESSION_KEY_IMAGE_CAPTCHA_LOGIN;
    private String captchaType = AppCons.CAPTCHA_TYPE_IMG;
    private CaptchaUtil cutil = new CaptchaUtil();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/refreshLoginUser", method = RequestMethod.GET)
    public @ResponseBody
    SR<User> refreshLoginUser() {
        SR<User> sr = loginService.refreshLoginUser();
        return sr;
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response, HttpSession session) {
        SR<String> sr = new SR<String>();
        sr = capService.codeGenValidate(imageCaptchaKey, session, captchaType);
        if (sr.getStatus().equals(AppCons.SR_FAIL)) {
            return;
        }
        String code = capService.genCaptchaCode(imageCaptchaKey, session, captchaType);
        BufferedImage image = cutil.getCaptchaImg(code);
        try {
            response.setContentType("image/png");
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (Exception e) {
            logService.systemError(AppCons.SYSTEM_ERROR_RUNTIME, e.getMessage(), e);
        }
        return;
    }

    @RequestMapping(value = "/loginFailTimes", method = RequestMethod.GET)
    public @ResponseBody
    SR<Integer> loginFailTimes(HttpSession session) {
        SR<Integer> sr = new SR<Integer>();
        Integer loginFailTimes = (Integer) session.getAttribute(AppCons.SESSION_KEY_LOGIN_FAIL_TIMES);
        if (loginFailTimes == null) {
            loginFailTimes = 0;
        }
        sr.setEntity(loginFailTimes);
        sr.setStatus(AppCons.SR_SUCCESS);
        return sr;
    }

    /**
     * 登录
     *
     * @param loginType
     * @param mobile
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/{loginType}", method = RequestMethod.POST)
    public @ResponseBody
    SR<User> login(@PathVariable String loginType, String mobile, String password, HttpSession session) {
        SR<User> sr = new SR<User>();
        sr.setStatus(AppCons.SR_FAIL);
        SR<User> validateResult = loginService.validate(mobile, password);
        if (validateResult.getStatus().equals(AppCons.SR_FAIL)) {
            sr.setMsg(validateResult.getMsg());
            return sr;
        }

        if (loginType.equals(AppCons.REGISTER_BIND_TYPE_BIND_WX)) {
            ThirdLoginInfo info = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
            if (info == null) {
                sr.setMsg("请重新登录");
                return sr;
            }
            User user = wxService.getUser(info.getUnionId());
            if (user != null) {
                sr.setMsg("已有账号绑定了该微信,请先解除原有账号的绑定");
                return sr;
            }
        }

        User user = validateResult.getEntity();
        sr = loginService.setLoginUser(user.getWxUnionid());

        if (loginType.equals(AppCons.REGISTER_BIND_TYPE_BIND_WX)) {
            ThirdLoginInfo info = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
            SR thirdLoginInfoBindSR = wxService.thirdLoginInfoBind(user, info);
            if (thirdLoginInfoBindSR.getStatus().equals(AppCons.SR_FAIL)) {
                sr.setMsg(thirdLoginInfoBindSR.getMsg());
                return sr;
            }
            session.removeAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
        }
        return sr;
    }

    @RequestMapping(value = "/thirdLoginUserinfo", method = RequestMethod.GET)
    public @ResponseBody
    SR<ThirdLoginUserinfo> thirdLoginInfo(HttpServletResponse response, HttpSession session) {
        SR<ThirdLoginUserinfo> sr = new SR<ThirdLoginUserinfo>(AppCons.SR_FAIL, "获取第三方登录用户信息失败");
        ThirdLoginInfo info = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
        if (info == null) {
            return sr;
        }
        sr.setEntity(info.getUserinfo());
        sr.setStatus(AppCons.SR_SUCCESS);
        return sr;
    }

    @RequestMapping(value = "/wx/requestLoginUrl", method = RequestMethod.GET)
    public @ResponseBody
    SR<ThirdLoginInfo> wxrequestLoginUrl(HttpServletResponse response, HttpSession session) {
        SR<ThirdLoginInfo> sr = new SR<ThirdLoginInfo>(AppCons.SR_FAIL, "获取微信登录url失败");
        ThirdLoginInfo info = wxService.initLoginInfo();

        String redirectUri = info.getRedirectUri();
        info.setRedirectUri(redirectUri);
        String state = session.getId();
        info.setState(state);

        sr.setStatus(AppCons.SR_SUCCESS);
        sr.setMsg(AppCons.SR_MSG_OK);
        sr.setEntity(info);

        session.setAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO, info);

        return sr;
    }

    @RequestMapping(value = "/wx/redirectProcess", method = RequestMethod.GET)
    public void wxrequestCodeUrl(HttpServletRequest request, HttpServletResponse response, HttpSession session, String code, String state) throws UnsupportedEncodingException {
        ThirdLoginInfo info = (ThirdLoginInfo) session.getAttribute(AppCons.SESSION_KEY_THIRD_LOGIN_INFO);
        if (info == null) {
            redirectTo(state, request, response, URLEncoder.encode("会话超时，请重新登录", "utf-8"));
            return;
        }
        String stateInSession = info.getState();
        if (StringUtils.isEmpty(state) || StringUtils.isEmpty(stateInSession) || !state.equals(stateInSession) || StringUtils.isEmpty(code)) {
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
        //如果是从个人中心微信绑定过来，直接绑定
        if (state.equals("/user/member/memberUserBind")) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof AnonymousAuthenticationToken) {
                redirectTo("/user/login", request, response, URLEncoder.encode("您没有登录", "utf-8"));
                return;
            }
            User u = (User) auth.getDetails();

            User user = wxService.getUser(info.getUnionId());
            if (user != null) {
                redirectTo(state, request, response, URLEncoder.encode("已有账号绑定了该微信,请先解除原有账号的绑定", "utf-8"));
                return;
            }

            SR srbind = wxService.thirdLoginInfoBind(u, info);
            if (srbind.getStatus().equals(AppCons.SR_FAIL)) {
                redirectTo(state, request, response, URLEncoder.encode(srbind.getMsg(), "utf-8"));
                return;
            }
            redirectTo(state, request, response, "");
            return;
        }
        //如果绑定过直接登录
        User user = wxService.getUser(info.getUnionId());
        if (user != null) {
            loginService.setLoginUser(user.getMobile());
            //从登录或注册页过来跳到首页
            if (state.equals("/user/login") || state.equals("/user/register")) {
                redirectTo("", request, response, "");
                return;
            } else {//其余跳到原来页面
                redirectTo(state, request, response, "");
                return;
            }

        }

        redirectTo("/user/registerThird", request, response, "");
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
