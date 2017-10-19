package com.wally.hiread.controller.order;

import com.wally.hiread.components.wxpay.model.UnifiedOrder;
import com.wally.hiread.components.wxpay.model.WxPaymentResult;
import com.wally.hiread.components.wxpay.service.WxpayService;
import com.wally.hiread.model.order.ProdOrder;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.order.*;
import com.wally.hiread.service.user.UserAddressService;
import com.wally.hiread.service.user.UserPointService;
import com.wally.hiread.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Properties;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/order/payment")
public class PaymentController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	ShoppingCartOrderService cartOrderService;
	@Autowired
	UserPointService upService;
	@Autowired
	CouponService couponService;
	@Autowired
	UserAddressService addrService;
	@Autowired
	ProdOrderService orderService;
	@Autowired
	WxpayService wxpayService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	Properties serverConfig;
	//预支付
	@RequestMapping(value = "/prepay", method = RequestMethod.POST)
	public @ResponseBody
	SR<String> prepay(String orderId,HttpServletRequest request){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailType(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		ProdOrder prodOrder = orderService.loadMyOrder(user.getId(), orderId);
		if(prodOrder==null){
			sr.setMsg("订单不存在");
			return sr;
		}
		String channel = prodOrder.getChannel();
		String out_trade_no = orderService.genOutTradeNo(prodOrder);//生成商户订单号
		int total_fee = prodOrder.getNetAmountFen();
		if(StringUtils.isEmpty(channel)||StringUtils.isEmpty(out_trade_no)||total_fee==0){
			sr.setMsg("订单无法支付");
			return sr;
		}
		String body="HiRead 课程购买";
		String baseUrl = serverConfig.getProperty("app.url.base");
		log.info("base url:"+baseUrl);
		if(StringUtils.isEmpty(baseUrl)){
			baseUrl=HttpUtil.getBaseUrl(request);
		}
		String notify_url=baseUrl+"order/payment/notify/"+channel;

		if(ProdOrder.CHANNEL_WX.equals(channel)){
			 /*
			 * 调用统一下单接口，成功后将返回二维码链接（code_url），失败后将返回错误信息;
			 * 根据 code_url 调用相关js库可生成支付二维码供用户使用
			 */
			UnifiedOrder unifiedOrder = new UnifiedOrder(body, out_trade_no, total_fee, notify_url);
			unifiedOrder.setSpbill_create_ip(HttpUtil.getIpAddr(request));
			sr =  wxpayService.unifiedOrder(unifiedOrder);
			if(sr.getStatus().equals(SR.FAIL)){
				log.info("prepay failed:"+sr.getMsg());
				return sr;
			}
			sr.setStatus(SR.SUCCESS);
			return sr;
		}
		sr.setMsg("不支持的支付方式");
		return sr;


	}
	//支付通知
	@RequestMapping(value = "/notify/{channel}", method = RequestMethod.POST)
	public void notifyPost(@PathVariable String channel, HttpServletRequest request, HttpServletResponse response){
		log.info("notify begin-------------------->");
		PrintWriter pw=null;
		try{
			if(StringUtils.isEmpty(channel)){
				log.info("channel is empty");
				log.info("notify end-------------------->");
				pw = response.getWriter();
				pw.write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[channel is empty]]></return_msg></xml>");
				pw.flush();
				return;
			}
			if(ProdOrder.CHANNEL_WX.equals(channel)){
				String inputStr=HttpUtil.getInputStreamString(request);
				WxPaymentResult wxPaymentResult = wxpayService.parsePaymentResult(inputStr);
				String returnCode = wxPaymentResult.getReturn_code();
				if(StringUtils.isEmpty(returnCode)||"FAIL".equals(returnCode)){
					log.info("no return code");
					log.info("notify end-------------------->");
					pw = response.getWriter();
					pw.write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[no return code]]></return_msg></xml>");
					pw.flush();
					return;
				}

				SR<String> validatePaymentResult=paymentService.validateWxPaymentResult(wxPaymentResult);
				if(validatePaymentResult.getStatus().equals(SR.FAIL)){
					log.info(validatePaymentResult.getMsg());
					log.info("notify end-------------------->");
					pw = response.getWriter();
					pw.write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+validatePaymentResult.getMsg()+"]]></return_msg></xml>");
					pw.flush();
					return;
				}

				paymentService.wxPaymentResultProcess(wxPaymentResult);
				log.info("success");
				log.info("notify end-------------------->");
				pw = response.getWriter();
				pw.write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				pw.flush();
				return;
			}else{
				log.info("channel not supported");
				log.info("notify end-------------------->");
				pw = response.getWriter();
				pw.write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[channel not supported]]></return_msg></xml>");
				pw.flush();
				return;
			}

		}catch(Exception ex){
			log.error(ex.getMessage(),ex);
			log.info("notify end-------------------->");

		}


	}

}


