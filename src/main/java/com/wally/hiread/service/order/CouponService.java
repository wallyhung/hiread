package com.wally.hiread.service.order;

import com.wally.hiread.dao.order.CouponMapper;
import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.ProductMapper;
import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.order.Coupon;
import com.wally.hiread.model.order.CouponConst;
import com.wally.hiread.model.order.CouponExample;
import com.wally.hiread.model.order.CouponVO;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.service.product.PractiseOptService;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.service.sys.SetupService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class CouponService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PractiseMapper practiseMapper;
    @Autowired
    private PractiseOptService practiseOptService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private SetupService setupService;

    public Coupon orderCoupon(String orderId){
        CouponExample e=new CouponExample();
        CouponExample.Criteria c = e.createCriteria();
        c.andProdOrderIdEqualTo(orderId);
        List<Coupon> cs = couponMapper.selectByExample(e);
        if(cs!=null&&cs.size()>0){
            return cs.get(0);
        }
        return null;
    }

    public List<Coupon> myCoupons(String userId){
        CouponExample e=new CouponExample();
        CouponExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        e.setOrderByClause("cast(c_amount as unsigned) desc");
        List<Coupon> cs = couponMapper.selectByExample(e);
        for(Coupon coupon : cs){
            this.compute(coupon);
        }
        return cs;
    }

    public SR<String> validateCoupon(String couponId,String userId){
        SR<String> sr=new SR<String>();
        CouponExample e=new CouponExample();
        CouponExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andIdEqualTo(couponId);
        List<Coupon> cs = couponMapper.selectByExample(e);
        if(cs==null||cs.size()!=1){
            sr.setMsg("不合法的现金券");
            return sr;
        }
        Coupon coupon = cs.get(0);
        boolean valid = validateDate(coupon);
        if(!valid){
            sr.setMsg("不合法的现金券");
            return sr;
        }
        sr.setStatus(SR.SUCCESS);
        return sr;
    }
    private boolean validateDate(Coupon c){
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        boolean isValidStart=false;
        String startDate = c.getStartDate();
        try{
            if(!StringUtils.isEmpty(startDate)){
                Date start = sdf.parse(startDate + " 00:00:00");
                if(!now.before(start)){
                    isValidStart=true;
                }
            }else{
                isValidStart=true;
            }
        }catch (Exception ex){
            isValidStart=false;
        }
        boolean isValidEnd=false;
        String endDate = c.getEndDate();
        try{
            if(!StringUtils.isEmpty(endDate)){
                Date end = sdf.parse(endDate + " 23:59:59");
                if(!now.after(end)){
                    isValidEnd=true;
                }
            }else{
                isValidEnd=true;
            }
        }catch (Exception ex){
            isValidEnd=false;
        }
        if(isValidStart&&isValidEnd){
            return true;
        }else {
            return false;
        }
    }
    private Coupon compute(Coupon c){
        boolean valid=validateDate(c);
        c.setValid(valid);

        if(StringUtils.isEmpty(c.getUsed())){
            c.setUsed("0");
        }

        String amount = c.getAmount();
        if(StringUtils.isEmpty(amount)){
            amount="0";
        }else{
            try{
                amount=Integer.parseInt(amount)+"";
            }catch (Exception e){
                amount="0";
            }
        }
        c.setAmount(amount);
        return c;

    }
    public Coupon getGiveCoupon(String userId,String refId,String type){
        CouponExample e=new CouponExample();
        CouponExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andReferenceIdEqualTo(refId);
        c.andGetTypeEqualTo(type);
        List<Coupon> cs = couponMapper.selectByExample(e);
        if(cs!=null&&cs.size()==1){
            return cs.get(0);
        }
        return null;
    }
    public String giveCoupon(String userId,String refId,String type){
        Setup setup = setupService.loadSetup();
        if(setup==null){
            return "配置不存在";
        }
        int amount=0;
        int expiry=0;
        if(Coupon.GETTYPE_PO.equals(type)){
            String buyerCoupon = setup.getBuyerCoupon();
            if(StringUtils.isEmpty(buyerCoupon)){
                return "未配置购买返现";
            }
            try{
                amount=Integer.parseInt(buyerCoupon);
            }catch (Exception ex){
                return "购买返现额度不正确";
            }
            String buyerCouponExpiryDate = setup.getBuyerCouponExpiryDate();
            if(StringUtils.isEmpty(buyerCouponExpiryDate)){
                buyerCouponExpiryDate="60";
            }
            try{
                expiry=Integer.parseInt(buyerCouponExpiryDate);
            }catch (Exception ex){
                return "购买返现有效期不正确";
            }
        }
        if(getGiveCoupon(userId,refId,type)!=null){
            return "返现已存在";
        }

        Date now=new Date();
        SimpleDateFormat f=new SimpleDateFormat(AppCons.DATE_FORMAT);
        Coupon coupon=new Coupon();
        coupon.setId(UUID.randomUUID().toString());
        coupon.setDatecreated(now);
        coupon.setDatemodified(now);
        coupon.setUserId(userId);
        coupon.setAmount(amount+"");
        coupon.setStartDate(f.format(now));
        Date dt = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, expiry);
        dt = cal.getTime();
        coupon.setEndDate(f.format(dt));
        coupon.setGetType(type);
        coupon.setReferenceId(refId);
        coupon.setUsed(Coupon.USED_NO);
        couponMapper.insert(coupon);
        return SR.SUCCESS;

    }

    public List<Coupon> listCoupon(String status, String id){
        CouponExample example = new CouponExample();
        CouponExample.Criteria criteria = example.createCriteria();
        criteria.andUsedEqualTo(status)
                .andUserIdEqualTo(id);
        List<Coupon> list = couponMapper.selectByExample(example);

        String endDate = null;
        String nowDate = DateUtil.dateToString(new Date(), AppCons.DATE_FORMAT);
        long diffDays = 0L;
        List<Coupon> allCoupons = new ArrayList<Coupon>();
        CouponVO couponVO = null;
        for (Coupon coupon : list) {
            couponVO = new CouponVO();
            endDate = coupon.getEndDate();
            try {
                diffDays = DateUtil.calculateIntervalDay(nowDate, endDate, AppCons.DATE_FORMAT);
                if(diffDays >= 0){
                    couponVO.setIntervalDays(String.valueOf(diffDays));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                couponVO.setId(coupon.getId());
                couponVO.setDatecreated(coupon.getDatecreated());
                couponVO.setDatemodified(coupon.getDatemodified());
                couponVO.setAmount(coupon.getAmount());
                couponVO.setEndDate(coupon.getEndDate());
                allCoupons.add(couponVO);
            }
        }

        return allCoupons;
    }
    public List<Coupon> listNearExpiredCoupon(String status, String id){
        CouponExample example = new CouponExample();
        CouponExample.Criteria criteria = example.createCriteria();
        criteria.andUsedEqualTo(status)
                .andUserIdEqualTo(id);
        List<Coupon> list = couponMapper.selectByExample(example);

        String endDate = null;
        String nowDate = DateUtil.dateToString(new Date(), AppCons.DATE_FORMAT);
        long diffDays = 0L;
        List<Coupon> nearExpiredCoupons = new ArrayList<Coupon>();
        CouponVO couponVO = null;
        for (Coupon coupon : list) {
            couponVO = new CouponVO();
            endDate = coupon.getEndDate();
            try {
                diffDays = DateUtil.calculateIntervalDay(nowDate, endDate, AppCons.DATE_FORMAT);
                if(diffDays >= 0 && diffDays <= CouponConst.COUPON_NEAR_EXPIRED_SETUP){
                    couponVO.setId(coupon.getId());
                    couponVO.setDatecreated(coupon.getDatecreated());
                    couponVO.setDatemodified(coupon.getDatemodified());
                    couponVO.setIntervalDays(String.valueOf(diffDays));
                    couponVO.setAmount(coupon.getAmount());
                    couponVO.setEndDate(coupon.getEndDate());
                    nearExpiredCoupons.add(couponVO);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return nearExpiredCoupons;
    }

    public void addCoupon(Coupon coupon){
        couponMapper.insertSelective(coupon);
    }





}
