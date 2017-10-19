package com.wally.hiread.controller.test;

import com.wally.hiread.components.wxpay.model.UnifiedOrder;
import com.wally.hiread.components.wxpay.service.WxpayService;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eric on 20/06/2017.
 */
@Controller
@RequestMapping(value = "/wxpay")
public class WxpayController {
    @Autowired
    WxpayService wxpayService;

    @RequestMapping(value = "/unifiedorder")
    public @ResponseBody SR<String> unifiedOrder(HttpServletRequest request){
        String body = "body";// 商品描述
        String out_trade_no = "99943232234";// 商户订单号
        int total_fee = 1;// 总金额,单位分
        String notify_url = "http://dev.hiread.cn/hiread";// 支付结果通知地址，不能带参数

        /*
         * 调用统一下单接口，成功后将返回二维码链接（code_url），失败后将返回错误信息;
         * 根据 code_url 调用相关js库可生成支付二维码供用户使用
         */
        UnifiedOrder unifiedOrder = new UnifiedOrder(body, out_trade_no, total_fee, notify_url);
        unifiedOrder.setSpbill_create_ip(HttpUtil.getIpAddr(request));
        SR<String> sr =  wxpayService.unifiedOrder(unifiedOrder);

        return sr;
    }
}
