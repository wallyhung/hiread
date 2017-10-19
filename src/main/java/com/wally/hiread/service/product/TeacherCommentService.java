package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.PractiseUserMapper;
import com.wally.hiread.dao.product.TeacherCommentMapper;
import com.wally.hiread.model.product.*;
import com.wally.hiread.service.user.UserProductService;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class TeacherCommentService {
    @Autowired
    private TeacherCommentMapper tcMapper;
    @Autowired
    private UserProductService upService;
    @Autowired
    private PractiseService practiseService;
    @Autowired
    private PractiseUserMapper puMapper;
    @Autowired
    private PractiseMapper practiseMappaer;
    @Autowired
    private UnitService unitService;

    public List<PractiseUser> myOnlinePractises(UserProduct up){
        String userId=up.getUserId();
        String productId=up.getProductId();
        String questionType="P1";
        List<Practise> practises = practiseService.listBy(productId, questionType);
        List<String> pids=new ArrayList<String>();
        for(Practise p:practises){
            pids.add(p.getId());
        }
        if(pids.size()==0){
            return null;
        }

        PractiseUserExample e=new PractiseUserExample();
        PractiseUserExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andPractiseIdIn(pids);
        c.andCompletedEqualTo("1");
        c.andFirstTimeEqualTo("1");
        e.setOrderByClause("dateCreated desc");
        List<PractiseUser> pus = puMapper.selectByExample(e);
        SimpleDateFormat f=new SimpleDateFormat(AppCons.DATETIME_FORMAT);

        for(PractiseUser pu:pus){
            pu.setDateCreatedTime(f.format(pu.getDatecreated()));
            String practiseId = pu.getPractiseId();
            Practise practise = practiseMappaer.selectByPrimaryKey(practiseId);
            String unitId=practise.getUnitId();
            Unit unit=unitService.load(unitId,false,false,false,false,false);
            practise.setUnit(unit);
            pu.setPractise(practise);

            String puId=pu.getId();
            TeacherCommentExample e2=new TeacherCommentExample();
            TeacherCommentExample.Criteria c2 = e2.createCriteria();
            c2.andReferenceIdEqualTo(puId);
            c2.andTypeEqualTo(TeacherComment.TYPE_PRACTISE);
            e2.setOrderByClause("dateCreated desc");
            List<TeacherComment> tcs = tcMapper.selectByExample(e2);
            if(tcs!=null&&tcs.size()>0){
                TeacherComment tc=tcs.get(0);
                tc.setDateCreatedTime(f.format(tc.getDatecreated()));
                pu.setTeacherComment(tc);
            }

        }
        return pus;

    }


}
