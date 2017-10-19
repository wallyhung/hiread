package com.wally.hiread.service.product;

import com.wally.hiread.model.product.HiToken;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TokenService {

    private Map<String,HiToken> tokenCache=new HashMap<>();

    public HiToken gen(){
        clean();
        String id=UUID.randomUUID().toString();
        HiToken token=new HiToken();
        token.setId(id);
        token.setDate(new Date());
        tokenCache.put(id,token);
        return token;
    }

    public void remove(String id){
        tokenCache.remove(id);
    }

    public HiToken get(String id){
        return tokenCache.get(id);
    }

    public void clean(){
        for(String key:tokenCache.keySet()){
            HiToken token=tokenCache.get(key);
            Date date = token.getDate();
            Date now=new Date();
            if(now.getTime()-date.getTime()>10000){
                tokenCache.remove(key);
            }
        }
    }

}
