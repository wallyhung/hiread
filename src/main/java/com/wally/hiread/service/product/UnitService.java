package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.product.*;
import com.wally.hiread.service.user.UserProductService;
import com.wally.hiread.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UnitService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductService productServcie;
    @Autowired
    private PractiseService practiseService;
    @Autowired
    private UnitSectionService usService;
    @Autowired
    private UnitHintService uhService;
    @Autowired
    private UnitAudioService uaService;
    @Autowired
    private UserProductService upService;
    private int computeSec(String min,String sec){
        int minI=0;
        int secI=0;
        try {
            minI=Integer.parseInt(min);
            secI=Integer.parseInt(sec);
        }catch(Exception ex){

        }
        return minI*60+secI;
    }
    private Unit compute(Unit unit){
        String pausePoint = unit.getPausePoint();
        String[] pausePoints=null;
        List<Integer> pauseList=new ArrayList<Integer>();
        if(!StringUtils.isEmpty(pausePoint)){
            pausePoints=pausePoint.split(";");
            for(String s : pausePoints){
                String[] split = s.split(":");
                if(split==null||split.length!=2){
                    continue;
                }

                String min=split[0];
                String sec=split[1];
                int computedSec= TimeUtil.toSec(min,sec);
                pauseList.add(computedSec);
            }
        }

        if(pauseList.size()>0){
            unit.setPauseList(pauseList);
        }
        String videoDurationMin = unit.getVideoDurationMin();
        String videoDurationSec = unit.getVideoDurationSec();
        int videoTime=computeSec(videoDurationMin,videoDurationSec);
        unit.setVideoTime(videoTime);

        int unitAudiosTime=0;
        List<UnitAudio> unitAudios = unit.getUnitAudios();
        if(unitAudios!=null&&unitAudios.size()>0){
            for(UnitAudio audio:unitAudios){
                String durationMin=audio.getDurationMin();
                String durationSec=audio.getDurationSec();
                unitAudiosTime+= this.computeSec(durationMin, durationSec);
            }
        }
        unit.setUnitAudiosTime(unitAudiosTime);
        unit.setMediaTime(videoTime+unitAudiosTime);



        return unit;
    }
    private Unit setProduct(Unit unit){
        String productId = unit.getProductId();
        Product product = productServcie.load(productId,false);
        unit.setProduct(product);
        return unit;
    }


    private Unit setPractises(Unit unit){
        List<Practise> practises = practiseService.listByUnitId(unit.getId(),true);
        unit.setPractises(practises);
        return unit;
    }

    private Unit setUnitSections(Unit unit){
        List<UnitSection> unitSections = usService.listByUnitAndProdId(unit.getProductId(), unit.getId());
        unit.setUnitSections(unitSections);
        return unit;
    }

    private Unit setUnitHints(Unit unit){
        List<UnitHint> unitHints = uhService.listByUnitId( unit.getId());
        unit.setUnitHints(unitHints);
        return unit;
    }

    private Unit setUnitAudios(Unit unit){
        List<UnitAudio> unitAudios = uaService.listByUnitId( unit.getId());
        unit.setUnitAudios(unitAudios);
        return unit;
    }

    public Unit load(String id,boolean product,boolean practises,boolean unitSections,boolean unitHints,boolean unitAudios){
        Unit unit = unitMapper.selectByPrimaryKey(id);
        if(product){
            this.setProduct(unit);
        }
        if(practises){
            this.setPractises(unit);
        }
        if(unitSections){
            this.setUnitSections(unit);
        }
        if(unitHints){
            this.setUnitHints(unit);
        }
        if(unitAudios){
            this.setUnitAudios(unit);
        }
        this.compute(unit);
        return unit;
    }
    public List<Unit> listByProductId(String productId){
        UnitExample e=new UnitExample();
        e.setOrderByClause("cast(c_unitNo as unsigned)");
        UnitExample.Criteria c = e.createCriteria();
        c.andProductIdEqualTo(productId);
        List<Unit> units = unitMapper.selectByExample(e);
        for(Unit u:units){
            this.compute(u);
        }
        return units;
    }

}
