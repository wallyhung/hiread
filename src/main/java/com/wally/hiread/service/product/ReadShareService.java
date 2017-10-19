package com.wally.hiread.service.product;

import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.dao.product.ReadPractiseMapper;
import com.wally.hiread.dao.product.UserReadComMapper;
import com.wally.hiread.dao.product.UserReadLikeMapper;
import com.wally.hiread.model.product.*;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ReadShareService {
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


    /**
     * 朗读秀点赞
     */
    public SR<UserReadLike> like(String userReadId, WxmpUserInfo user) {
        SR<UserReadLike> sr=new SR<UserReadLike>();
        String wxfwOpenid = user.getOpenid();
        String wxUnionid = user.getUnionid();
        String wxNickname = user.getNickname();
        String wxAvatar = user.getHeadimgurl();
        String userId=user.getUserId();

        int countLike = this.countLike(userReadId, wxUnionid);
        if(countLike>=10){
            sr.setMsg("点赞次数超过限制");
            return sr;
        }
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
        userReadLike.setUserId(userId);
        userReadLikeMapper.insert(userReadLike);

        /* 更新点赞数量 */
        int likeCount=this.countLike(userReadId);
        UserRead userRead = userReadService.load(userReadId);
        userRead.setLike(likeCount+"");
        userReadService.update(userRead);

        sr.setEntity(userReadLike);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    /**
     * 朗读秀评论
     */
    public SR<UserReadCom> comment(String userReadId, WxmpUserInfo user, String content) {
        SR<UserReadCom> sr=new SR<UserReadCom>();
        String wxfwOpenid = user.getOpenid();
        String wxUnionid = user.getUnionid();
        String wxNickname = user.getNickname();
        String wxAvatar = user.getHeadimgurl();
        String userId=user.getUserId();

        int countComment = this.countComment(userReadId, wxUnionid);
        if(countComment>=10){
            sr.setMsg("评论次数超过限制");
            return sr;
        }

        /* 添加评论记录 */
        Date nowDate = DateUtil.currentDate();
        UserReadCom userReadCom = new UserReadCom();
        userReadCom.setId(UUID.randomUUID().toString());
        userReadCom.setDatecreated(nowDate);
        userReadCom.setDatemodified(nowDate);
        userReadCom.setUserReadId(userReadId);

        userReadCom.setWxfwOpenid(wxfwOpenid);
        userReadCom.setWxNickname(wxNickname);
        userReadCom.setWxAvatar(wxAvatar);
        userReadCom.setWxUnionid(wxUnionid);
        userReadCom.setComment(content);
        userReadCom.setUserId(userId);
        userReadComMapper.insert(userReadCom);

        /* 更新评论数量 */
        UserRead userRead = userReadService.load(userReadId);
        int commentCount = this.countComment(userReadId);
        userRead.setComment(commentCount+"");
        userReadService.update(userRead);

        sr.setEntity(userReadCom);
        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    /**
     * 统计点赞数量
     */
    public int countLike(String userReadId){
        UserReadLikeExample example = new UserReadLikeExample();
        UserReadLikeExample.Criteria criteria = example.createCriteria();
        criteria.andUserReadIdEqualTo(userReadId);
        return userReadLikeMapper.countByExample(example);
    }
    public int countLike(String userReadId,String unionId){
        UserReadLikeExample example = new UserReadLikeExample();
        UserReadLikeExample.Criteria criteria = example.createCriteria();
        criteria.andUserReadIdEqualTo(userReadId);
        criteria.andWxUnionidEqualTo(unionId);
        return userReadLikeMapper.countByExample(example);
    }

    /**
     * 统计评论数量
     */
    public int countComment(String userReadId){
        UserReadComExample example = new UserReadComExample();
        UserReadComExample.Criteria criteria = example.createCriteria();
        criteria.andUserReadIdEqualTo(userReadId);
        return userReadComMapper.countByExample(example);
    }
    public int countComment(String userReadId,String unionId){
        UserReadComExample example = new UserReadComExample();
        UserReadComExample.Criteria criteria = example.createCriteria();
        criteria.andUserReadIdEqualTo(userReadId);
        criteria.andWxUnionidEqualTo(unionId);
        return userReadComMapper.countByExample(example);
    }

    /**
     * 获取所有评论
     *
     * @param userReadId
     * @return
     */
    public List<UserReadCom> getComments(String userReadId){
        UserReadComExample example = new UserReadComExample();
        example.setOrderByClause("dateCreated DESC");
        UserReadComExample.Criteria criteria = example.createCriteria();
        criteria.andUserReadIdEqualTo(userReadId);
        return userReadComMapper.selectByExample(example);
    }


}
