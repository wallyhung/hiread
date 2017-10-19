package com.wally.hiread.controller.product;

import com.wally.hiread.components.wxmp.webpage.authorization.model.WebpageAuthorizationConst;
import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.components.wxmp.webpage.authorization.service.WebpageAuthorizationService;
import com.wally.hiread.model.product.*;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.SessionOperation;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.*;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.RegisterService;
import com.wally.hiread.service.user.UserProductService;
import com.wally.hiread.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/product/readShare")
public class ReadShareController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    UserProductService userProductService;
    @Autowired
    ReadPractiseService rpService;
    @Autowired
    UserReadService urService;
    @Autowired
    LoginService loginService;
    @Autowired
    WebpageAuthorizationService webpageAuthorizationService;
    @Autowired
    RegisterService registerService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    UnitService unitService;
    @Autowired
    Properties serverConfig;
    @Autowired
    ReadShareService readShareService;

    private void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        if (!StringUtils.isEmpty(contextPath)) {
            url = contextPath + url;
        }
        response.sendRedirect(url);
    }

    //录音分享人扫码验证
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public void share(Model model, String userReadId, String sign, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        String urlBase = serverConfig.getProperty("app.url.base");
        String shareUrl =urlBase+ "product/readShare/page/shared?userReadId=" + userReadId;
        try {
            SessionOperation opt=(SessionOperation)session.getAttribute(SessionOperation.KEY);
            if(opt==null){
                opt=new SessionOperation();
            }
            opt.setPage(SessionOperation.PAGE_READ_SHARE_AUTH);
            opt.setMsg("");
            session.setAttribute(SessionOperation.KEY,opt);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (StringUtils.isEmpty(userReadId) || StringUtils.isEmpty(sign)) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('"+"参数不正确"+"');");
                out.println("</script>");
                return;
            }
            urService.clearExpired();
            UserReadShare userReadShare = urService.getUserReadShare(userReadId);
            if (userReadShare == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('"+"二维码已过期"+"');");
                out.println("</script>");
                return;
            }
            String userId=userReadShare.getUserId();
            String sign1 = userReadShare.getSign();
            if (!sign.equals(sign1)) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('"+"非法请求"+"');");
                out.println("</script>");
                return;
            }

            //已登录验证是否是同一个人
            SR<User> sr=loginService.refreshLoginUser();
            if(sr.getStatus().equals(SR.SUCCESS)){
                User user = sr.getEntity();
                //不是同一人
                if(!user.getId().equals(userId)){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('"+"您没有权限"+"');");
                    out.println("</script>");
                    return;
                }
                //是同一人
                response.sendRedirect(shareUrl);
                return;
            }

            //未登录已授权
            if(opt.getWxUser()!=null){
                User user = userService.getUserByPrimaryKey(userId);
                //不是同一人
                if(!user.getId().equals(userId)){
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('"+"您没有权限"+"');");
                    out.println("</script>");
                    return;
                }
                //是同一人
                response.sendRedirect(shareUrl);
                return;
            }
            //未登录未授权
            opt.setInitiatorId(userId);
            opt.setPage(SessionOperation.PAGE_READ_SHARE_AUTH);
            opt.setRefId(userReadShare.getUserReadId());
            opt.setType(SessionOperation.TYPE_SHARE);

            //生成微信授权链接
            String redirect_uri=urlBase+"wx/mp/callback";
            String wxAuthUrl=webpageAuthorizationService.generateReferenceUrl(redirect_uri,WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_SCOPE_USERINFO,null);
            log.info("wx auth url:"+wxAuthUrl);
            response.sendRedirect(wxAuthUrl);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @RequestMapping(value = "/page/shared", method = RequestMethod.GET)
    public String toShared(String userReadId,HttpSession session) {
        return "product/class-user-read-shared";
    }

    //获取userReadShare
    @RequestMapping(value = "/getUserReadShare", method = RequestMethod.GET)
    public @ResponseBody
    SR<UserReadShare> userRead(String userReadId,HttpSession session){
        SR<UserReadShare> sr=new SR<UserReadShare>();
        UserRead ur=urService.load(userReadId);
        if(ur==null){
            sr.setMsg("录音不存在");
            return sr;
        }
        //记录session
        SessionOperation opt=(SessionOperation)session.getAttribute(SessionOperation.KEY);
        if(opt==null) {
            opt = new SessionOperation();
        }
        String userId=ur.getUserId();
        opt.setInitiatorId(userId);
        opt.setPage(SessionOperation.PAGE_READ_SHARE);
        opt.setRefId(ur.getId());
        opt.setType("");
        opt.setMsg("");
        session.setAttribute(SessionOperation.KEY,opt);

        UserReadShare urs=new UserReadShare();
        urs.setUserId(userReadId);
        User user = userService.loadUserById(userId);
        user.setPassword(null);
        urs.setUser(user);
        urs.setUserReadId(ur.getId());
        int commentCount = readShareService.countComment(userReadId);
        urs.setCommentCount(commentCount);
        int likeCount = readShareService.countLike(userReadId);
        urs.setLikeCount(likeCount);
        List<UserReadCom> userReadComs = readShareService.getComments(userReadId);
        urs.setUserReadComs(userReadComs);
        ReadPractise readPractise = rpService.load(ur.getReadPractiseId());
        urs.setReadPractise(readPractise);
        String unitId = readPractise.getUnitId();
        Unit unit=unitService.load(unitId,false,false,false,false,false);
        Product product = productService.load(unit.getProductId(), false);
        urs.setProduct(product);

        sr.setEntity(urs);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    //右上角分享操作
    @RequestMapping(value = "/shared", method = RequestMethod.POST)
    public @ResponseBody SR shared(String userReadId) {
        SR sr=new SR();
        urService.shared(userReadId);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    //页面操作
    @RequestMapping(value = "/operation", method = RequestMethod.POST)
    public @ResponseBody
    SR<String> likeEveryDayRead(HttpSession session, String userReadId,String type,String param) {
        SR<String> sr=new SR<String>();
        UserRead ur=urService.load(userReadId);
        if(ur==null){
            sr.setMsg("录音不存在");
            return sr;
        }
        if(StringUtils.isEmpty(type)){
            sr.setMsg("参数缺失");
            return sr;
        }
        if(!type.equals(SessionOperation.TYPE_PRAISE)
                &&!type.equals(SessionOperation.TYPE_COMMENT)
                &&!type.equals(SessionOperation.TYPE_GET_COUPON)){
            sr.setMsg("操作不支持");
            return sr;
        }

        SR<User> srUser=loginService.refreshLoginUser();
        WxmpUserInfo wxUser = null;
        SessionOperation opt=(SessionOperation)session.getAttribute(SessionOperation.KEY);
        if(opt==null){
            opt = new SessionOperation();
        }
        session.setAttribute(SessionOperation.KEY,opt);

        String urlBase = serverConfig.getProperty("app.url.base");
        if(srUser.getStatus().equals(SR.SUCCESS)||opt.getWxUser()!=null){
            if(srUser.getStatus().equals(SR.SUCCESS)){
                //已登录，从登录用户信息拿到微信信息
                User user=srUser.getEntity();
                wxUser=new WxmpUserInfo();
                wxUser.setOpenid(user.getWxfwOpenid());
                wxUser.setUnionid(user.getWxUnionid());
                wxUser.setNickname(user.getWxNickname());
                wxUser.setHeadimgurl(user.getAvatarLink());
                wxUser.setUserId(user.getId());
            }else if(opt.getWxUser()!=null){
                //未登录已授权，拿到授权信息
                wxUser=opt.getWxUser();
            }
            if(type.equals(SessionOperation.TYPE_PRAISE)){
                SR srLike=readShareService.like(userReadId,wxUser);
                if(srLike.getStatus().equals(SR.FAIL)){
                    sr.setMsg(srLike.getMsg());
                    return sr;
                }
            }else if(type.equals(SessionOperation.TYPE_COMMENT)){
                if(StringUtils.isEmpty(param)){
                    sr.setMsg("内容不能为空");
                    return sr;
                }
                if(param.length()>300){
                    sr.setMsg("内容过长");
                    return sr;
                }
                SR srComment=readShareService.comment(userReadId,wxUser,param);
                if(srComment.getStatus().equals(SR.FAIL)){
                    sr.setMsg(srComment.getMsg());
                    return sr;
                }
            }else if(type.equals(SessionOperation.TYPE_GET_COUPON)){
                User user = userService.loadUserByUnionId(wxUser.getUnionid());
                //已授权，已注册
                if(user!=null){
                    sr.setMsg("您已注册，无法领券");
                    return sr;
                }
                //已授权，未注册,跳转到注册页面
                String userId=ur.getUserId();
                opt.setInitiatorId(userId);
                opt.setPage(SessionOperation.PAGE_READ_SHARE);
                opt.setRefId(ur.getId());
                opt.setType(type);
                opt.setParam("");
                opt.setMsg("");
                String registerUrl = urlBase + "user/register/step2";
                sr.setStatus(SR.FAIL);
                sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
                sr.setEntity(registerUrl);
                return sr;

            }
            sr.setStatus(SR.SUCCESS);
            return sr;

        }
        //未登录未授权，设置session
        String userId=ur.getUserId();
        opt.setInitiatorId(userId);
        opt.setPage(SessionOperation.PAGE_READ_SHARE);
        opt.setRefId(ur.getId());
        opt.setType(type);
        opt.setParam(param);
        opt.setMsg("");
        //生成微信授权链接
        String redirect_uri=urlBase+"wx/mp/callback";
        String wxAuthUrl=webpageAuthorizationService.generateReferenceUrl(redirect_uri,WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_SCOPE_USERINFO,null);
        sr.setStatus(SR.FAIL);
        sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
        sr.setEntity(wxAuthUrl);
        return sr;
    }




}
