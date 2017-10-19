package com.wally.hiread.controller.product;

import com.wally.hiread.components.wxmp.webpage.authorization.service.WebpageAuthorizationService;
import com.wally.hiread.model.product.ReadPractise;
import com.wally.hiread.model.product.UserProduct;
import com.wally.hiread.model.product.UserRead;
import com.wally.hiread.model.product.UserReadShare;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.service.product.ReadPractiseService;
import com.wally.hiread.service.product.UnitService;
import com.wally.hiread.service.product.UserReadService;
import com.wally.hiread.service.user.LoginService;
import com.wally.hiread.service.user.RegisterService;
import com.wally.hiread.service.user.UserProductService;
import com.wally.hiread.service.user.UserService;
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
@RequestMapping("/product/readPractise")
public class ClassReadPractiseController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    UserProductService userProductService;
    @Autowired
    ReadPractiseService rpService;
    @Autowired
    UserReadService urService;
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    @Autowired
    WebpageAuthorizationService webpageAuthorizationService;
    @Autowired
    RegisterService registerService;
    @Autowired
    ProductService productService;
    @Autowired
    UnitService unitService;

    @RequestMapping(value = "/readPractises", method = RequestMethod.GET)
    public @ResponseBody
    SR<List<ReadPractise>> everyDayRead(String userProductId) {
        SR<List<ReadPractise>> sr = new SR<List<ReadPractise>>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user = (User) auth.getDetails();
        String userId = user.getId();

        UserProduct up = userProductService.myUserProduct(userId, userProductId, false);
        if (up == null) {
            sr.setMsg("用户商品不存在");
            return sr;
        }
        List<ReadPractise> readPractises = rpService.readPractiseList(userId,up.getProductId());
        sr.setEntity(readPractises);
        sr.setStatus(SR.SUCCESS);
        return sr;

    }

    @RequestMapping(value = "/userRead", method = RequestMethod.POST)
    public @ResponseBody
    SR<UserRead> userRead(String readPractiseId) {
        SR<UserRead> sr = new SR<UserRead>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user = (User) auth.getDetails();
        String userId = user.getId();

        ReadPractise rp = rpService.load(readPractiseId);
        if (rp == null) {
            sr.setMsg("录音练习不存在");
            return sr;
        }

        UserRead ur = new UserRead();
        ur.setUserId(user.getId());
        ur.setReadPractiseId(rp.getId());
        ur = urService.insert(ur);

        sr.setEntity(ur);
        sr.setStatus(SR.SUCCESS);
        return sr;

    }

    @RequestMapping(value = "/userReads", method = RequestMethod.GET)
    public @ResponseBody
    SR<List<UserRead>> userReads(String productId) {
        SR<List<UserRead>> sr = new SR<List<UserRead>>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user = (User) auth.getDetails();
        String userId = user.getId();

        List<UserRead> userReads = urService.userReadList(userId, productId,null);
        sr.setEntity(userReads);
        sr.setStatus(SR.SUCCESS);
        return sr;

    }

    //点击分享处理
    @RequestMapping(value = "/userReadShare", method = RequestMethod.POST)
    public @ResponseBody
    SR<UserReadShare> userReadShare(String userReadId) {
        SR<UserReadShare> sr = new SR<UserReadShare>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            sr.setMsg("请登录");
            sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            return sr;
        }
        User user = (User) auth.getDetails();
        String userId = user.getId();
        UserReadShare userReadShare = urService.userReadShare(userId, userReadId);
        sr.setEntity(userReadShare);
        sr.setStatus(SR.SUCCESS);
        return sr;

    }

}
