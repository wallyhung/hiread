package com.wally.hiread.service.order;

import com.wally.hiread.dao.order.ProdOrderItemMapper;
import com.wally.hiread.dao.order.ProdOrderMapper;
import com.wally.hiread.model.order.*;
import com.wally.hiread.setting.sys.AppCons;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ProdOrderService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private ProdOrderMapper orderMapper;
    @Autowired
    private ProdOrderItemMapper orderItemMapper;
    @Autowired
    private PaymentService paymentService;
    public ProdOrder loadMyOrder(String userId, String orderId){
        ProdOrderExample e=new ProdOrderExample();
        ProdOrderExample.Criteria c = e.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andIdEqualTo(orderId);
        List<ProdOrder> pos = orderMapper.selectByExample(e);
        if(pos!=null&&pos.size()==1){
            return compute(pos.get(0));
        }
        return null;
    }
    public ProdOrder loadByOutTradeNo(String outTradeNo){
        ProdOrderExample e=new ProdOrderExample();
        ProdOrderExample.Criteria c = e.createCriteria();
        c.andOutTradeNoEqualTo(outTradeNo);
        List<ProdOrder> pos = orderMapper.selectByExample(e);
        if(pos!=null&&pos.size()==1){
            return compute(pos.get(0));
        }
        return null;
    }

    public String genOutTradeNo(ProdOrder order){

            Date now = new Date();
            SimpleDateFormat f = new SimpleDateFormat(AppCons.TIMESTAMP_FORMAT);
            order.setDatemodified(now);
        synchronized (ProdOrderService.class) {
            String orderNo = order.getOrderNo();
            String outTradeNo = orderNo + "-" + f.format(now);
            order.setOutTradeNo(outTradeNo);
            orderMapper.updateByPrimaryKey(order);
            return outTradeNo;
        }
    }

    private ProdOrder compute(ProdOrder o){
        String netAmount = o.getNetAmount();
        try{
            double netAmountD=new BigDecimal(netAmount).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            int netAmountFen=new Double(netAmountD*100).intValue();
            o.setNetAmountFen(netAmountFen);
        }catch (Exception e){
            log.error(e);
        }
        o.setOrderItems(this.getItems(o.getId()));
        List<Payment> payments = paymentService.getPayments(o.getId());
        boolean hasZeroAmountPay=false;
        for(Payment p : payments){
            String amount = p.getAmount();
            //TO SPECIAL,当支付金额为0表示支付通知的return_code为"FAIL"
            if("0.00".equals(amount)){
                hasZeroAmountPay=true;
                break;
            }
        }
        if(hasZeroAmountPay){
            o.setHasZeroAmountPay("1");
        }else{
            o.setHasZeroAmountPay("0");
        }
        return o;
    }

    public List<ProdOrderItem> getItems(String orderId){
        ProdOrderItemExample e=new ProdOrderItemExample();
        ProdOrderItemExample.Criteria c = e.createCriteria();
        c.andProdOrderIdEqualTo(orderId);
        List<ProdOrderItem> prodOrderItems = orderItemMapper.selectByExample(e);
        return prodOrderItems;

    }



}
