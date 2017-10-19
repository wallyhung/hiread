package com.wally.hiread.service.sys;

import com.wally.hiread.model.user.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by eric on 29/06/2017.
 */
@Service
public class SecurityUserManager implements ApplicationContextAware{
    static ApplicationContext applicationContext;

    public static User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof AnonymousAuthenticationToken){
            return null;
        }
        return (User) auth.getDetails();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SecurityUserManager.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
