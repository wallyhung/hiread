package com.wally.hiread.service.order;

import com.wally.hiread.dao.order.CouponMapper;
import com.wally.hiread.dao.order.ProdOrderItemMapper;
import com.wally.hiread.dao.order.ProdOrderMapper;
import com.wally.hiread.model.order.*;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.service.sys.SetupService;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ShoppingCartOrderService {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private SetupService setupService;
    @Autowired
    private ProdOrderMapper orderMapper;
    @Autowired
    private ProdOrderItemMapper orderItemMapper;
    @Autowired
    private ProdOrderService poService;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private PaymentService paymentService;

    public SR<String> submit(HttpSession session, String channel, String buyerMessage, String addressId,String userId,String orderId ){
        SR<String> sr=new SR<String>();
        ShoppingCartSubmit cartSubmit = cartService.getCartSubmit(session);
        if(cartSubmit==null){
            sr.setMsg("会话过期");
            return sr;
        }
        boolean noPayment = cartSubmit.isNoPayment();
        if(StringUtils.isEmpty(channel)&&!noPayment){
            sr.setMsg("请选择支付方式");
            return sr;
        }
        if(StringUtils.isEmpty(addressId)){
            sr.setMsg("请选择收货地址");
            return sr;
        }
        if(!StringUtils.isEmpty(buyerMessage)&&buyerMessage.length()>300){
            sr.setMsg("买家留言请不要超过300字");
            return sr;
        }


        ProdOrder order=null;

        //插入订单
        if(StringUtils.isEmpty(orderId)){
            order=new ProdOrder();
            order.setUserId(userId);
            int i = setupService.genOrderNo();
            if(i==-1){
                sr.setMsg(AppCons.SYSTEM_ERROR_TO_USER);
                return sr;
            }
            String orderNo="D"+new DecimalFormat("00000000").format(i);
            order.setOrderNo(orderNo);
            order.setAmount(new DecimalFormat("0.00").format(cartSubmit.getCoursePriceSum()));
            order.setUsePoint(cartSubmit.getUsePoint()+"");
            order.setCouponAmount(cartSubmit.getCouponPrice()+"");
            order.setNetAmount(new DecimalFormat("0.00").format(cartSubmit.getTotal()));
            //订单金额为0直接完成支付
            if(noPayment){
                order.setPaid("1");
                order.setAmount(order.getNetAmount());
            }else{
                order.setPaid("0");
                order.setChannel(channel);
            }
            order.setAddressId(addressId);
            order.setProductDiscount(new DecimalFormat("0.00").format(cartSubmit.getDiffPriceSum()));
            order.setGrouDiscount(new DecimalFormat("0.00").format(cartSubmit.getCompoundDiffPriceSum()));
            order.setBuyerMessage(buyerMessage);

            Date now=new Date();
            order.setId(UUID.randomUUID().toString());
            order.setDatecreated(now);
            order.setDatemodified(now);
            orderMapper.insert(order);
            //插入订单明细
            List<ShoppingCart> cartSelectedList = cartSubmit.getCartSelectedList();
            for(ShoppingCart cart: cartSelectedList){
                Product product = cart.getProduct();
                ProdOrderItem item=new ProdOrderItem();
                item.setAmount(product.getVideoCoursePrice());
                item.setNumber("1");
                item.setProdOrderId(order.getId());
                item.setProductId(product.getId());

                item.setId(UUID.randomUUID().toString());
                item.setDatecreated(now);
                item.setDatemodified(now);
                orderItemMapper.insert(item);
            }


            //更新现金券
            Coupon couponSelected = cartSubmit.getCouponSelected();
            if(couponSelected!=null){
                couponSelected.setDatemodified(now);
                couponSelected.setProdOrderId(order.getId());
                couponMapper.updateByPrimaryKey(couponSelected);
            }

            //订单金额为0直接完成支付
            if(noPayment){
                order=poService.loadMyOrder(userId,order.getId());
                paymentService.afterOrderPayment(order);
            }

            cartService.removeSelected(session);

        }
        //更新订单的支付渠道，留言，收货地址
        else{
            order=poService.loadMyOrder(userId,orderId);
            order.setChannel(channel);
            order.setBuyerMessage(buyerMessage);
            order.setAddressId(addressId);
            Date now=new Date();
            order.setDatemodified(now);
            orderMapper.updateByPrimaryKey(order);
        }

        sr.setStatus(SR.SUCCESS);
        sr.setEntity(order.getId());
        return sr;

    }




}
