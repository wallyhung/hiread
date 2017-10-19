package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.UserFreeTalkMapper;
import com.wally.hiread.model.product.UserFreeTalk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserFreeTalkService {
    @Autowired
    private UserFreeTalkMapper talkMapper;
    public UserFreeTalk insert(UserFreeTalk talk){
        Date now=new Date();
        talk.setId(UUID.randomUUID().toString());
        talk.setDatecreated(now);
        talk.setDatemodified(now);
        talkMapper.insert(talk);
        return talk;
    }

    public UserFreeTalk load(String id){
        return talkMapper.selectByPrimaryKey(id);
    }

    public UserFreeTalk update(UserFreeTalk talk){
        Date now=new Date();
        talk.setDatemodified(now);
        talkMapper.updateByPrimaryKey(talk);
        return talk;
    }


}
