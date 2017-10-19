package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.PractiseUserMapper;
import com.wally.hiread.model.product.Practise;
import com.wally.hiread.model.product.PractiseUser;
import com.wally.hiread.model.product.PractiseUserExample;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PractiseUserService {
    @Autowired
    private PractiseUserMapper practiseUserMapper;
    @Autowired
    private PractiseService practiseService;

    public boolean isFirstTime(String userId,String practiseId,String type){
        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andPractiseIdEqualTo(practiseId);
        c.andTypeEqualTo(type);
        List<PractiseUser> list = practiseUserMapper.selectByExample(e);
        if(list==null||list.size()==0){
            return true;
        }
        return false;
    }

    public boolean practiseDone(String userId,String practiseId,String type){
        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andPractiseIdEqualTo(practiseId);
        c.andTypeEqualTo(type);
        c.andCorrectEqualTo(PractiseUser.CORRECT_YES);
        List<PractiseUser> list = practiseUserMapper.selectByExample(e);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }
    public boolean practiseCompletedForStudyLog(String practiseId,String studyLogId){
        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();
        c.andCompletedEqualTo("1");
        c.andPractiseIdEqualTo(practiseId);
        c.andUserStudyLogIdEqualTo(studyLogId);
        List<PractiseUser> list = practiseUserMapper.selectByExample(e);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    public boolean practiseCorrectForStudyLog(String practiseId,String studyLogId){
        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();
        c.andCorrectEqualTo(PractiseUser.CORRECT_YES);
        c.andTryTimesEqualTo("1");
        c.andPractiseIdEqualTo(practiseId);
        c.andUserStudyLogIdEqualTo(studyLogId);
        List<PractiseUser> list = practiseUserMapper.selectByExample(e);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    public PractiseUser getNotCorrectPractiseUser(String userId,String practiseId,String type){
        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andPractiseIdEqualTo(practiseId);
        c.andTypeEqualTo(type);
        c.andCorrectEqualTo(PractiseUser.CORRECT_NO);
        List<PractiseUser> list = practiseUserMapper.selectByExample(e);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    public PractiseUser insert(PractiseUser pu){
        Date now=new Date();
        SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        pu.setId(UUID.randomUUID().toString());
        pu.setDatecreated(now);
        pu.setDatemodified(now);
        practiseUserMapper.insert(pu);
        return pu;
    }

    public PractiseUser load(String id){
        return practiseUserMapper.selectByPrimaryKey(id);
    }

    public int update(PractiseUser pu){
        Date now=new Date();
        pu.setDatemodified(now);
        int i = practiseUserMapper.updateByPrimaryKey(pu);
        return i;
    }
    /*
    连续作对数量
     */
    public int accurateNum(PractiseUser pu,List<Practise> practises){
        if(pu==null){
            return 0;
        }
        if(!pu.getFirstTime().equals("1")){
            return 0;
        }
//        String lastAccuratePractiseId="";
//        int accurateNum=0;
//        for(int i=0;i<practises.size();i++){
//            Practise p=practises.get(i);
//            if(p.isFirstTimeCorrect()){
//                lastAccuratePractiseId=p.getId();
//            }
//            if(pu.getPractiseId().equals(p.getId())){
//                accurateNum=p.getAccurateNum();
//            }
//        }
//        if(lastAccuratePractiseId.equals(pu.getPractiseId())){//如果题目是最后一道作对的题目才返回连对数量
//            return accurateNum;
//        }
        for(int i=0;i<practises.size();i++){
            Practise p=practises.get(i);
            if(pu.getPractiseId().equals(p.getId())){
                return p.getAccurateNum();
            }
        }
        return 0;

    }
    /*
    用户是否是第一次作对题目
     */
    public PractiseUser firstTimeCorrectPractiseUser(String userId,String productId,String unitId,String type,String practiseId){
        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();

        c.andUserIdEqualTo(userId);
        c.andProductIdEqualTo(productId);
        c.andUnitIdEqualTo(unitId);
        c.andTypeEqualTo(type);
        c.andPractiseIdEqualTo(practiseId);
        c.andCorrectEqualTo(PractiseUser.CORRECT_YES);
        c.andUseTranslationEqualTo("0");
        c.andUseHelpEqualTo("0");
        c.andFirstTimeEqualTo("1");
        c.andTryTimesEqualTo("1");
        c.andCompletedEqualTo("1");
        List<PractiseUser> pus=practiseUserMapper.selectByExample(e);
        if(pus!=null&&pus.size()>0){
            return pus.get(0);
        }
        return null;

    }
    /*
    根据practiseUser，得到该题目所处的习题集
     */
    public List<Practise> practiseList(PractiseUser pu){
        List<Practise> practises=null;
        String type=pu.getType();
        String[] applyType=null;
        if(type.equals(Practise.APPLY_TYPE_PRETEST_PRACTISE)||type.equals(Practise.APPLY_TYPE_POSTTEST_PRACTISE)){
            if(type.equals(Practise.APPLY_TYPE_PRETEST_PRACTISE)){
                applyType=new String[]{type};
            }else{
                applyType=new String[]{Practise.APPLY_TYPE_PRETEST_PRACTISE,type};
            }
            practises=practiseService.listByProductId(pu.getProductId(),false,applyType,true);

        }else{
            practises=practiseService.listByUnitId(pu.getUnitId(),false,type,true);
        }
        return practises;
    }
    /*
    根据practiseUser，得到该题目所处的习题集的每道题是否是第一次作对，以及在此基础上的连对数
     */
    public List<Practise> practiseListWithAccuateNum(PractiseUser pu){
        List<Practise> practises=this.practiseList(pu);
        int accurateNum=0;

        for(Practise p:practises){
            String questionType = p.getQuestionType();
            if(questionType.equals("P2")){
                p.setFirstTimeCorrect(true);
                p.setAccurateNum(0);
            }else{
                PractiseUser firstTimeCorrectPractiseUser = this.firstTimeCorrectPractiseUser(pu.getUserId(), pu.getProductId(), pu.getUnitId(), pu.getType(), p.getId());
                boolean firstTimeCorrect=firstTimeCorrectPractiseUser==null?false:true;
                p.setFirstTimeCorrect(firstTimeCorrect);
                if(firstTimeCorrect){
                    accurateNum++;
                }else{
                    accurateNum=0;
                }
                p.setAccurateNum(accurateNum);
                p.setFirstTimeCorrectPractiseUser(firstTimeCorrectPractiseUser);
            }
        }
        return practises;
    }


}
