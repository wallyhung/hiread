package com.wally.hiread.service.sys;

import com.wally.hiread.dao.sys.TestingMapper;
import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.dao.user.UserTestingMapper;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.sys.Testing;
import com.wally.hiread.model.sys.TestingExample;
import com.wally.hiread.model.sys.TestingReport;
import com.wally.hiread.model.user.User;
import com.wally.hiread.model.user.UserTesting;
import com.wally.hiread.model.user.UserTestingExample;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class TestingService {
	@Autowired
	TestingMapper testingMapper;
	@Autowired
	UserTestingMapper userTestingMapper;
	@Autowired
	ProductService productService;
	@Autowired
	UserMapper userMapper;

	public List<Testing> list(String paperLevel){
		TestingExample e=new TestingExample();
		TestingExample.Criteria c = e.createCriteria();
		c.andPaperLevelEqualTo(paperLevel);
		e.setOrderByClause("cast(c_sequence as unsigned),dateCreated");
		List<Testing> ts = testingMapper.selectByExample(e);
		return ts;
	}

	public UserTesting findUserTesting(String userId,String testingId){
		UserTestingExample e=new UserTestingExample();
		UserTestingExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(userId);
		c.andTestingIdEqualTo(testingId);
		List<UserTesting> userTestings = userTestingMapper.selectByExample(e);
		if(userTestings!=null&&userTestings.size()>0){
			return userTestings.get(0);
		}
		return null;
	}

	public Testing load(String id){
		return testingMapper.selectByPrimaryKey(id);
	}

	public TestingReport getTestingReport(String userId,int paperLevel){
		TestingReport r=new TestingReport();
		r.setUserId(userId);
		r.setPaperLevel(paperLevel);
		setCorrectPercent(r);
		setRecommendHireadRanks(r);
		setRecommendProducts(r);
		return r;
	}

	public void setTestingResult(String userId,int paperLevel,int correctPercent){
		User user = userMapper.selectByPrimaryKey(userId);
		Date now=new Date();
		SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
		user.setHireadTestDate(dtf.format(now));
		user.setHireadTestLevel(paperLevel+"");
		user.setHireadTestResult(correctPercent+"");
		userMapper.updateByPrimaryKey(user);
	}
	public String getPaperLevel(int point){
		if(point>=0&&point<=15){
			return "1";
		}
		if(point>=16&&point<=22){
			return "2";
		}
		if(point>=23&&point<=27){
			return "3";
		}
		if(point>=28&&point<=30){
			return "4";
		}
		if(point>=31&&point<=32){
			return "5";
		}
		return null;
	}
	private void setRecommendProducts(TestingReport r){
		List<Product> recommendProducts=new ArrayList<Product>();
		List<Integer> recommendHireadRanks = r.getRecommendHireadRanks();
		for(int rank:recommendHireadRanks){
			List<Product> products = productService.listByHireadRank(rank + "");
			recommendProducts.addAll(products);
		}
		r.setRecommendProducts(recommendProducts);

	}
	private void setRecommendHireadRanks(TestingReport r){
		int p = r.getCorrectPercent();
		List<Integer> recommandHireadRanks=new ArrayList<Integer>();
		int paperLevel = r.getPaperLevel();
		int correctPercent=r.getCorrectPercent();
		if(paperLevel==1){
			if(correctPercent>80){
				recommandHireadRanks.add(1);
				recommandHireadRanks.add(2);
			}else{
				recommandHireadRanks.add(1);
			}
		}else if(paperLevel==2){
			if(correctPercent<30){
				recommandHireadRanks.add(1);
			}else if(correctPercent>=30&&correctPercent<=80){
				recommandHireadRanks.add(1);
				recommandHireadRanks.add(2);
			}else{
				recommandHireadRanks.add(2);
				recommandHireadRanks.add(3);
			}
		}else if(paperLevel==3){
			if(correctPercent<30){
				recommandHireadRanks.add(2);
			}else if(correctPercent>=30&&correctPercent<=80){
				recommandHireadRanks.add(2);
				recommandHireadRanks.add(3);
			}else{
				recommandHireadRanks.add(3);
				recommandHireadRanks.add(4);
			}
		}else if(paperLevel==4){
			if(correctPercent<30){
				recommandHireadRanks.add(3);
			}else if(correctPercent>=30&&correctPercent<=80){
				recommandHireadRanks.add(3);
				recommandHireadRanks.add(4);
			}else{
				recommandHireadRanks.add(4);
				recommandHireadRanks.add(5);
			}
		}else if(paperLevel==5){
			if(correctPercent<30){
				recommandHireadRanks.add(4);
			}else if(correctPercent>=30&&correctPercent<=80){
				recommandHireadRanks.add(4);
				recommandHireadRanks.add(5);
			}else if(correctPercent>80&&correctPercent<=90){
				recommandHireadRanks.add(5);
				recommandHireadRanks.add(6);
			}else{
				recommandHireadRanks.add(6);
				recommandHireadRanks.add(7);
			}
		}
		r.setRecommendHireadRanks(recommandHireadRanks);
	}
	
	private void setCorrectPercent(TestingReport r){
		String userId=r.getUserId();
		UserTestingExample e=new UserTestingExample();
		UserTestingExample.Criteria c = e.createCriteria();
		c.andUserIdEqualTo(userId);
		List<UserTesting> userTestings = userTestingMapper.selectByExample(e);
		if(userTestings==null||userTestings.size()==0){
			r.setTotalNum(0);
			r.setCorrectNum(0);
			r.setCorrectPercent(0);
			return;
		}

		int correctNum=0;
		int totalNum=0;
		for(UserTesting t:userTestings){
			String testingId = t.getTestingId();
			Testing testing = this.load(testingId);
			if(testing!=null&&testing.getPaperLevel().equals(r.getPaperLevel()+"")){
				totalNum++;
				String correct = t.getCorrect();
				if(!StringUtils.isEmpty(correct)&&correct.equals("Yes")){
					correctNum++;
				}
			}
		}
		if(totalNum==0){
			r.setTotalNum(0);
			r.setCorrectNum(0);
			r.setCorrectPercent(0);
			return;
		}
		Double ceil = Math.ceil( 100*correctNum/totalNum);
		int correctPercent = ceil.intValue();
		r.setTotalNum(totalNum);
		r.setCorrectNum(correctNum);
		r.setCorrectPercent(correctPercent);
	}
	





}
