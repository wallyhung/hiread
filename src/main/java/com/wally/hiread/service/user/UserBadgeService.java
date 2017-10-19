package com.wally.hiread.service.user;

import com.wally.hiread.dao.user.UserBadgeMapper;
import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.model.product.Practise;
import com.wally.hiread.model.product.PractiseUser;
import com.wally.hiread.model.product.UserRead;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserBadge;
import com.wally.hiread.model.user.UserBadgeExample;
import com.wally.hiread.model.user.UserBadgePanel;
import com.wally.hiread.service.product.PractiseUserService;
import com.wally.hiread.service.product.UserReadService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.TimeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserBadgeService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserBadgeMapper ubMapper;
    @Autowired
    private PractiseUserService puService;
    @Autowired
    private UserStudyLogService studyLogService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserReadService urService;


    public List<UserBadge> greatStartBadgeProcess(String userId,String productId,String greateStartType){
        List<UserBadge> badges=new ArrayList<>();
        UserBadge badgeGreateStart = this.findBadgeGreateStart(userId, productId, greateStartType);
        if(badgeGreateStart==null){
            UserBadge ub = this.insert(userId, UserBadge.TYPE_GREAT_START, null + "", null, null, productId, null,null,greateStartType);
            badges.add(ub);
        }
        return badges;
    }

    public List<UserBadge> unitCompleteBadgeProcess(String userId,String productId,String unitId,String unitSection){
        List<UserBadge> badges=new ArrayList<>();
        UserBadge badgeUnitSection = this.findBadgeUnitSection(userId, productId, unitId, unitSection);
        if(badgeUnitSection==null){
            UserBadge ub = this.insert(userId, UserBadge.TYPE_UNIT_COMPLETE, null + "", null, unitId, productId, null,unitSection,null);
            badges.add(ub);
        }
        return badges;
    }

    public List<UserBadge> recordingBadgeProcess(String userId,String productId,String unitId){
        List<UserBadge> badges=new ArrayList<>();
        Date today=new Date();
        List<UserRead> todayReads = urService.userReadList(userId, productId, today);
        if(todayReads!=null&&todayReads.size()>0){
            List<UserBadge> list = findBadgeToday(userId, productId,UserBadge.TYPE_DAILY_RECORDING);
            if(list==null||list.size()==0){
                UserBadge ub = this.insert(userId, UserBadge.TYPE_DAILY_RECORDING, null + "", null, unitId, productId, null,null,null);
                badges.add(ub);
            }

            list = findBadgeToday(userId, productId,UserBadge.TYPE_CONSISTENT_RECORDING);
            if(list==null||list.size()==0){
                boolean consistentRead=true;
                for(int i=0;i<4;i++){
                    Date dayBefore=TimeUtil.getDaysBefore(today,i+1);
                    List<UserRead> dayBeforeReads = urService.userReadList(userId, productId, dayBefore);
                    if(dayBeforeReads==null||dayBeforeReads.size()==0){
                        consistentRead=false;
                    }
                }
                if(consistentRead){
                    UserBadge ub = this.insert(userId, UserBadge.TYPE_CONSISTENT_RECORDING, null + "", null, unitId, productId, null,null,null);
                    badges.add(ub);
                }
            }
        }
        return badges;
    }
    public List<UserBadge> studyTimeBadgeProcess(String userId,String productId){
        List<UserBadge> badges=new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        int todayStudyMin=studyLogService.userTotalStudyMin(userId,productId,df.format(now));
        if(todayStudyMin>=25){
            List<UserBadge> list = findBadgeToday(userId, productId,UserBadge.TYPE_DAILY_STUDY);
            if(list==null||list.size()==0){
                UserBadge ub = this.insert(userId, UserBadge.TYPE_DAILY_STUDY, null + "", null, null, productId, null,null,null);
                badges.add(ub);
            }
        }
        if(todayStudyMin>=15){
            List<UserBadge> list = findBadgeToday(userId,productId, UserBadge.TYPE_CONSISTENT);
            if(list==null||list.size()==0){
                Date yesterDay=TimeUtil.getDaysBefore(now,1);
                int yesterDayStudyMin=studyLogService.userTotalStudyMin(userId,productId,df.format(yesterDay));
                if(yesterDayStudyMin>=15){
                    Date beforeYesterday=TimeUtil.getDaysBefore(now,2);
                    int beforeYesterdayStudyMin=studyLogService.userTotalStudyMin(userId,productId,df.format(beforeYesterday));
                    if(beforeYesterdayStudyMin>=15){
                        UserBadge ub = this.insert(userId,UserBadge.TYPE_CONSISTENT,null+"",null,null,productId,null,null,null);
                        badges.add(ub);
                    }
                }
            }
        }
        return badges;
    }

    public List<UserBadge> accurateBadgeProcess(PractiseUser pu,List<Practise> practises){
        List<UserBadge> badges=new ArrayList<UserBadge>();
        String practiseUserId=pu.getId();
        Practise practise=null;
        Practise practiseBefore=null;
        for(int i=0;i<practises.size();i++){
            Practise p=practises.get(i);
            if(pu.getPractiseId().equals(p.getId())){
                practise=p;
                if(i>0){
                    practiseBefore=practises.get(i-1);
                }
            }
        }
        int accurateNum=practise.getAccurateNum();
        if(practise==null||practiseBefore==null||accurateNum<5){
            return badges;
        }
        PractiseUser firstTimeCorrectPractiseUserBefore=practiseBefore.getFirstTimeCorrectPractiseUser();
        if(firstTimeCorrectPractiseUserBefore==null){
            log.error("accurate badge process error:firstTimeCorrectPractiseUser null or firstTimeCorrectPractiseUserBefore null");
            return badges;
        }

        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        if(accurateNum==5){
            if(pu.getFirstTime().equals("1")){
                UserBadge ub = this.insert(pu.getUserId(), UserBadge.TYPE_ACCURATE, accurateNum + "", null, pu.getUnitId(), pu.getProductId(), practiseUserId,null,null);
                badges.add(ub);
            }
        }else{
            List<UserBadge> accurateUserBadges=findBadgeByPractiseUser(firstTimeCorrectPractiseUserBefore,UserBadge.TYPE_ACCURATE);
            if(accurateUserBadges==null||accurateUserBadges.size()!=1){
                log.error("accurate badge process error:user badge before not exist or more than one,puid:"+pu.getId());
                return badges;
            }
            UserBadge accurateUserBadgeBefore=accurateUserBadges.get(0);
            if(!accurateUserBadgeBefore.getAccurateNum().equals((accurateNum-1)+"")){
                log.error("accurate badge process error:user badge before accurate num not correct,puid:"+pu.getId());
            }
            accurateUserBadgeBefore.setDatemodified(now);
            accurateUserBadgeBefore.setAccurateNum(accurateNum+"");
            accurateUserBadgeBefore.setGetDate(df.format(now));
            accurateUserBadgeBefore.setPractiseUserId(pu.getId());
            ubMapper.updateByPrimaryKey(accurateUserBadgeBefore);

            if(accurateNum>=10){
                if(accurateNum==10||accurateNum==15||accurateNum==20){
                    UserBadge ub=this.insert(pu.getUserId(),UserBadge.TYPE_ACCURATE_ADDITIONAL,accurateNum+"",null,pu.getUnitId(),pu.getProductId(),practiseUserId,null,null);
                    badges.add(ub);
                }
                if(accurateNum>10){
                    List<UserBadge> accurateAdditionalUserBadges=findBadgeByPractiseUser(firstTimeCorrectPractiseUserBefore,UserBadge.TYPE_ACCURATE_ADDITIONAL);
                    if(accurateAdditionalUserBadges==null||accurateAdditionalUserBadges.size()==0){
                        log.error("accurate addtional badge process error:user badge before not exist,puid:"+pu.getId());
                        return badges;
                    }
                    for(UserBadge accurateAdditionalUserBadgeBefore:accurateAdditionalUserBadges){
                        if(!accurateAdditionalUserBadgeBefore.getAccurateNum().equals((accurateNum-1)+"")){
                            log.error("accurate additional badge process error:user badge before accurate num not correct,puid:"+pu.getId());
                        }
                        accurateAdditionalUserBadgeBefore.setDatemodified(now);
                        accurateAdditionalUserBadgeBefore.setAccurateNum(accurateNum+"");
                        accurateAdditionalUserBadgeBefore.setGetDate(df.format(now));
                        accurateAdditionalUserBadgeBefore.setPractiseUserId(pu.getId());
                        ubMapper.updateByPrimaryKey(accurateAdditionalUserBadgeBefore);
                    }
                }
            }
        }
        return badges;


    }

    private String getCategory(String type){
        String category="";
        if(type.equals(UserBadge.TYPE_UNIT_COMPLETE)
                ||type.equals(UserBadge.TYPE_ACCURATE)
                ||type.equals(UserBadge.TYPE_ACCURATE_ADDITIONAL)
                ||type.equals(UserBadge.TYPE_SUBJECT_SPECIALIST)){
            category=UserBadge.CATEGORY_ACADEMIC;
        }else if(type.equals(UserBadge.TYPE_DAILY_STUDY)
                ||type.equals(UserBadge.TYPE_CONSISTENT)
                ||type.equals(UserBadge.TYPE_GREAT_START)
                ||type.equals(UserBadge.TYPE_DAILY_RECORDING)
                ||type.equals(UserBadge.TYPE_CONSISTENT_RECORDING)){
            category=UserBadge.CATEGORY_BEHAVIOR;
        }
        return category;
    }

    public UserBadge insert(String userId,String type,String accurateNum,String subject,String unitId,String productId,String practiseUserId,String unitSection,String greatStartType){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserBadge badge=new UserBadge();
        badge.setId(UUID.randomUUID().toString());
        badge.setDatecreated(now);
        badge.setDatemodified(now);
        badge.setGetDate(df.format(now));
        badge.setUserId(userId);
        badge.setCategory(this.getCategory(type));
        badge.setType(type);
        badge.setAccurateNum(accurateNum);
        badge.setSubject(subject);
        badge.setUnitId(unitId);
        badge.setProductId(productId);
        badge.setPractiseUserId(practiseUserId);
        badge.setUnitSection(unitSection);
        badge.setGreatStartType(greatStartType);
        ubMapper.insert(badge);
        return badge;
    }

    public List<UserBadge> findBadgeByPractiseUser(PractiseUser firstTimeCorrectPractiseUserBefore,String type){
        UserBadgeExample e=new UserBadgeExample();
        UserBadgeExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(firstTimeCorrectPractiseUserBefore.getUserId());
        c.andTypeEqualTo(type);
        c.andProductIdEqualTo(firstTimeCorrectPractiseUserBefore.getProductId());
        c.andUnitIdEqualTo(firstTimeCorrectPractiseUserBefore.getUnitId());
        c.andPractiseUserIdEqualTo(firstTimeCorrectPractiseUserBefore.getId());
        List<UserBadge> userBadges = ubMapper.selectByExample(e);
        return userBadges;
    }

    public UserBadge findBadgeUnitSection(String userId,String productId,String unitId,String unitSection){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserBadgeExample e=new UserBadgeExample();
        UserBadgeExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        c.andUnitSectionEqualTo(unitSection);
        c.andTypeEqualTo(UserBadge.TYPE_UNIT_COMPLETE);
        if(!StringUtils.isEmpty(unitId)){
            c.andUnitIdEqualTo(unitId);
        }
        List<UserBadge> userBadges = ubMapper.selectByExample(e);
        if(userBadges!=null&&userBadges.size()==1){
            return userBadges.get(0);
        }
        return null;
    }
    public UserBadge findBadgeGreateStart(String userId,String productId,String greatStartType){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserBadgeExample e=new UserBadgeExample();
        UserBadgeExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        c.andTypeEqualTo(UserBadge.TYPE_GREAT_START);
        c.andGreatStartTypeEqualTo(greatStartType);
        List<UserBadge> userBadges = ubMapper.selectByExample(e);
        if(userBadges!=null&&userBadges.size()==1){
            return userBadges.get(0);
        }
        return null;
    }
    public List<UserBadge> findBadgeToday(String userId,String productId,String type){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserBadgeExample e=new UserBadgeExample();
        UserBadgeExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        c.andTypeEqualTo(type);
        c.andGetDateEqualTo(df.format(now));
        List<UserBadge> userBadges = ubMapper.selectByExample(e);
        return userBadges;
    }

    public UserBadgePanel getPanel(String userId,String productId){
        UserBadgePanel panel=new UserBadgePanel();
        this.setUserBadges(userId,productId,panel);
        this.setBadgeUsers(productId,panel);
        return panel;
    }
    public void setBadgeUsers(String productId,UserBadgePanel panel){
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        String weekStart = df.format(TimeUtil.getWeekStart());
        String  weekEnd = df.format(TimeUtil.getWeekEnd());
        Map<String,String> map=new HashMap<>();
        map.put("productId",productId);
        map.put("category","behavior");
        List<User> badgeBehaviorAllUsers = userMapper.selectBadgeCount(map);
        panel.setBadgeBehaviorAllUsers(badgeBehaviorAllUsers);
        map.put("weekStart",weekStart);
        map.put("weekEnd",weekEnd);
        List<User> badgeBehaviorWeekUsers = userMapper.selectBadgeCount(map);
        panel.setBadgeBehaviorWeekUsers(badgeBehaviorWeekUsers);

        map.put("category","academic");
        map.put("weekStart",null);
        map.put("weekEnd",null);
        List<User> badgeAcademicAllUsers = userMapper.selectBadgeCount(map);
        panel.setBadgeAcademicAllUsers(badgeAcademicAllUsers);
        map.put("weekStart",weekStart);
        map.put("weekEnd",weekEnd);
        List<User> badgeAcademicWeekUsers = userMapper.selectBadgeCount(map);
        panel.setBadgeAcademicWeekUsers(badgeAcademicWeekUsers);

    }

    public void setUserBadges(String userId,String productId,UserBadgePanel panel){
        UserBadgeExample e=new UserBadgeExample();
        UserBadgeExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        List<UserBadge> userBadges = ubMapper.selectByExample(e);
        List<UserBadge> userWeekBadges=new ArrayList<UserBadge>();
        List<UserBadge> userAllBadges=new ArrayList<UserBadge>();
        SimpleDateFormat dateFormat=new SimpleDateFormat(AppCons.DATE_FORMAT);
        for(UserBadge b:userBadges){
            String getDate = b.getGetDate();
            Date date=null;
            try {
                date=dateFormat.parse(getDate);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            boolean inWeek = TimeUtil.inWeek(date);
            if(inWeek){
                accumulateBadge(b,userWeekBadges);
            }
            accumulateBadge(b,userAllBadges);
        }
        panel.setUserWeekBadges(userWeekBadges);
        panel.setUserAllBadges(userAllBadges);
    }
    private void accumulateBadge(UserBadge source,List<UserBadge> targetContainer){
        boolean updated=false;
        for(UserBadge target:targetContainer){
            String sourceType=source.getType();
            String targetType=target.getType();
            if(sourceType.equals(UserBadge.TYPE_ACCURATE)){
                String sourceAccurateNum=source.getAccurateNum();
                String targetAccurateNum=target.getAccurateNum();
                if(sourceType.equals(targetType)&&sourceAccurateNum.equals(targetAccurateNum)){
                    target.setCount(target.getCount()+1);
                    updated=true;
                    break;
                }
            }
            else{
                if(sourceType.equals(targetType)){
                    target.setCount(target.getCount()+1);
                    updated=true;
                    break;
                }
            }
        }
        if(!updated){
            UserBadge badge=new UserBadge();
            badge.setCount(1);
            badge.setAccurateNum(source.getAccurateNum());
            badge.setType(source.getType());
            targetContainer.add(badge);
        }

    }


}
