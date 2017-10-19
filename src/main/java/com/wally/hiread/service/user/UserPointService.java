package com.wally.hiread.service.user;

import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.ProductMapper;
import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.dao.user.UserPointMapper;
import com.wally.hiread.model.user.*;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.model.user.UserPoint;
import com.wally.hiread.model.user.UserPointExample;
import com.wally.hiread.service.product.PractiseOptService;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.service.sys.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserPointService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PractiseMapper practiseMapper;
    @Autowired
    private PractiseOptService practiseOptService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserPointMapper userPointMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private SetupService setupService;

    public List<UserPoint> myUserPoints(String userId){
        UserPointExample e=new UserPointExample();
        UserPointExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        List<UserPoint> list = userPointMapper.selectByExample(e);
        for (UserPoint up : list) {
            this.compute(up);
        }
        return list;
    }

    /**
     * 用户有效积分
     * @param userId
     * @return
     */
    public int myUserPointsSum(String userId) {
        int sum = 0;
        List<UserPoint> userPoints = this.myUserPoints(userId);
        for (UserPoint up : userPoints) {
            String p = up.getPoint();
            int point = 0;
            try {
                point = Integer.parseInt(p);
            } catch (Exception e) {
                continue;
            }
            sum += point;
        }
        return sum;

    }

    /**
     * 计算用户总积分
     *
     * @param userId
     * @return
     */
    public int countTotalPoint(String userId) {
        List<UserPoint> userPoints = this.myUserPoints(userId);
        int total = 0;
        int point = 0;
        String sPoint = null;
        for (UserPoint userPoint : userPoints) {
            sPoint = userPoint.getPoint();
            point = Integer.parseInt(sPoint);
            if (point > 0) {
                total += point;
            }
        }

        return total;
    }

    /**
     * 计算用户总消费积分
     * @param userId
     * @return
     */
    public int countConsumedPoint(String userId) {
        List<UserPoint> userPoints = myUserPoints(userId);
        int total = 0;
        int point = 0;
        String sPoint;
        for (UserPoint userPoint : userPoints) {
            sPoint = userPoint.getPoint();
            point = Integer.parseInt(sPoint);
            if(point < 0){
                total += point;
            }
        }

        return total;
    }

    /**
     * 获取邀请注册积分
     * @param userId
     * @return
     */
    public List<UserPointExt> listMyInvitationUserPoint(String userId){
        List<UserPointExt> userPointExts = new ArrayList<UserPointExt>();

        UserPointExample example = new UserPointExample();
        UserPointExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId)
                .andEventTypeEqualTo(UserPointConst.POINT_EVENT_TYPE_INVITATION);
        List<UserPoint> list = userPointMapper.selectByExample(example);
        User user = null;
        UserPointExt userPointExt = null;
        for (UserPoint up : list) {
            up = this.compute(up);
            user = userService.getUserByPrimaryKey(up.getUserId());
            userPointExt = new UserPointExt();
            userPointExt.setId(up.getId());
            userPointExt.setDatecreated(up.getDatecreated());
            userPointExt.setDatemodified(up.getDatemodified());
            userPointExt.setEventType(up.getEventType());
            userPointExt.setPoint(up.getPoint());
            userPointExt.setReferenceId(up.getReferenceId());
            userPointExt.setUserId(up.getUserId());
            userPointExt.setUser(user);

            userPointExts.add(userPointExt);
        }

        return userPointExts;
    }

    /**
     * 获取邀请注册人数
     * @param userId
     * @return
     */
    public int countInvitation(String userId){
        UserPointExample example = new UserPointExample();
        UserPointExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId)
                .andEventTypeEqualTo(UserPointConst.POINT_EVENT_TYPE_INVITATION);

        return userPointMapper.countByExample(example);
    }

    private UserPoint compute(UserPoint up) {
        String point = up.getPoint();
        if (StringUtils.isEmpty(point)) {
            point = "0";
        } else {
            try {
                point = Integer.parseInt(point) + "";
            } catch (Exception e) {
                point = "0";
            }
        }
        up.setPoint(point);
        return up;
    }
    public double myUserPointsSumRmb(String userId){
        int sum=myUserPointsSum(userId);
        Setup setup = setupService.loadSetup();
        double rate=0.1;
        if(setup!=null&&!StringUtils.isEmpty(setup.getUserPointExchangeRate())){
            try {
                rate = Double.parseDouble(setup.getUserPointExchangeRate());
            }catch (Exception ex){}
        }
        return new BigDecimal(sum*rate).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void addPoint(UserPoint userPoint){
        userPointMapper.insertSelective(userPoint);
    }


}
