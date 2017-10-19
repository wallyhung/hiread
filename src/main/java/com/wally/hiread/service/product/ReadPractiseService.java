package com.wally.hiread.service.product;

import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.dao.product.ReadPractiseMapper;
import com.wally.hiread.dao.product.UserReadComMapper;
import com.wally.hiread.dao.product.UserReadLikeMapper;
import com.wally.hiread.model.product.*;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ReadPractiseService {
    @Autowired
    private ReadPractiseMapper rpMapper;
    @Autowired
    UserReadLikeMapper userReadLikeMapper;
    @Autowired
    UserReadService userReadService;
    @Autowired
    UserService userService;
    @Autowired
    UserReadComMapper userReadComMapper;
    @Autowired
    ProductService productService;
    //获取每日录音练习
    public ReadPractise getEveryDayRead(UserProduct userProduct) {
        String productId = userProduct.getProduct().getId();
        ReadPractiseExample e = new ReadPractiseExample();
        ReadPractiseExample.Criteria c = e.createCriteria();
        c.andProductIdEqualTo(productId);
        List<ReadPractise> readPractises = rpMapper.selectByExample(e);
        if (readPractises != null && readPractises.size() > 0) {
            return readPractises.get(0);
        }
        return null;
    }

    public ReadPractise load(String id) {
        ReadPractiseExample e = new ReadPractiseExample();
        ReadPractiseExample.Criteria c = e.createCriteria();
        c.andIdEqualTo(id);
        List<ReadPractise> readPractises = rpMapper.selectByExample(e);
        if (readPractises != null && readPractises.size() > 0) {
            return readPractises.get(0);
        }
        return null;
    }

    public List<ReadPractise> readPractiseList(String userId,String productId) {
        if(StringUtils.isEmpty(productId)){
            return null;
        }
        Product prod=productService.load(productId,true);
        if(prod==null){
            return null;
        }
        List<Unit> units = prod.getUnits();
        List<String> unitIds=new ArrayList<String>();
        for(Unit unit:units){
            unitIds.add(unit.getId());
        }
        if(unitIds.size()==0){
            return null;
        }

        ReadPractiseExample e = new ReadPractiseExample();
        ReadPractiseExample.Criteria c = e.createCriteria();
        c.andUnitIdIn(unitIds);
        List<ReadPractise> readPractises = rpMapper.selectByExample(e);
        for (ReadPractise rp : readPractises) {
            List<UserRead> userReads = userReadService.userReadListByReadPractiseId(userId,rp.getId());
            rp.setUserReads(userReads);
        }
        return readPractises;
    }

    /**
     * 朗读秀点赞
     *
     * @param userReadId 朗读秀 id
     * @param wxmpUserInfo   点赞人微信身份信息
     */
    public void like(String userReadId, User user,WxmpUserInfo wxmpUserInfo) {
        String wxfwOpenid = user.getWxfwOpenid();
        String wxUnionid = user.getWxUnionid();
        String wxNickname = user.getWxNickname();
        String wxAvatar = user.getAvatarLink();
        /* 添加点赞记录 */
        Date nowDate = DateUtil.currentDate();
        UserReadLike userReadLike = new UserReadLike();
        userReadLike.setId(UUID.randomUUID().toString());
        userReadLike.setDatecreated(nowDate);
        userReadLike.setDatemodified(nowDate);
        userReadLike.setUserReadId(userReadId);
        userReadLike.setWxfwOpenid(wxfwOpenid);
        userReadLike.setWxNickname(wxNickname);
        userReadLike.setWxAvatar(wxAvatar);
        userReadLike.setWxUnionid(wxUnionid);
        userReadLike.setUserId(user.getId());
        userReadLikeMapper.insert(userReadLike);

        /* 更新点赞数量 */
        int likeCount=this.likeCount(userReadId);
        UserRead userRead = userReadService.load(userReadId);
        userRead.setDatemodified(nowDate);
        userRead.setLike(likeCount+"");
        userReadService.update(userRead);
    }

    public int likeCount(String userReadId){
        UserReadLikeExample e=new UserReadLikeExample();
        UserReadLikeExample.Criteria c = e.createCriteria();
        c.andUserReadIdEqualTo(userReadId);
        return userReadLikeMapper.countByExample(e);
    }

    /**
     * 朗读秀评论
     *
     * @param userReadId
     * @param wxmpUserInfo
     * @param content
     */
    public void comment(String userReadId, WxmpUserInfo wxmpUserInfo, String content) {
        /* 添加评论记录 */
        Date nowDate = DateUtil.currentDate();
        User commentUser = userService.loadUserByUnionId(wxmpUserInfo.getUnionid());
        UserReadCom userReadCom = new UserReadCom();
        userReadCom.setId(UUID.randomUUID().toString());
        userReadCom.setDatecreated(nowDate);
        userReadCom.setDatemodified(nowDate);
        userReadCom.setUserReadId(userReadId);
        userReadCom.setWxfwOpenid(wxmpUserInfo.getOpenid());
        userReadCom.setWxNickname(wxmpUserInfo.getNickname());
        userReadCom.setWxAvatar(wxmpUserInfo.getHeadimgurl());
        userReadCom.setWxUnionid(wxmpUserInfo.getUnionid());
        userReadCom.setComment(content);
        if (commentUser != null) {
            userReadCom.setUserId(commentUser.getId());
        }
        userReadComMapper.insertSelective(userReadCom);

        /* 更新评论数量 */
        UserRead userRead = userReadService.load(userReadId);
        String commentNum = userRead.getComment();
        try {
            int iCommentNum = Integer.parseInt(commentNum);
            UserRead newUserRead = new UserRead();
            newUserRead.setId(userRead.getId());
            newUserRead.setDatemodified(nowDate);
            newUserRead.setComment(String.valueOf(iCommentNum + 1));
            userReadService.updateByPrimaryKeySelective(newUserRead);
        } catch (NumberFormatException e) {
        }
    }

    /**
     * 朗读秀领券
     */
    public void takeCoupon(){

    }


}
