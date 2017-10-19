package com.wally.hiread.service.user;

import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.model.order.Coupon;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.model.user.*;
import com.wally.hiread.service.order.CouponService;
import com.wally.hiread.service.sys.SetupService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class RegisterService {
	@Autowired
	UserService userService;
	@Autowired
	UserMapper userMapper;
	@Autowired
	SetupService setupService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserPointService userPointService;

	public boolean mobileUnique(String mobile) {
		User user = userService.loadUserByMobile(mobile);
		if(user==null){
			return true;
		}
		return false;
	}

	/**
	 * 通过网站应用注册
	 *
	 * @param thirdLoginUser
	 * @return
	 */
	public User registerUser(ThirdLoginUserinfo thirdLoginUser){
		User user = userService.loadUserByUnionId(thirdLoginUser.getUnionid());

		if(user == null){
			User newUser = new User();
			Date now = new Date();

			newUser.setId(UUID.randomUUID().toString());
			newUser.setDatecreated(now);
			newUser.setDatemodified(now);
			newUser.setRegisterDate(DateUtil.dateToString(now, "yyyy-MM-dd HH:mm:ss"));
			newUser.setActive("false");
			newUser.setRegisterType("wxmobile");
			newUser.setWxOpenid(thirdLoginUser.getOpenid());
			newUser.setWxUnionid(thirdLoginUser.getUnionid());
			newUser.setWxNickname(thirdLoginUser.getNickname());
			newUser.setAvatar(thirdLoginUser.getHeadimgurl());
			newUser.setAvatarLink(thirdLoginUser.getHeadimgurl());
			newUser.setPoint("0");

			userMapper.insert(newUser);

			return newUser;
		} else {
			return user;
		}
	}

	/**
	 * 通过服务号注册
	 *
	 * @param wxmpUserInfo
	 * @return
	 */
	public User registerUserByWxmp(WxmpUserInfo wxmpUserInfo){
		User user = userService.loadUserByUnionId(wxmpUserInfo.getUnionid());

		if(user == null){
			User newUser = new User();
			Date now = new Date();

			newUser.setId(UUID.randomUUID().toString());
			newUser.setDatecreated(now);
			newUser.setDatemodified(now);
			newUser.setRegisterDate(DateUtil.dateToString(now, "yyyy-MM-dd HH:mm:ss"));
			newUser.setActive("false");
			newUser.setRegisterType("wxfwmobile");
			newUser.setWxfwOpenid(wxmpUserInfo.getOpenid());
			newUser.setWxUnionid(wxmpUserInfo.getUnionid());
			newUser.setWxNickname(wxmpUserInfo.getNickname());
			newUser.setAvatar(wxmpUserInfo.getHeadimgurl());
			newUser.setAvatarLink(wxmpUserInfo.getHeadimgurl());
			newUser.setPoint("0");

			userMapper.insert(newUser);

			return newUser;
		} else {
			return user;
		}
	}
	public User bindMobile(ThirdLoginUserinfo thirdLoginUser, RegisterUser registerUser){
		User user = userService.loadUserByUnionId(thirdLoginUser.getUnionid());

		if(user != null){
			Date now = new Date();
			user.setDatemodified(now);
			user.setMobile(registerUser.getMobile());
			user.setPassword(registerUser.getPassword());
			user.setActive("true");

			userMapper.updateByPrimaryKeySelective(user);
		}

		return user;
	}

	/**
	 * 赠送现金券
	 *
	 * @param userId
	 * @param userType
	 */
	public int giveCoupon(String userId, String userType,String refId){
		Setup setup = setupService.loadSetup();
		Date nowDate = DateUtil.currentDate();
		SimpleDateFormat f=new SimpleDateFormat(AppCons.DATE_FORMAT);

		int expiry = 0;
		String endDate = "";
		try{
			expiry = Integer.parseInt(setup.getRegisterCouponExpiryDate());
		} catch (NumberFormatException e){
			e.printStackTrace();
		} finally {
			Calendar cal = Calendar.getInstance();
			cal.setTime(nowDate);
			cal.add(Calendar.DATE, expiry);
			Date dt = cal.getTime();
			endDate = f.format(dt);
		}
		Coupon coupon = new Coupon();
		coupon.setId(UUID.randomUUID().toString());
		coupon.setDatecreated(nowDate);
		coupon.setDatemodified(nowDate);
		String amount=null;
		String getType=null;
		if(userType.equals(UserConst.USER_TYPE_INVITEE)){
			amount=setup.getInvitedRegisterCoupon();
			getType="inviteRegister";
		}else if(userType.equals(UserConst.USER_TYPE_SELF)){
			amount=setup.getNewRegisterCoupon();
			getType="register";
		}
		coupon.setAmount(amount);
		coupon.setUserId(userId);
		coupon.setGetType(getType);
		coupon.setUsed("0");
		coupon.setStartDate(f.format(nowDate));
		coupon.setEndDate(endDate);
		coupon.setReferenceId(refId);
		couponService.addCoupon(coupon);

		User user = new User();
		user.setId(userId);
		user.setDatemodified(nowDate);
		user.setCouponId(coupon.getId());
		userService.updateByPrimaryKey(user);

		int iAmount = 0;
		try {
            iAmount = Integer.parseInt(amount);
        } catch (NumberFormatException e) {}


		return iAmount;
	}

	/**
	 * 添加邀请人
	 *
	 * @param userId
	 * @param inviterId
	 */
	public void addInviter(String userId, String inviterId){
		User user = new User();
		Date nowDate = DateUtil.currentDate();
		user.setId(userId);
		user.setDatemodified(nowDate);
		user.setInviteBy(inviterId);
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 赠送积分给邀请者
	 *
	 * @param inviterId
	 * @param inviteeId
	 */
	public int giveIntegralToInviter(String inviterId, String inviteeId){
		Date nowDate = DateUtil.currentDate();
		Setup setup = setupService.loadSetup();
		String registerInviterPoint = setup.getRegisterInviterPoint();
		UserPoint userPoint = new UserPoint();
		userPoint.setId(UUID.randomUUID().toString());
		userPoint.setDatecreated(nowDate);
		userPoint.setDatemodified(nowDate);
		userPoint.setEventType("邀请奖励");
		userPoint.setPoint(registerInviterPoint);
		userPoint.setUserId(inviterId);
		userPoint.setReferenceId(inviteeId);
		userPointService.addPoint(userPoint);

		return Integer.parseInt(registerInviterPoint);
	}

	/**
	 * 添加成员服务号 openid
	 *
	 * @param wxmpOpenid
	 * @param unionid
	 */
	public void updateWxmpOpenid(String wxmpOpenid, String unionid){
		User user = userService.loadUserByUnionId(unionid);
		if(user != null){
			user.setDatemodified(DateUtil.currentDate());
			user.setWxfwOpenid(wxmpOpenid);
			userService.updateByPrimaryKey(user);
		}
	}
	
}
