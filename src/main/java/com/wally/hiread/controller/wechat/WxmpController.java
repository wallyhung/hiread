package com.wally.hiread.controller.wechat;

import com.wally.hiread.components.jave.AudioConverter;
import com.wally.hiread.components.wxmp.WxmpConst;
import com.wally.hiread.components.wxmp.material.MaterialManager;
import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.components.wxmp.webpage.authorization.service.WebpageAuthorizationService;
import com.wally.hiread.components.wxmp.webpage.jssdk.JssdkConfig;
import com.wally.hiread.model.product.UserRead;
import com.wally.hiread.model.product.UserReadCom;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.SessionOperation;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.ReadShareService;
import com.wally.hiread.service.product.UserReadService;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.RegisterService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.setting.sys.AppCons;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by eric on 07/06/2017.
 */
@Controller
@RequestMapping(value = "/wx/mp")
public class WxmpController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    Properties serverConfig;
    @Autowired
    MaterialManager materialManager;
    @Autowired
    JssdkConfig jssdkConfig;
    @Autowired
    WebpageAuthorizationService webpageAuthorizationService;
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    @Autowired
    ReadShareService readShareService;
    @Autowired
    RegisterService registerService;
    @Autowired
    UserReadService urService;

    /**
     * 上传语音文件
     *
     * @param mediaId
     * @return
     */
    @RequestMapping(value = "/audio/upload", method = RequestMethod.POST)
    public @ResponseBody
    SR uploadAudio(String mediaId) {
        SR sr = new SR();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("您未登录");
            return sr;
        }

        try {
            String filename = materialManager.downloadTempMaterial(WxmpConst.TEMP_MATERIAL_FORMAT_AUDIO, mediaId);
            String baseDir = serverConfig.getProperty("file.save.path.audio");
            File sourceFile = new File(baseDir + File.separator + filename);
            File targetDir = new File(baseDir);
            AudioConverter.convertAmr2Mp3(sourceFile, targetDir);
        } catch (Exception e) {
            e.printStackTrace();
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("系统错误");
        }

        sr.setStatus(AppCons.SR_SUCCESS);
        sr.setMsg(AppCons.SR_MSG_OK);

        return sr;
    }

    @RequestMapping(value = "/jssdk-config", method = RequestMethod.GET)
    public @ResponseBody
    SR<JssdkConfig> authConfiguration(String url) {
        SR<JssdkConfig> sr = new SR<>();

        try {
            jssdkConfig = jssdkConfig.getAuthConfiguration(url);
            sr.setStatus(AppCons.SR_SUCCESS);
            sr.setMsg(AppCons.SR_MSG_OK);
            sr.setEntity(jssdkConfig);
        } catch (Exception e) {
            e.printStackTrace();
            sr.setStatus(AppCons.SR_FAIL);
        }

        return sr;
    }

    /**
     * 网页授权回调
     *
     * @param session
     * @param code
     * @param state
     */
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void callback(HttpServletResponse response, HttpSession session, String code, String state) {
        log.info("wx mp callback process..");
        String urlBase = serverConfig.getProperty("app.url.base");
        String redirectUrl =urlBase+ "product/readShare/page/shared";
        Map accessTokenMap = webpageAuthorizationService.getWebpageAuthorizationAccessToken(code);
        SessionOperation opt=(SessionOperation)session.getAttribute(SessionOperation.KEY);
        if(opt==null){
            opt=new SessionOperation();
        }
        opt.setMsg("");
        session.setAttribute(SessionOperation.KEY,opt);

        try {
            if(accessTokenMap==null){
                opt.setMsg("access token未取得");
                response.sendRedirect(redirectUrl);
                return;
            }
            String accessToken = (String) accessTokenMap.get("access_token");
            String openid = (String) accessTokenMap.get("openid");
            WxmpUserInfo wxUser = webpageAuthorizationService.getUserInfo(accessToken, openid);
            String page = opt.getPage();
            String type = opt.getType();
            String refId = opt.getRefId();
            String initiatorId = opt.getInitiatorId();

            User user=userService.loadUserByUnionId(wxUser.getUnionid());
            if(user!=null){
                SR<User> userSR = loginService.setLoginUser(user.getWxUnionid());//自动登录
                registerService.updateWxmpOpenid(wxUser.getOpenid(), wxUser.getUnionid());
            }
            opt.setWxUser(wxUser);

            if(SessionOperation.PAGE_READ_SHARE_AUTH.equals(page)){//录音分享验证
                redirectUrl+="?userReadId="+refId;
                if(SessionOperation.TYPE_SHARE.equals(type)){ //分享人扫码
                    User initiator = userService.loadUserById(initiatorId);
                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    if(!initiator.getWxUnionid().equals(wxUser.getUnionid())){
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('"+"录音分享扫码人必须是本人"+"');");
                        out.println("</script>");
                        return;
                    }
                }else{
                    opt.setMsg("不支持的操作");
                    response.sendRedirect(redirectUrl);
                    return;
                }
            }else if(SessionOperation.PAGE_READ_SHARE.equals(page)){//录音分享页面操作
                redirectUrl+="?userReadId="+refId;
                UserRead ur=urService.load(refId);
                if(SessionOperation.TYPE_PRAISE.equals(type)){//点赞
                    SR srLike=readShareService.like(refId,wxUser);
                    if(srLike.getStatus().equals(SR.FAIL)){
                        opt.setMsg(srLike.getMsg());
                    }
                }else if(SessionOperation.TYPE_COMMENT.equals(type)){//评论
                    SR<UserReadCom> srComment = readShareService.comment(refId, wxUser, opt.getParam());
                    if(srComment.getStatus().equals(SR.FAIL)){
                        opt.setMsg(srComment.getMsg());
                    }

                }else if(SessionOperation.TYPE_GET_COUPON.equals(type)){//领券
                    //已注册提示
                    if(user!=null){
                        opt.setMsg("您已注册，无法领券");
                        response.sendRedirect(redirectUrl);
                        return;
                    }
                    //未注册跳转到注册页面
                    String userId=ur.getUserId();
                    opt.setInitiatorId(userId);
                    opt.setPage(SessionOperation.PAGE_READ_SHARE);
                    opt.setRefId(ur.getId());
                    opt.setType(type);
                    opt.setParam("");
                    opt.setMsg("");
                    String registerUrl = urlBase + "user/register/step2";
                    response.sendRedirect(registerUrl);
                    return;

                }else{
                    opt.setMsg("不支持的操作");
                    response.sendRedirect(redirectUrl);
                    return;
                }

            }else{
                opt.setMsg("操作不支持");
                response.sendRedirect(redirectUrl);
                return;
            }
            response.sendRedirect(redirectUrl);
            return;


        } catch (IOException e) {
            log.error(e.getMessage(),e);
            try {
                opt.setMsg(e.getMessage());
                response.sendRedirect(redirectUrl);
            } catch (IOException e1) {
                log.error(e1.getMessage(),e1);
            }
            return;
        }
    }

}
