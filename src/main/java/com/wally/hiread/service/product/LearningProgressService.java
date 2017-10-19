package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.LearningProgressMapper;
import com.wally.hiread.model.product.LearningProgress;
import com.wally.hiread.model.product.LearningProgressExample;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class LearningProgressService {
    @Autowired
    private LearningProgressMapper learningProgressMapper;

    public LearningProgress insert(LearningProgress lp){
        Date now=new Date();
        SimpleDateFormat f=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        lp.setId(UUID.randomUUID().toString());
        lp.setDatecreated(now);
        lp.setDatemodified(now);
        lp.setStartTime(f.format(now));
        learningProgressMapper.insert(lp);
        return lp;
    }


    public int update(LearningProgress lp) throws ParseException {
        Date now=new Date();
        lp.setDatemodified(now);
        int i = learningProgressMapper.updateByPrimaryKey(lp);
        return i;
    }

    public LearningProgress load(String userId,String productId,String unitId,String type){
        LearningProgressExample e=new LearningProgressExample();
        LearningProgressExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        c.andUnitIdEqualTo(unitId);
        c.andTypeEqualTo(type);
        List<LearningProgress> list = learningProgressMapper.selectByExample(e);
        if(list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;
    }

    public long productLearningTime(String userId,String productId){
        long time=0;
        LearningProgressExample e=new LearningProgressExample();
        LearningProgressExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        List<LearningProgress> list = learningProgressMapper.selectByExample(e);
        if(list!=null&&list.size()>0){
            for(LearningProgress l:list){
                String totalTime = l.getTotalTime();
                if(!StringUtils.isEmpty(totalTime)){
                    try{
                        time+=Long.parseLong(totalTime);
                    }catch (Exception ex){
                        continue;
                    }
                }
            }
        }
        return time;
    }



}
