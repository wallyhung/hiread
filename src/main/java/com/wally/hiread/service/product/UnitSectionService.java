package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UnitSectionMapper;
import com.wally.hiread.model.product.UnitSection;
import com.wally.hiread.model.product.UnitSectionExample;
import com.wally.hiread.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UnitSectionService {
    @Autowired
    private UnitSectionMapper unitMapper;

    private UnitSection compute(UnitSection us){
        int computedSec=-1;
        String timeMin = us.getTimeMin();
        if(UnitSection.TIME_MIN_START.equals(timeMin)){
            computedSec=0;
        }else if(UnitSection.TIME_MIN_END.equals(timeMin)){
            computedSec=99999;
        }else{
            String timeSec = us.getTimeSec();
            computedSec= TimeUtil.toSec(timeMin,timeSec);
        }
        us.setComputedSec(computedSec);
        return us;
    }

    public List<UnitSection> listByUnitAndProdId(String productId,String unitId){
        UnitSectionExample e=new UnitSectionExample();
        UnitSectionExample.Criteria c = e.createCriteria();
        //c.andProductIdEqualTo(productId);//TO CHANGE
        c.andUnitIdEqualTo(unitId);
        List<UnitSection> uss = unitMapper.selectByExample(e);
        for(UnitSection us:uss){
            compute(us);
        }
        return uss;
    }

}
