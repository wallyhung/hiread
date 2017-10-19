package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UnitAudioMapper;
import com.wally.hiread.model.product.UnitAudio;
import com.wally.hiread.model.product.UnitAudioExample;
import com.wally.hiread.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UnitAudioService {
    @Autowired
    private UnitAudioMapper unitAudioMapper;

    private UnitAudio compute(UnitAudio uh){
        int computedSec=-1;
        String timeMin = uh.getTimeMin();
        if(UnitAudio.TIME_MIN_START.equals(timeMin)){
            computedSec=0;
        }else if(UnitAudio.TIME_MIN_END.equals(timeMin)){
            computedSec=99999;
        }else{
            String timeSec = uh.getTimeSec();
            computedSec= TimeUtil.toSec(timeMin,timeSec);
        }
        uh.setComputedSec(computedSec);
        return uh;
    }

    public List<UnitAudio> listByUnitId(String unitId){
        UnitAudioExample e=new UnitAudioExample();
        UnitAudioExample.Criteria c = e.createCriteria();
        c.andUnitIdEqualTo(unitId);
        List<UnitAudio> uhs = unitAudioMapper.selectByExample(e);
        for(UnitAudio uh:uhs){
            compute(uh);
        }
        return uhs;
    }
    public UnitAudio load(String id){
        UnitAudio unitAudio = unitAudioMapper.selectByPrimaryKey(id);
        compute(unitAudio);
        return unitAudio;
    }

}
