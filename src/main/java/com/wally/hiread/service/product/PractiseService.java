package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.ProductMapper;
import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.product.Practise;
import com.wally.hiread.model.product.PractiseExample;
import com.wally.hiread.model.product.PractiseOpt;
import com.wally.hiread.model.product.Unit;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PractiseService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PractiseMapper practiseMapper;
    @Autowired
    private PractiseOptService practiseOptService;
    @Autowired
    private PractiseUserService puService;

    private Practise compute(Practise practise){
        int computedSec=-1;
        String videoTimeMin = practise.getVideoTimeMin();
        if(Practise.VIDEO_TIME_START.equals(videoTimeMin)){
            computedSec=0;
        }else if(Practise.VIDEO_TIME_END.equals(videoTimeMin)){
            computedSec=99999;
            Unit unit = unitMapper.selectByPrimaryKey(practise.getUnitId());
            if(unit!=null){
                computedSec=TimeUtil.toSec(unit.getVideoDurationMin(),unit.getVideoDurationSec());
            }
        }else{
            String videoTimeSec = practise.getVideoTimeSec();
            computedSec= TimeUtil.toSec(videoTimeMin,videoTimeSec);
        }
        practise.setComputedSec(computedSec);
        return practise;
    }

    private Practise setOpts(Practise p){
        String pId = p.getId();
        List<PractiseOpt> practiseOpts = practiseOptService.listByPractiseId(pId);
        p.setPractiseOpts(practiseOpts);
        return p;
    }

    public Practise load(String id,boolean practiceOpts){
        Practise practise = practiseMapper.selectByPrimaryKey(id);
        compute(practise);
        if(practiceOpts){
            this.setOpts(practise);
        }
        return practise;
    }
    public Practise load(String id,boolean practiceOpts,boolean unit){
        Practise practise = this.load(id,practiceOpts);
        if(unit){
            Unit u = unitMapper.selectByPrimaryKey(practise.getUnitId());
            practise.setUnit(u);
        }
        return practise;
    }

    public List<Practise> listByUnitId(String unitId,boolean practiceOpts){
        PractiseExample pe=new PractiseExample();
        pe.setOrderByClause("dateCreated");
        PractiseExample.Criteria pc = pe.createCriteria().andUnitIdEqualTo(unitId);
        List<Practise> practises = practiseMapper.selectByExample(pe);
        for(Practise p:practises){
            compute(p);
            if(practiceOpts){
                this.setOpts(p);
            }
        }
        return practises;
    }
    /*
    非课前、课后测试题目
     */
    public List<Practise>  listByUnitId(String unitId,boolean practiceOpts,String applyType,boolean activeFilter){
        PractiseExample e=new PractiseExample();
        e.setOrderByClause("dateCreated");
        PractiseExample.Criteria c = e.createCriteria();
        c.andUnitIdEqualTo(unitId);
        if(activeFilter){
            c.andActiveEqualTo(Practise.ACTIVE_YES);
        }
        List<Practise> practises = practiseMapper.selectByExample(e);
        List<Practise> result=getPractisesByApplyType(practises,new String[]{applyType},practiceOpts);
        return result;
    }
    /*
    课前、课后测试题目
     */
    public List<Practise>  listByProductId(String productId,boolean practiceOpts,String[] applyType,boolean activeFilter){
        PractiseExample e=new PractiseExample();
        e.setOrderByClause("dateCreated");
        PractiseExample.Criteria c = e.createCriteria();
        c.andProductIdEqualTo(productId);
        if(activeFilter){
            c.andActiveEqualTo(Practise.ACTIVE_YES);
        }
        List<Practise> practises = practiseMapper.selectByExample(e);
        List<Practise> result=getPractisesByApplyType(practises,applyType,practiceOpts);
        return result;
    }

    private List<Practise> getPractisesByApplyType(List<Practise> practises,String[] applyType,boolean practiceOpts){
        List<Practise> result=new ArrayList<Practise>();
        for(Practise p:practises){
            String ap = p.getApplyType();
            if(!StringUtils.isEmpty(ap)){
                String[] split = ap.split(AppCons.FIELD_SEPERATOR);
                boolean contains=false;
                for(int i=0;i<split.length;i++){
                    if(Arrays.asList(applyType).contains(split[i])){
                        contains=true;
                        break;
                    }
                }
                if(contains){
                    if(applyType.length==1&&applyType[0].equals(Practise.APPLY_TYPE_VIDEO_GAMI)){
                        if(StringUtils.isEmpty(p.getSequence())){
                            continue;//如果是videoGami，必须设置sequence
                        }
                        compute(p);
                        if(p.getComputedSec()<0){
                            continue;//如果是videoGami，必须设置正确的时点
                        }
                    }else{
                        p.setComputedSec(0);//非videoGami时点设置为0
                    }
                    if(practiceOpts){
                        setOpts(p);
                    }
                    result.add(p);
                }
            }
        }
        //设置排序
        if(applyType.length==1&&applyType[0].equals(Practise.APPLY_TYPE_VIDEO_GAMI)){
            Collections.sort(result, new Comparator<Practise>() {
                @Override
                public int compare(Practise o1, Practise o2) {
                    if(o1.getComputedSec()<o2.getComputedSec()){
                        return -1;
                    }else if(o1.getComputedSec()==o2.getComputedSec()){
                        return Integer.parseInt(o1.getSequence())-Integer.parseInt(o2.getSequence());
                    }else{
                        return 1;
                    }
                }
            });
        }else if(applyType.length==1&&(applyType[0].equals(Practise.APPLY_TYPE_PREVIEW_HW)||applyType[0].equals(Practise.APPLY_TYPE_REVIEW_HW))){
            List<Practise> wordCards=new ArrayList<Practise>();
            List<Practise> others=new ArrayList<Practise>();
            for(Practise p:result){
                if(p.getQuestionType().equals("P2")){
                    wordCards.add(p);
                }else{
                    others.add(p);
                }
            }
            wordCards.addAll(others);
            result=wordCards;
        }
        return result;
    }

    public Practise setExt(String userId,Practise p,String type){
        if(!StringUtils.isEmpty(type)){
            boolean practiseDone = puService.practiseDone(userId, p.getId(), type);
            p.setPractiseDone(practiseDone);

        }
        return p;

    }

    public List<Practise> listBy(String productId,String questionType){
        PractiseExample e=new PractiseExample();
        PractiseExample.Criteria c = e.createCriteria();
        if(!StringUtils.isEmpty(productId)){
            c.andProductIdEqualTo(productId);
        }
        if(!StringUtils.isEmpty(questionType)){
            c.andQuestionTypeEqualTo(questionType);
        }
        List<Practise> practises = practiseMapper.selectByExample(e);
        return  practises;
    }



}
