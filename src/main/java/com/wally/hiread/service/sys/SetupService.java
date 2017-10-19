package com.wally.hiread.service.sys;

import com.wally.hiread.dao.sys.SetupMapper;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.model.sys.SetupExample;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class SetupService {
	@Autowired
	SetupMapper setupMapper;
	@Autowired
    Environment env;

	private Setup compute(Setup setup){
		String userPointExchangeRate = setup.getUserPointExchangeRate();
		if(StringUtils.isEmpty(userPointExchangeRate)){
			setup.setUserPointExchangeRate("0.1");
		}
		String doubleItemDiscount = setup.getDoubleItemDiscount();
		if(StringUtils.isEmpty(doubleItemDiscount)){
			setup.setDoubleItemDiscount("0.9");
		}
		String tripleItemDiscount = setup.getTripleItemDiscount();
		if(StringUtils.isEmpty(tripleItemDiscount)){
			setup.setTripleItemDiscount("0.8");
		}
		return setup;

	}

	public Setup loadSetup(){
		SetupExample e=new SetupExample();
		List<Setup> setups = setupMapper.selectByExample(e);
		if(setups!=null&&setups.size()==1){
			return compute(setups.get(0));
		}
		return null;
		
	}
	
	public boolean isDevEnv(){
		String[] activeProfiles = env.getActiveProfiles();
		for(String profile:activeProfiles){
			if(profile.equals(AppCons.ENV_PROFILE_PRODUCTION)){
				return false;
			}
		}
		return true;
	}
	
	public int genOrderNo(){

		Setup setup = this.loadSetup();
		if(setup==null){
			return  -1;
		}
		synchronized (this){
			String seq=setup.getLatestOrderSeq();
			if(StringUtils.isEmpty(seq)){
				seq="0";
			}

			int seqI;
			try{
				seqI=Integer.parseInt(seq);
			}catch(Exception ex){
				return -1;
			}
			int result=seqI+1;
			setup.setLatestOrderSeq(result+"");
			setupMapper.updateByPrimaryKey(setup);
			return result;
		}

	}




}
