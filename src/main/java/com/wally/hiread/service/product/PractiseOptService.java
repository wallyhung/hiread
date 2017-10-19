package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.PractiseOptMapper;
import com.wally.hiread.model.product.PractiseOpt;
import com.wally.hiread.model.product.PractiseOptExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PractiseOptService {
    @Autowired
    private PractiseOptMapper practiseOptMapper;

    private PractiseOpt compute(PractiseOpt practiseOpt){
        return practiseOpt;
    }

    public PractiseOpt load(String id){
        PractiseOpt practiseOpt = practiseOptMapper.selectByPrimaryKey(id);
        compute(practiseOpt);
        return practiseOpt;
    }
    public List<PractiseOpt> listByPractiseId(String practiseId){
        PractiseOptExample pe=new PractiseOptExample();
        PractiseOptExample.Criteria pc = pe.createCriteria().andPractiseIdEqualTo(practiseId);
        pe.setOrderByClause("cast(c_sort as unsigned)");
        List<PractiseOpt> practiseOpts = practiseOptMapper.selectByExample(pe);
        for(PractiseOpt p:practiseOpts){
            compute(p);
        }
        return practiseOpts;
    }


}
