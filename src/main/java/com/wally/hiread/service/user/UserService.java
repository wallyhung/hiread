package com.wally.hiread.service.user;

import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.model.user.Questionnaire;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserExample;
import com.wally.hiread.model.user.UserExample.Criteria;
import com.wally.hiread.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserPointService upService;

    public User loadUserByMobile(String mobile) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andMobileEqualTo(mobile);
        List<User> users = userMapper.selectByExample(e);
        if (users != null && users.size() == 1) {
            return users.get(0);
        }
        return null;

    }

    public User loadUserByUnionId(String unionid) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andWxUnionidEqualTo(unionid);
        List<User> users = userMapper.selectByExample(e);

        return (users != null && users.size() > 0) ? users.get(0) : null;
    }

    public User loadUserById(String id) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(e);

        return (users != null && users.size() > 0) ? users.get(0) : null;
    }

    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int updateUser(User user) {
        Date date = DateUtil.currentDate();
        user.setDatemodified(date);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User getUserByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户是否已注册
     *
     * @param wxmpOpenid
     * @return
     */
    public boolean isRegistered(String wxmpOpenid) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andWxfwOpenidEqualTo(wxmpOpenid);
        c.andActiveEqualTo("true");
        List<User> users = userMapper.selectByExample(e);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    public void updateUserPoint(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        int sum = upService.myUserPointsSum(userId);
        user.setPoint(sum+"");
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 是否通过微信公众号绑定了手机号
     *
     * @param wxmpOpenid
     * @return
     */
    public boolean isMobileBoundByWxmp(String wxmpOpenid) {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andWxfwOpenidEqualTo(wxmpOpenid)
                .andMobileIsNotNull()
                .andMobileNotEqualTo("");
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否通过微信网站应用绑定了手机号
     *
     * @param wxwebsiteOpenid
     * @return
     */
    public boolean isMobileBoundByWxwebsite(String wxwebsiteOpenid) {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andWxOpenidEqualTo(wxwebsiteOpenid)
                .andMobileIsNotNull()
                .andMobileNotEqualTo("");
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否通过微信开放平台绑定了手机号。
     * 注：这里表示通过微信开放平台上关联的网站应用或服务号绑定了手机号，有唯一的用户身份 id(unionid)。
     *
     * @param wxunionid
     * @return
     */
    public boolean isMobileBoundByWxopen(String wxunionid) {
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andWxUnionidEqualTo(wxunionid)
                .andMobileIsNotNull()
                .andMobileNotEqualTo("");
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户的网站 openid 判断用户是否存在
     *
     * @param wxOpenid
     * @return
     */
    public boolean isUserExistByWxOpenid(String wxOpenid) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andWxOpenidEqualTo(wxOpenid);
        List<User> users = userMapper.selectByExample(e);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户的网站 openid 判断用户是否有效
     *
     * @param wxOpenid
     * @return
     */
    public boolean isUserValidByWxOpenid(String wxOpenid) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andWxOpenidEqualTo(wxOpenid)
                .andActiveEqualTo("true");
        List<User> users = userMapper.selectByExample(e);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isUserValidByUnionid(String unionid) {
        UserExample e = new UserExample();
        Criteria c = e.createCriteria();
        c.andWxUnionidEqualTo(unionid)
                .andActiveEqualTo("true");
        List<User> users = userMapper.selectByExample(e);
        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 保存调查问卷信息
     *
     * @param questionnaire
     */
    public void saveQuestionnaire(Questionnaire questionnaire, User user) {
        Date date = DateUtil.currentDate();
        user.setDatemodified(date);
        user.setStudSchoolType(questionnaire.getQuestion1());
        user.setStudEnglishYearSchool(questionnaire.getQuestion2());
        user.setStudEnglishYearTraining(questionnaire.getQuestion3());
        user.setStudEnglishWeekHour(questionnaire.getQuestion4());
        user.setStudExtraEnglish(questionnaire.getQuestion5());
        user.setStudExtraEnglishBook(questionnaire.getQuestion6());
        user.setStudReadBook(questionnaire.getQuestion7());
        user.setStudReadBookAddition(questionnaire.getQuestion7Addition());
        user.setStudInterestTopic(questionnaire.getQuestion8());
        user.setStudInterestTopicAddition(questionnaire.getQuestion8Addition());
        user.setStudExtraRemark(questionnaire.getQuestion9());
        user.setStudSex(questionnaire.getQuestion10Gender());
        user.setStudEnglishName(questionnaire.getQuestion10EnglishName());
        user.setStudBirthday(questionnaire.getQuestion10Birthday());
        user.setQuestionnairePoint(String.valueOf(questionnaire.getScore()));
        userMapper.updateByPrimaryKeySelective(user);
    }


}
