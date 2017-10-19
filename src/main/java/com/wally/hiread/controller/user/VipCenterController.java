package com.wally.hiread.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.model.order.CouponConst;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserPasswordUpdateVO;
import com.wally.hiread.model.user.UserPoint;
import com.wally.hiread.model.user.UserPointExt;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.sys.SecurityManager;
import com.wally.hiread.service.sys.SecurityUserManager;
import com.wally.hiread.service.user.UserPointService;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.util.DateUtil;
import com.wally.hiread.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by eric on 21/06/2017.
 */
@Controller
@RequestMapping(value = "/user/vipcenter")
public class VipCenterController {
    @Autowired
    UserService userService;
    @Autowired
    CouponService couponService;
    @Autowired
    UserPointService userPointService;

    @RequestMapping(value = {"/index", ""})
    public String index() {

        return "user/vipcenter/index";
    }

    @RequestMapping(value = "/profile-setting/get")
    public @ResponseBody
    SR<User> getProfileSetting() {
        SR<User> sr = new SR<User>();
        User user = SecurityUserManager.getUser();
        if (user != null) {
            sr.setStatus(SR.SUCCESS);
            sr.setEntity(user);
        } else {
            sr.setStatus(SR.FAIL);
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            sr.setMsg("未登录");
        }

        return sr;
    }

    @RequestMapping(value = "/profile-setting/update", method = RequestMethod.POST)
    public @ResponseBody
    SR updateProfileSetting(@RequestBody User user) {
        SR<User> sr = new SR<User>();
        if (SecurityManager.isLogin()) {
            userService.updateUser(user);
            sr.setStatus(SR.SUCCESS);
        } else {
            sr.setStatus(SR.FAIL);
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
            sr.setMsg("未登录");
        }
        return sr;
    }

    @RequestMapping(value = "/my-coupon/list", method = RequestMethod.POST)
    public @ResponseBody
    SR<JSONObject> listMyCoupon(String type) {
        SR sr = new SR();
        JSONObject coupons = new JSONObject();
        User user = SecurityUserManager.getUser();

        if (type != null) {
            String[] types = type.split(";");
            for (int i = 0; i < types.length; i++) {
                type = types[i];
                if (CouponConst.COUPON_TYPE_ALL.equals(type)) {
                    coupons.put(CouponConst.COUPON_TYPE_ALL,
                            JSONArray.parseArray(JSON.toJSONString(couponService.listCoupon(CouponConst.COUPON_STATUS_NOT_USE, user.getId()))));
                }
                if (CouponConst.COUPON_TYPE_NEAR_EXPIRED.equals(type)) {
                    coupons.put(CouponConst.COUPON_TYPE_NEAR_EXPIRED,
                            JSONArray.parseArray(JSON.toJSONString(couponService.listNearExpiredCoupon(CouponConst.COUPON_STATUS_NOT_USE, user.getId()))));
                }
            }
            sr.setEntity(coupons);
            sr.setStatus(SR.SUCCESS);
        } else {
            sr.setStatus(SR.FAIL);
            sr.setFailType(SR.FAIL_TYPE_REQUEST_PARAMETER_ERROR);
        }

        return sr;
    }

    @RequestMapping(value = "/my-integral/get")
    public @ResponseBody SR<JSONObject> getMyIntegral(){
        SR sr = new SR();
        JSONObject integral = new JSONObject();

        User user = SecurityUserManager.getUser();
        if(user != null){
            String userId = user.getId();
            int validCount = userPointService.myUserPointsSum(userId);
            int totalCount = userPointService.countTotalPoint(userId);
            int consumedCount = userPointService.countConsumedPoint(userId);
            List<UserPoint> userPoints = userPointService.myUserPoints(userId);
            integral.put("validCount", validCount);
            integral.put("totalCount", totalCount);
            integral.put("consumedCount", consumedCount);
            integral.put("items", JSONArray.parseArray(JSON.toJSONString(userPoints)));

            sr.setStatus(SR.SUCCESS);
            sr.setEntity(integral);
            return sr;
        } else {
           sr.setStatus(SR.FAIL);
           sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);

           return sr;
        }
    }
    @RequestMapping(value = "/my-invitation/get")
    public @ResponseBody SR<JSONObject> getMyInvitation(){
        SR sr = new SR();
        JSONObject invitation = new JSONObject();

        User user = SecurityUserManager.getUser();
        if(user != null){
            String userId = user.getId();
            List<UserPointExt> userPointExts = userPointService.listMyInvitationUserPoint(userId);
            int total = userPointService.countInvitation(userId);
            invitation.put("total", total);
            invitation.put("items", JSONArray.parseArray(JSON.toJSONString(userPointExts)));

            sr.setStatus(SR.SUCCESS);
            sr.setEntity(invitation);

            return sr;
        } else {
            sr.setStatus(SR.FAIL);
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);

            return sr;
        }
    }
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public @ResponseBody SR<JSONObject> resetPassword(@RequestBody UserPasswordUpdateVO userPasswordUpdateVO){
        SR sr = new SR();

        String oldPassword = userPasswordUpdateVO.getOldPassword();
        String newPassword = userPasswordUpdateVO.getNewPassword();
        String newPasswordRepeat = userPasswordUpdateVO.getNewPasswordRepeat();

        User authUser = SecurityUserManager.getUser();
        if(authUser != null){
            String userId = authUser.getId();
            if(newPassword != null && newPassword.equals(newPasswordRepeat)){
                String encryptedPassword = MD5Util.string2MD5(newPassword);
                User user = userService.getUserByPrimaryKey(userId);
                String encryptedOldPassword = MD5Util.string2MD5(oldPassword);
                if(encryptedOldPassword.equals(user.getPassword())){
                    User userUpdate = new User();
                    userUpdate.setId(user.getId());
                    userUpdate.setDatemodified(DateUtil.currentDate());
                    userUpdate.setPassword(encryptedPassword);
                    userService.updateByPrimaryKey(userUpdate);

                    sr.setStatus(SR.SUCCESS);
                } else {
                    sr.setStatus(SR.FAIL);
                    sr.setFailType(SR.FAIL_TYPE_BIZ_INPUT_ILLEGAL);
                }
            } else {
                sr.setStatus(SR.FAIL);
                sr.setFailType(SR.FAIL_TYPE_BIZ_INPUT_ILLEGAL);
            }
        } else {
            sr.setStatus(SR.FAIL);
            sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
        }

        return sr;
    }
}
