package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UserVocabMapper;
import com.wally.hiread.model.product.Practise;
import com.wally.hiread.model.product.UserReadShare;
import com.wally.hiread.model.product.UserVocab;
import com.wally.hiread.model.product.UserVocabExample;
import com.wally.hiread.setting.sys.AppCons;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserVocabService {
    private Logger log = Logger.getLogger(this.getClass());
    private List<UserReadShare> shares;
    private String key="vjaosjdoQasdfkjDSF";

    @Autowired
    private UserVocabMapper uvMapper;
    @Autowired
    private PractiseService practiseService;


    public UserVocab getUserVocab(String userId,String practiseId){
        UserVocabExample e=new UserVocabExample();
        UserVocabExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andPractiseIdEqualTo(practiseId);
        c.andDeletedEqualTo("No");
        List<UserVocab> userVocabs = uvMapper.selectByExample(e);
        if(userVocabs!=null&&userVocabs.size()==1){
            return userVocabs.get(0);
        }
        return null;

    }

    public UserVocab add(String userId,String practiseId){
        UserVocab uv=null;
        UserVocabExample e=new UserVocabExample();
        UserVocabExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andPractiseIdEqualTo(practiseId);
        List<UserVocab> userVocabs = uvMapper.selectByExample(e);
        if(userVocabs!=null&&userVocabs.size()==1){
            uv= userVocabs.get(0);
        }

        Date now=new Date();
        SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        if(uv==null){
            uv=new UserVocab();
            uv.setId(UUID.randomUUID().toString());
            uv.setDatecreated(now);
            uv.setDatemodified(now);
            uv.setUserId(userId);
            uv.setPractiseId(practiseId);
            uv.setDeleted("No");
            uvMapper.insert(uv);
            return uv;
        }else if(uv.getDeleted().equals("Yes")){
            uv.setDatemodified(now);
            uv.setDeleted("No");
            uvMapper.updateByPrimaryKey(uv);
        }
        return uv;
    }

    public UserVocab delete(String userVocabId){
        UserVocab userVocab = uvMapper.selectByPrimaryKey(userVocabId);
        if(userVocab==null){
            return null;
        }
        Date now=new Date();
        SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        userVocab.setDatemodified(now);
        userVocab.setDeleteTime(dtf.format(now));
        userVocab.setDeleted("Yes");
        uvMapper.updateByPrimaryKey(userVocab);
        return userVocab;
    }

    public List<UserVocab> myList(String userId){
        UserVocabExample e=new UserVocabExample();
        UserVocabExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andDeletedEqualTo("No");
        List<UserVocab> userVocabs = uvMapper.selectByExample(e);
        for(UserVocab uv:userVocabs){
            Practise p = practiseService.load(uv.getPractiseId(), false,true);
            uv.setPractise(p);
        }
        return userVocabs;
    }



}
