package com.wally.hiread.service.user;

import com.wally.hiread.dao.user.UserStudyLogMapper;
import com.wally.hiread.model.user.UserStudyLog;
import com.wally.hiread.model.user.UserStudyLogExample;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserStudyLogService {
    @Autowired
    UserStudyLogMapper studyLogMapper;

    public List<UserStudyLog> todayListForUnitSection(String userId,String productId,String unitId,String studySection){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserStudyLogExample e=new UserStudyLogExample();
        UserStudyLogExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andUnitIdEqualTo(unitId);
        c.andProductIdEqualTo(productId);
        c.andStudySectionEqualTo(studySection);
        c.andStudyDateEqualTo(df.format(now));
        return studyLogMapper.selectByExample(e);
    }
    public long totalTimeForUnitSection(String userId,String productId,String unitId,String studySection,boolean onlyFirstRound) {
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserStudyLogExample e = new UserStudyLogExample();
        UserStudyLogExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andUnitIdEqualTo(unitId);
        c.andProductIdEqualTo(productId);
        c.andStudySectionEqualTo(studySection);
        if(onlyFirstRound){
            c.andSnEqualTo("1");
        }
        List<UserStudyLog> list = studyLogMapper.selectByExample(e);
        long totalTime=getTotalTime(list);
        return totalTime;
    }

    public int userTotalStudyMin(String userId,String productId,String studyDate){
        UserStudyLogExample e = new UserStudyLogExample();
        UserStudyLogExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        c.andStudyDateEqualTo(studyDate);
        List<UserStudyLog> list = studyLogMapper.selectByExample(e);
        long totalTime=getTotalTime(list);
        int min=new BigDecimal(totalTime/60.0).setScale(0,BigDecimal.ROUND_CEILING).intValue();
        return min;
    }
    private long getTotalTime(List<UserStudyLog> studyLogs){
        long totalTime=0;
        for(UserStudyLog us:studyLogs){
            String times = us.getTimes();
            try{
                long timesl=Long.parseLong(times);
                totalTime+=timesl;
            }catch(Exception ex){

            }
        }
        return totalTime;
    }

    //今日未完成log
    public UserStudyLog todayUncompletedForUnitSection(String userId,String productId,String unitId,String studySection){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserStudyLogExample e=new UserStudyLogExample();
        UserStudyLogExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andUnitIdEqualTo(unitId);
        c.andProductIdEqualTo(productId);
        c.andStudySectionEqualTo(studySection);
        c.andStudyDateEqualTo(df.format(now));
        c.andCompletedEqualTo("0");
        List<UserStudyLog> userStudyLogs = studyLogMapper.selectByExample(e);
        if(userStudyLogs==null||userStudyLogs.size()==0){
            return null;
        }
        return userStudyLogs.get(0);
    }
    //今日最新轮次号
    public int todayLatestSnForUnitSection(String userId,String productId,String unitId,String studySection){
        Date now=new Date();
        SimpleDateFormat df=new SimpleDateFormat(AppCons.DATE_FORMAT);
        UserStudyLogExample e=new UserStudyLogExample();
        UserStudyLogExample.Criteria c = e.createCriteria();
        c.andUnitIdEqualTo(userId);
        c.andProductIdEqualTo(unitId);
        c.andStudySectionEqualTo(studySection);
        c.andStudyDateEqualTo(df.format(now));
        e.setOrderByClause("cast(c_sn as unsigned) desc");
        List<UserStudyLog> userStudyLogs = studyLogMapper.selectByExample(e);
        if(userStudyLogs==null||userStudyLogs.size()==0){
            return 0;
        }
        String sn=userStudyLogs.get(0).getSn();
        try{
            return Integer.parseInt(sn);
        }catch(Exception ex){
            return 0;
        }
    }

    public UserStudyLog insert(UserStudyLog log){
        Date now=new Date();
        log.setId(UUID.randomUUID().toString());
        log.setDatecreated(now);
        log.setDatemodified(now);
        studyLogMapper.insert(log);
        return log;
    }

    public UserStudyLog update(UserStudyLog log){
        Date now=new Date();
        log.setDatemodified(now);
        studyLogMapper.updateByPrimaryKey(log);
        return log;
    }

}
