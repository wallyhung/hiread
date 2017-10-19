package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UserReadComMapper;
import com.wally.hiread.dao.product.UserReadLikeMapper;
import com.wally.hiread.dao.product.UserReadMapper;
import com.wally.hiread.model.product.ReadPractise;
import com.wally.hiread.model.product.UserRead;
import com.wally.hiread.model.product.UserReadExample;
import com.wally.hiread.model.product.UserReadShare;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.MD5Util;
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
public class UserReadService {
    private Logger log = Logger.getLogger(this.getClass());
    private List<UserReadShare> shares;
    private String key="vjaosjdoQasdfkjDSF";

    @Autowired
    private UserReadMapper urMapper;
    @Autowired
    private ReadPractiseService readPractiseService;
    @Autowired
    UserReadLikeMapper userReadLikeMapper;
    @Autowired
    UserReadComMapper userReadComMapper;


    public UserRead insert(UserRead ur){
        Date now=new Date();
        ur.setId(UUID.randomUUID().toString());
        ur.setDatecreated(now);
        ur.setDatemodified(now);
        ur.setShared("0");
        urMapper.insert(ur);
        return ur;
    }
    public UserRead load(String id){
        return urMapper.selectByPrimaryKey(id);
    }
    public UserRead update(UserRead ur){
        Date now=new Date();
        ur.setDatemodified(now);
        urMapper.updateByPrimaryKey(ur);
        return ur;
    }
    public void updateByPrimaryKeySelective(UserRead ur){
        urMapper.updateByPrimaryKeySelective(ur);
    }
    public List<UserRead> userReadListByReadPractiseId(String userId,String readPractiseId){
        UserReadExample e=new UserReadExample();
        UserReadExample.Criteria c = e.createCriteria();
        c.andReadPractiseIdEqualTo(readPractiseId);
        c.andUserIdEqualTo(userId);
        List<UserRead> userReads = urMapper.selectByExample(e);
        return userReads;
    }

    public List<UserRead> userReadList(String userId,String productId,Date date){
        List<ReadPractise> readPractises = readPractiseService.readPractiseList(userId,productId);
        if(readPractises==null||readPractises.size()==0){
            return null;
        }
        List<String> readPractiseIds=new ArrayList<String>();
        Map<String,ReadPractise> map=new HashMap<String,ReadPractise>();
        for(ReadPractise rp:readPractises){
            readPractiseIds.add(rp.getId());
            map.put(rp.getId(),rp);
        }
        UserReadExample e=new UserReadExample();
        UserReadExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andReadPractiseIdIn(readPractiseIds);

        if(date!=null){
            Date dayStart = TimeUtil.getDayStart(date);
            Date dayEnd = TimeUtil.getDayEnd(date);
            c.andDatecreatedGreaterThanOrEqualTo(dayStart);
            c.andDatecreatedLessThan(dayEnd);
        }
        e.setOrderByClause("dateCreated desc");
        List<UserRead> userReads = urMapper.selectByExample(e);
        SimpleDateFormat f=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        //设置关联的read_practise和几天前
        for(UserRead ur :userReads){
            String readPractiseId=ur.getReadPractiseId();
            ur.setReadPractise(map.get(readPractiseId));
            ur.setDateCreatedTime(f.format(ur.getDatecreated()));
//            Date dateCreated=ur.getDatecreated();
//            try {
//                if(DateUtil.isNDaysBefore(dateCreated,0)){
//                    ur.setnDaysBefore("今天");
//                }else if(DateUtil.isNDaysBefore(dateCreated,1)){
//                    ur.setnDaysBefore("昨天");
//                }else if(DateUtil.isNDaysBefore(dateCreated,2)){
//                    ur.setnDaysBefore("前天");
//                }
//            } catch (ParseException e1) {
//                log.error(e1.getMessage(),e1);
//            }
        }
        return userReads;

    }

    public UserReadShare userReadShare(String userId,String userReadId){
        if(shares==null){
            shares=new ArrayList<UserReadShare>();
        }
        UserReadShare share=this.getUserReadShare(userReadId);
        Date now=new Date();
        SimpleDateFormat f=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        if(share==null){
            share=new UserReadShare();
            share.setUserId(userId);
            share.setUserReadId(userReadId);
            share.setCreated(f.format(now));
            String sign=this.signShare(share);
            share.setSign(sign);
            shares.add(share);
        }else{
            share.setCreated(f.format(now));
            String sign=this.signShare(share);
            share.setSign(sign);
        }
        return share;

    }

    public String signShare(UserReadShare share){
        String source=share.getUserId()+
                share.getUserReadId()+
                share.getCreated()+
                key;

        String sign= MD5Util.string2MD5(source);
        return sign;

    }

    public UserReadShare getUserReadShare(String userReadId){
        if(shares==null){
            return null;
        }
        if(StringUtils.isEmpty(userReadId)){
            return null;
        }
        for(UserReadShare share:shares){
            if(userReadId.equals(share.getUserReadId())
                    ){
                return share;

            }
        }
        return null;
    }
    //清除存储在service中的过期share
    public void clearExpired(){
        synchronized (this){
            if(shares==null){
                return;
            }
            for(Iterator<UserReadShare> iterator=shares.iterator();iterator.hasNext();){
                UserReadShare s=iterator.next();
                if(this.expired(s.getCreated())){
                    iterator.remove();
                }
            }
        }

    }

    private boolean expired(String created){

        SimpleDateFormat f=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        try {
            Date parse = f.parse(created);
            long time = new Date().getTime() - parse.getTime();
            if(time/60000>=5){
                return true;
            }
            return false;

        } catch (ParseException e) {
            return true;
        }
    }

    public void shared(String userReadId){
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        UserRead ur = this.load(userReadId);
        if(ur!=null){
            ur.setDatemodified(now);
            ur.setSharedTime(sdf.format(now));
            ur.setShared("1");
            urMapper.updateByPrimaryKey(ur);
        }
    }



}
