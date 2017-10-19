package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UnitHintMapper;
import com.wally.hiread.model.product.UnitHint;
import com.wally.hiread.model.product.UnitHintExample;
import com.wally.hiread.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UnitHintService {
    @Autowired
    private UnitHintMapper unitHintMapper;

    private UnitHint compute(UnitHint uh){
        int computedSec=-1;
        String timeMin = uh.getTimeMin();
        if(UnitHint.TIME_MIN_START.equals(timeMin)){
            computedSec=0;
        }else if(UnitHint.TIME_MIN_END.equals(timeMin)){
            computedSec=99999;
        }else{
            String timeSec = uh.getTimeSec();
            computedSec= TimeUtil.toSec(timeMin,timeSec);
        }
        uh.setComputedSec(computedSec);

        int computedSecEnd=-1;
        String timeMinEnd=uh.getTimeMinEnd();
        if(UnitHint.TIME_MIN_START.equals(timeMinEnd)){
            computedSecEnd=0;
        }else if(UnitHint.TIME_MIN_END.equals(timeMinEnd)){
            computedSecEnd=99999;
        }else{
            String timeSecEnd = uh.getTimeSecEnd();
            computedSecEnd= TimeUtil.toSec(timeMinEnd,timeSecEnd);
        }
        uh.setComputedSecEnd(computedSecEnd);
        return uh;
    }

    public List<UnitHint> listByUnitId(String unitId){
        UnitHintExample e=new UnitHintExample();
        UnitHintExample.Criteria c = e.createCriteria();
        c.andUnitIdEqualTo(unitId);
        List<UnitHint> uhs = unitHintMapper.selectByExample(e);
        for(UnitHint uh:uhs){
            compute(uh);
        }
        return uhs;
    }

}
