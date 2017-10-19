package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.PractiseUserMapper;
import com.wally.hiread.dao.user.StudyPointMapper;
import com.wally.hiread.model.product.Practise;
import com.wally.hiread.model.product.PractiseUser;
import com.wally.hiread.model.user.StudyPoint;
import com.wally.hiread.model.user.StudyPointExample;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.user.UserService;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class StudyPointService {
    @Autowired
    private PractiseUserMapper practiseUserMapper;
    @Autowired
    private PractiseUserService puService;
    @Autowired
    private PractiseService pService;
    @Autowired
    private StudyPointMapper spMapper;
    @Autowired
    private UserService uService;

    public int calcStudyPoint(PractiseUser pu){
        String practiseId = pu.getPractiseId();
        Practise practise = pService.load(practiseId, false);
        if(practise==null){
            return 0;
        }
        if(!pu.getCorrect().equals(PractiseUser.CORRECT_YES)){
            return 0;
        }
        int pointMin=1;//题目最低分值
        int pointMax=5;//题目最高分值
        String points = practise.getPoints();
        if(StringUtils.isEmpty(points)){
            pointMax=5;
        }else{
            try{
                pointMax=Integer.parseInt(points);
            }catch(Exception ex){
                pointMax=5;
            }
        }
        int point=pointMin;
        if(pu.getTryTimes().equals("1")&&
                pu.getFirstTime().equals("1")&&
                pu.getCompleted().equals("1")&&
                pu.getUseHelp().equals("0")&&
                pu.getUseTranslation().equals("0")){
            point=pointMax;
        }
        return point;
    }
    public int updateStudyPoint(String practiseUserId){
        PractiseUser pu = puService.load(practiseUserId);
        if(pu==null){
            return 0;
        }
        int point = this.calcStudyPoint(pu);
        if(point==0){
            return 0;
        }
        pu.setStudyPoint(point+"");
        puService.update(pu);

        Date now=new Date();
        SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        StudyPoint sp=new StudyPoint();
        sp.setId(UUID.randomUUID().toString());
        sp.setDatecreated(now);
        sp.setDatemodified(now);
        sp.setUserId(pu.getUserId());
        sp.setReferenceId(pu.getId());
        sp.setPoint(point+"");
        spMapper.insert(sp);

        int total = this.userTotalStudyPoint(pu.getUserId());
        User user = uService.loadUserById(pu.getUserId());
        user.setStudyPoint(total+"");
        uService.updateUser(user);

        return point;
    }

    public int userTotalStudyPoint(String userId){
        StudyPointExample e=new StudyPointExample();
        StudyPointExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        List<StudyPoint> studyPoints = spMapper.selectByExample(e);
        if(studyPoints==null||studyPoints.size()==0){
            return 0;
        }
        int total=0;
        for(StudyPoint sp:studyPoints){
            int point=0;
            try{
                point=Integer.parseInt(sp.getPoint());
            }catch(Exception ex){
                point=0;
            }
            total+=point;
        }
        return total;

    }




}
