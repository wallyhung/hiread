package com.wally.hiread.service.order;

import com.wally.hiread.components.wxpay.model.WxPaymentResult;
import com.wally.hiread.dao.order.CouponMapper;
import com.wally.hiread.dao.order.PaymentMapper;
import com.wally.hiread.dao.order.ProdOrderMapper;
import com.wally.hiread.dao.product.UserProductMapper;
import com.wally.hiread.dao.user.UserPointMapper;
import com.wally.hiread.model.order.*;
import com.wally.hiread.model.product.UserProduct;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.UserPoint;
import com.wally.hiread.service.sys.SetupService;
import com.wally.hiread.setting.sys.AppCons;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class PaymentService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private ProdOrderMapper orderMapper;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private ProdOrderService orderService;
    @Autowired
    private UserPointMapper userPointMapper;
    @Autowired
    private UserProductMapper userProductMapper;
    @Autowired
    private SetupService setupService;

    public List<Payment> getPayments(String orderId) {
        PaymentExample e = new PaymentExample();
        PaymentExample.Criteria c = e.createCriteria();
        c.andProdOrderIdEqualTo(orderId);
        List<Payment> payments = paymentMapper.selectByExample(e);
        return payments;

    }

    public SR<String> validateWxPaymentResult(WxPaymentResult result) {
        SR<String> sr = new SR<String>();
        //验证商户订单号
        String out_trade_no = result.getOut_trade_no();
        ProdOrder prodOrder = orderService.loadByOutTradeNo(out_trade_no);
        if (prodOrder == null) {
            sr.setMsg("order not found");
            return sr;
        }
        //是否已支付过
        String paid = prodOrder.getPaid();
        if (ProdOrder.PAID_YES.equals(paid)) {
            sr.setMsg("order already paid");
            return sr;
        }
        //验证订单金额
        String totalFee = result.getTotal_fee();
        if (StringUtils.isEmpty(totalFee) || !totalFee.equals(prodOrder.getNetAmountFen() + "")) {
            sr.setMsg("total fee not correct");
            return sr;
        }

        sr.setStatus(SR.SUCCESS);
        return sr;
    }

    public void wxPaymentResultProcess(WxPaymentResult result) {
        Date now = new Date();
        SimpleDateFormat f = new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        log.info("payment result process ..");
        synchronized (PaymentService.class) {
            //更新订单
            ProdOrder order = orderService.loadByOutTradeNo(result.getOut_trade_no());

            order.setDatemodified(now);
            order.setPaymentTime(f.format(now));
            //插入支付结果
            Payment p = new Payment();
            p.setId(UUID.randomUUID().toString());
            p.setDatecreated(now);
            p.setDatemodified(now);
            p.setProdOrderId(order.getId());
            p.setUserId(order.getUserId());
            p.setChannel(ProdOrder.CHANNEL_WX);
            p.setTransactionNo(result.getTransaction_id());

            String resultCode = result.getResult_code();
            if ("FAIL".equals(resultCode)) {
                //TO SPECIAL,当支付金额为0表示支付通知的return_code为"FAIL"
                p.setAmount("0.00");
                order.setPaid(ProdOrder.PAID_NO);
            } else {
                p.setAmount(order.getNetAmount());
                order.setPaid(ProdOrder.PAID_YES);
            }

            orderMapper.updateByPrimaryKey(order);
            paymentMapper.insert(p);

            afterOrderPayment(order);

        }
    }

    public void afterOrderPayment(ProdOrder order){
        Date now=new Date();
        SimpleDateFormat f = new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        if (order.getPaid().equals(ProdOrder.PAID_YES)) {
            //插入用户课程
            List<ProdOrderItem> orderItems = order.getOrderItems();
            for (ProdOrderItem item : orderItems) {
                UserProduct up = new UserProduct();
                up.setId(UUID.randomUUID().toString());
                up.setDatecreated(now);
                up.setDatemodified(now);
                up.setUserId(order.getUserId());
                up.setProductId(item.getProductId());
                up.setProdOrderId(item.getProdOrderId());
                userProductMapper.insert(up);

            }
            //现金券更新
            Coupon coupon = couponService.orderCoupon(order.getId());
            if (coupon != null) {
                coupon.setDatemodified(now);
                coupon.setUsed(Coupon.USED_YES);
                coupon.setUseTime(f.format(now));
                couponMapper.updateByPrimaryKey(coupon);
            }
            String giveCoupon = couponService.giveCoupon(order.getUserId(), order.getId(), Coupon.GETTYPE_PO);
            if(!SR.SUCCESS.equals(giveCoupon)){
                log.info("现金券赠送失败:"+giveCoupon);
            }
            //插入使用积分
            String usePoint = order.getUsePoint();
            if (!StringUtils.isEmpty(usePoint)) {
                int usePointI = 0;
                try {
                    usePointI = Integer.parseInt(usePoint);
                } catch (Exception e) {

                }
                if (usePointI > 0) {
                    UserPoint point = new UserPoint();
                    point.setId(UUID.randomUUID().toString());
                    point.setDatecreated(now);
                    point.setDatemodified(now);
                    point.setUserId(order.getUserId());
                    point.setEventType(UserPoint.EVENT_TYPE_USE_POINT);
                    point.setReferenceId(order.getId());
                    point.setPoint(-usePointI + "");
                    userPointMapper.insert(point);
                }
            }


        }
    }


}
