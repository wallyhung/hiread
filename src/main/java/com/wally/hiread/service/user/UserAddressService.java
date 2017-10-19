package com.wally.hiread.service.user;

import com.wally.hiread.dao.user.UserAddressMapper;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.UserAddress;
import com.wally.hiread.model.user.UserAddressExample;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.RegExpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class UserAddressService {
	@Autowired
	UserAddressMapper addressMapper;
	
	public List<UserAddress> myAddresses(String userId) {
		UserAddressExample e = new UserAddressExample();
		UserAddressExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(userId);
		e.setOrderByClause("field(c_default,'1') desc, dateModified desc");
		List<UserAddress> addresses = addressMapper.selectByExample(e);
		return addresses;
	}

	public void delete(String id,String userId){
		UserAddressExample e = new UserAddressExample();
		UserAddressExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(userId);
		c.andIdEqualTo(id);
		List<UserAddress> addresses = addressMapper.selectByExample(e);
		if(addresses!=null&&addresses.size()==1){
			addressMapper.deleteByPrimaryKey(id);

//			List<UserAddress> userAddresses = this.myAddresses(userId);
//			if(userAddresses!=null&&userAddresses.size()>0){
//				UserAddress addr = userAddresses.get(0);
//				addr.setAsDefault(UserAddress.DEFAULT_YES);
//				this.setAsDefault(addr,userId);
//			}
		}

	}

	public UserAddress load(String id,String userId){
		UserAddressExample e = new UserAddressExample();
		UserAddressExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(userId);
		c.andIdEqualTo(id);
		List<UserAddress> addresses = addressMapper.selectByExample(e);
		if(addresses!=null&&addresses.size()==1) {
			return addresses.get(0);
		}
		return null;
	}
	public SR<String> validate(UserAddress addr){
		SR<String> sr=new SR<String>();
		String name = addr.getName();
		if(StringUtils.isEmpty(name)){
			sr.setMsg("用户名不能为空");
			return sr;
		}
		if(!RegExpValidator.isUsernameWithoutSpe(name)) {
			sr.setMsg("用户名格式不正确");
			return sr;
		}
		String mobile = addr.getMobile();
		if(StringUtils.isEmpty(mobile)){
			sr.setMsg("手机号不能为空");
			return sr;
		}
		if(!RegExpValidator.isMobile(mobile)) {
			sr.setMsg("手机号格式不正确");
			return sr;
		}
		if(StringUtils.isEmpty(addr.getProvince())||
				StringUtils.isEmpty(addr.getProvinceId())||
				StringUtils.isEmpty(addr.getCity())||
				StringUtils.isEmpty(addr.getCityId())||
				StringUtils.isEmpty(addr.getArea())||
				StringUtils.isEmpty(addr.getAreaId())||
				StringUtils.isEmpty(addr.getDetailAddress())){
			sr.setMsg("地址不正确");
			return sr;
		}
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	public void add(UserAddress addr,String userId){

		Date now=new Date();
		SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
		addr.setId(UUID.randomUUID().toString());
		addr.setDatecreated(now);
		addr.setDatemodified(now);
		addr.setUserId(userId);

		boolean isDefault=false;
		if(StringUtils.isEmpty(addr.getAsDefault())||!addr.getAsDefault().equals(UserAddress.DEFAULT_YES)){
			addr.setAsDefault(UserAddress.DEFAULT_NO);
		}else{
			isDefault=true;
		}
		addressMapper.insert(addr);

		if(isDefault){
			this.setAsDefault(addr,userId);
		}
	}

	public void update(UserAddress addr,String userId){

		Date now=new Date();
		SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
		addr.setDatemodified(now);

		boolean isDefault=false;
		if(StringUtils.isEmpty(addr.getAsDefault())||!addr.getAsDefault().equals(UserAddress.DEFAULT_YES)){
			addr.setAsDefault(UserAddress.DEFAULT_NO);
		}else{
			isDefault=true;
		}
		addressMapper.updateByPrimaryKey(addr);

		if(isDefault){
			this.setAsDefault(addr,userId);
		}
	}

	public void setAsDefault(UserAddress addr,String userId){
		if(addr!=null&&addr.getAsDefault().equals(UserAddress.DEFAULT_YES)){
			List<UserAddress> userAddresses = this.myAddresses(userId);
			for(UserAddress a:userAddresses){
				if(addr.getId().equals(a.getId())){
					addressMapper.updateByPrimaryKey(addr);
				}else{
					a.setAsDefault(UserAddress.DEFAULT_NO);
					addressMapper.updateByPrimaryKey(a);
				}
			}
		}

	}
		

	
	
}
