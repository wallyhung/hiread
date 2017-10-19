package com.wally.hiread.components.wxpay.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.components.wxpay.WxpayConstant;
import com.wally.hiread.components.wxpay.WxpayException;
import com.wally.hiread.components.wxpay.model.UnifiedOrder;
import com.wally.hiread.components.wxpay.model.WxPaymentResult;
import com.wally.hiread.components.wxpay.util.XmlUtil;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Properties;

/**
 * 提供微信支付 Api 服务。详见 <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1">微信支付 API 列表<a/>
 * <p>
 * Created by eric on 19/06/2017.
 */
@Service
public class WxpayService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    Properties serverConfig;

    /**
     * 统一下单。接口调用成功后将返回二维码链接（code_url）用于生成支付二维码，失败将返回错误信息
     *
     * @param unifiedOrder
     * @return
     */
    public SR<String> unifiedOrder(UnifiedOrder unifiedOrder) {
        SR<String> sr = new SR<String>();
//        unifiedOrder.setSpbill_create_ip(HttpUtil.getIpAddr(request));
        // 生成签名
        String signature = null;
        JSONObject xmlObject = null;
        try {
            signature = generateSign(WxpayConstant.WXPAY_API_NAME_UNIFIEDORDER, unifiedOrder);
            unifiedOrder.setSign(signature);
            // 生成 xml request body
            String requestBody = XmlUtil.sampleJavaBean2Xml(unifiedOrder);
            log.info("request body:"+requestBody);
            String responseResult = HttpUtil.doXmlPost(WxpayConstant.WXPAY_API_URL_UNIFIEDORDER, requestBody);
            log.info("response result:"+responseResult);
            if(StringUtils.isEmpty(responseResult)){
                sr.setStatus(AppCons.SR_FAIL);
                sr.setMsg("预支付请求没有响应");
                return sr;
            }

            JSONObject jsonResult = XmlUtil.toJsonObject(responseResult);
            xmlObject = jsonResult.getJSONObject("xml");

            if(!xmlObject.containsValue("return_code")){
                sr.setStatus(AppCons.SR_FAIL);
                sr.setMsg("预支付请求响应异常");
                return sr;
            }
            String return_code=xmlObject.getString("return_code");
            if(return_code.equals(WxpayConstant.WXPAY_API_RETURN_CODE_FAIL)){
                sr.setStatus(AppCons.SR_FAIL);
                sr.setMsg(xmlObject.getString("return_msg"));
                return sr;
            }

            String result_code=xmlObject.getString("result_code");
            if (result_code.equals(WxpayConstant.WXPAY_API_RESULT_CODE_FAIL)){
                String err_code_des = xmlObject.getString("err_code_des");
                sr.setStatus(AppCons.SR_FAIL);
                sr.setMsg(err_code_des);
                return sr;
            }

            String codeUrl = xmlObject.getString("code_url");
            sr.setStatus(AppCons.SR_SUCCESS);
            sr.setEntity(codeUrl);

        } catch (WxpayException e) {
            log.error(e);
            sr.setMsg(e.getMessage());
            sr.setStatus(AppCons.SR_FAIL);
            sr.setEntity(e.getMessage());
        } catch (JSONException e) {
            log.error(e);
            sr.setMsg(e.getMessage());
            sr.setStatus(AppCons.SR_FAIL);
            sr.setEntity(xmlObject.toString());
        }

        return sr;
    }

    public String generateSign(String apiName, Object paramValue) throws WxpayException {

        switch (apiName) {
            case WxpayConstant.WXPAY_API_NAME_UNIFIEDORDER:
                if (!(paramValue instanceof UnifiedOrder) || paramValue == null) {
                    throw new WxpayException("Generate signature failed - param value error");
                }
                return generateUnifiedOrderSign((UnifiedOrder) paramValue);
            case WxpayConstant.WXPAY_API_NAME_PAYMENT_RESULT:
                if (!(paramValue instanceof WxPaymentResult) || paramValue == null) {
                    throw new WxpayException("Generate signature failed - param value error");
                }
                return generateSignatureForPaymentResult((WxPaymentResult) paramValue);
            default:
                return null;
        }
    }

    protected String generateUnifiedOrderSign(UnifiedOrder unifiedOrder) {
        String[] paramNameArr = new String[]{"appid", "mch_id", "device_info", "nonce_str", "sign_type", "body",
                "detail", "attach", "out_trade_no", "fee_type", "total_fee", "spbill_create_ip",
                "time_start", "time_expire", "goods_tag", "notify_url", "trade_type", "product_id",
                "limit_pay", "openid"};
        String splicedStr = "";

        Arrays.sort(paramNameArr);

        for (int i = 0; i < paramNameArr.length; i++) {
            switch (paramNameArr[i]) {
                case "appid":
                    String appid = unifiedOrder.getAppid();
                    if ((appid != null) && !appid.isEmpty()) {
                        splicedStr += "&appid=" + appid;
                    }
                    break;
                case "mch_id":
                    String mchId = unifiedOrder.getMch_id();
                    if (mchId != null && !mchId.isEmpty()) {
                        splicedStr += "&mch_id=" + mchId;
                    }
                    break;
                case "device_info":
                    String deviceInfo = unifiedOrder.getDevice_info();
                    if ((deviceInfo != null) && !deviceInfo.isEmpty()) {
                        splicedStr += "&device_info=" + deviceInfo;
                    }
                    break;
                case "nonce_str":
                    String nonceStr = unifiedOrder.getNonce_str();
                    if ((nonceStr != null) && !nonceStr.isEmpty()) {
                        splicedStr += "&nonce_str=" + nonceStr;
                    }
                    break;
                case "sign_type":
                    String signType = unifiedOrder.getSign_type();
                    if ((signType != null) && !signType.isEmpty()) {
                        splicedStr += "&sign_type=" + signType;
                    }
                    break;
                case "body":
                    String body = unifiedOrder.getBody();
                    if ((body != null) && !body.isEmpty()) {
                        splicedStr += "&body=" + body;
                    }
                    break;
                case "detail":
                    String detail = unifiedOrder.getDetail();
                    if ((detail != null) && !detail.isEmpty()) {
                        splicedStr += "&detail=" + detail;
                    }
                    break;
                case "attach":
                    String attach = unifiedOrder.getAttach();
                    if ((attach != null) && !attach.isEmpty()) {
                        splicedStr += "&attach=" + attach;
                    }
                    break;
                case "out_trade_no":
                    String outTradeNo = unifiedOrder.getOut_trade_no();
                    if ((outTradeNo != null) && !outTradeNo.isEmpty()) {
                        splicedStr += "&out_trade_no=" + outTradeNo;
                    }
                    break;
                case "fee_type":
                    String feeType = unifiedOrder.getFee_type();
                    if ((feeType != null) && !feeType.isEmpty()) {
                        splicedStr += "&fee_type=" + feeType;
                    }
                    break;
                case "total_fee":
                    int totalFee = unifiedOrder.getTotal_fee();
                    splicedStr += "&total_fee=" + String.valueOf(totalFee);
                    break;
                case "spbill_create_ip":
                    String spbillCreateIp = unifiedOrder.getSpbill_create_ip();
                    if ((spbillCreateIp != null) && !spbillCreateIp.isEmpty()) {
                        splicedStr += "&spbill_create_ip=" + spbillCreateIp;
                    }
                    break;
                case "time_start":
                    String timeStart = unifiedOrder.getTime_start();
                    if ((timeStart != null) && !timeStart.isEmpty()) {
                        splicedStr += "&time_start=" + timeStart;
                    }
                    break;
                case "time_expire":
                    String timeExpire = unifiedOrder.getTime_expire();
                    if ((timeExpire != null) && !timeExpire.isEmpty()) {
                        splicedStr += "&time_expire=" + timeExpire;
                    }
                    break;
                case "goods_tag":
                    String goodsTag = unifiedOrder.getGoods_tag();
                    if ((goodsTag != null) && !goodsTag.isEmpty()) {
                        splicedStr += "&goods_tag=" + goodsTag;
                    }
                    break;
                case "notify_url":
                    String notifyUrl = unifiedOrder.getNotify_url();
                    if ((notifyUrl != null) && !notifyUrl.isEmpty()) {
                        splicedStr += "&notify_url=" + notifyUrl;
                    }
                    break;
                case "trade_type":
                    String tradeType = unifiedOrder.getTrade_type();
                    if ((tradeType != null) && !tradeType.isEmpty()) {
                        splicedStr += "&trade_type=" + tradeType;
                    }
                    break;
                case "product_id":
                    String productId = unifiedOrder.getProduct_id();
                    if ((productId != null) && !productId.isEmpty()) {
                        splicedStr += "&product_id=" + productId;
                    }
                    break;
                case "limit_pay":
                    String limitPay = unifiedOrder.getLimit_pay();
                    if ((limitPay != null) && !limitPay.isEmpty()) {
                        splicedStr += "&limit_pay=" + limitPay;
                    }
                    break;
                case "openid":
                    String openid = unifiedOrder.getOpenid();
                    if ((openid != null) && !openid.isEmpty()) {
                        splicedStr += "&openid=" + openid;
                    }
                    break;
            }
        }

        splicedStr = splicedStr.substring(1);
        splicedStr += "&key=" + serverConfig.getProperty("app.wxpay.secret");

        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            crypt.update(splicedStr.getBytes("UTF-8"));
            String signature = byteToHex(crypt.digest()).toUpperCase();
            return signature;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    protected String generateSignatureForPaymentResult(WxPaymentResult pr){
        String[] paramsArr = new String[]{"appid", "mch_id", "device_info", "nonce_str", "sign_type", "body",
                "detail", "attach", "out_trade_no", "fee_type", "total_fee", "spbill_create_ip",
                "time_start", "time_expire", "goods_tag", "notify_url", "trade_type", "product_id",
                "limit_pay", "openid"};

        String stringA = "";
        String param = null;
        Arrays.sort(paramsArr);

        for (int i = 0; i < paramsArr.length; i++) {
            param = paramsArr[i];
            switch (param) {
                case "return_code":
                    String return_code = pr.getReturn_code();
                    if(return_code != null && !return_code.isEmpty()){
                        stringA += "&" + param + "=" + return_code;
                    }
                    break;
                case "return_msg":
                    String return_msg = pr.getReturn_msg();
                    if(return_msg != null && !return_msg.isEmpty()){
                        stringA += "&" + param + "=" + return_msg;
                    }
                    break;
                case "appid":
                    String appid = pr.getAppid();
                    if(appid != null && !appid.isEmpty()){
                        stringA += "&" + param + "=" + appid;
                    }
                    break;
                case "mch_id":
                    String mch_id = pr.getMch_id();
                    if(mch_id != null && !mch_id.isEmpty()){
                        stringA += "&" + param + "=" + mch_id;
                    }
                    break;
                case "device_info":
                    String device_info = pr.getDevice_info();
                    if(device_info != null && !device_info.isEmpty()){
                        stringA += "&" + param + "=" + device_info;
                    }
                    break;
                case "nonce_str":
                    String nonce_str = pr.getNonce_str();
                    if(nonce_str != null && !nonce_str.isEmpty()){
                        stringA += "&" + param + "=" + nonce_str;
                    }
                    break;
                case "result_code":
                    String result_code = pr.getResult_code();
                    if(result_code != null && !result_code.isEmpty()){
                        stringA += "&" + param + "=" + result_code;
                    }
                    break;
                case "err_code":
                    String err_code = pr.getErr_code();
                    if(err_code != null && !err_code.isEmpty()){
                        stringA += "&" + param + "=" + err_code;
                    }
                    break;
                case "err_code_des":
                    String err_code_des = pr.getErr_code_des();
                    if(err_code_des != null && !err_code_des.isEmpty()){
                        stringA += "&" + param + "=" + err_code_des;
                    }
                    break;
                case "openid":
                    String openid = pr.getOpenid();
                    if(openid != null && !openid.isEmpty()){
                        stringA += "&" + param + "=" + openid;
                    }
                    break;
                case "is_subscribe":
                    String is_subscribe = pr.getIs_subscribe();
                    if(is_subscribe != null && !is_subscribe.isEmpty()){
                        stringA += "&" + param + "=" + is_subscribe;
                    }
                    break;
                case "trade_type":
                    String trade_type = pr.getTrade_type();
                    if(trade_type != null && !trade_type.isEmpty()){
                        stringA += "&" + param + "=" + trade_type;
                    }
                    break;
                case "bank_type":
                    String bank_type = pr.getBank_type();
                    if(bank_type != null && !bank_type.isEmpty()){
                        stringA += "&" + param + "=" + bank_type;
                    }
                    break;
                case "total_fee":
                    String total_fee = pr.getTotal_fee();
                    if(total_fee != null && !total_fee.isEmpty()){
                        stringA += "&" + param + "=" + total_fee;
                    }
                    break;
                case "settlement_total_fee":
                    String settlement_total_fee = pr.getSettlement_total_fee();
                    if(settlement_total_fee != null && !settlement_total_fee.isEmpty()){
                        stringA += "&" + param + "=" + settlement_total_fee;
                    }
                    break;
                case "fee_type":
                    String fee_type = pr.getFee_type();
                    if(fee_type != null && !fee_type.isEmpty()){
                        stringA += "&" + param + "=" + fee_type;
                    }
                    break;
                case "cash_fee":
                    String cash_fee = pr.getCash_fee();
                    if(cash_fee != null && !cash_fee.isEmpty()){
                        stringA += "&" + param + "=" + cash_fee;
                    }
                    break;
                case "cash_fee_type":
                    String cash_fee_type = pr.getCash_fee_type();
                    if(cash_fee_type != null && !cash_fee_type.isEmpty()){
                        stringA += "&" + param + "=" + cash_fee_type;
                    }
                    break;
                case "coupon_fee":
                    String coupon_fee = pr.getCoupon_fee();
                    if(coupon_fee != null && !coupon_fee.isEmpty()){
                        stringA += "&" + param + "=" + coupon_fee;
                    }
                    break;
                case "coupon_count":
                    String coupon_count = pr.getCoupon_count();
                    if(coupon_count != null && !coupon_count.isEmpty()){
                        stringA += "&" + param + "=" + coupon_count;
                    }
                    break;
                case "coupon_type_$n":
                    String coupon_type_$n = pr.getCoupon_type_$n();
                    if(coupon_type_$n != null && !coupon_type_$n.isEmpty()){
                        stringA += "&" + param + "=" + coupon_type_$n;
                    }
                    break;
                case "coupon_id_$n":
                    String coupon_id_$n = pr.getCoupon_id_$n();
                    if(coupon_id_$n != null && !coupon_id_$n.isEmpty()){
                        stringA += "&" + param + "=" + coupon_id_$n;
                    }
                    break;
                case "coupon_fee_$n":
                    String coupon_fee_$n = pr.getCoupon_fee_$n();
                    if(coupon_fee_$n != null && !coupon_fee_$n.isEmpty()){
                        stringA += "&" + param + "=" + coupon_fee_$n;
                    }
                    break;
                case "transaction_id":
                    String transaction_id = pr.getTransaction_id();
                    if(transaction_id != null && !transaction_id.isEmpty()){
                        stringA += "&" + param + "=" + transaction_id;
                    }
                    break;
                case "out_trade_no":
                    String out_trade_no = pr.getOut_trade_no();
                    if(out_trade_no != null && !out_trade_no.isEmpty()){
                        stringA += "&" + param + "=" + out_trade_no;
                    }
                    break;
                case "attach":
                    String attach = pr.getAttach();
                    if(attach != null && !attach.isEmpty()){
                        attach += "&" + param + "=" + attach;
                    }
                    break;
                case "time_end":
                    String time_end = pr.getTime_end();
                    if(time_end != null && !time_end.isEmpty()){
                        stringA += "&" + param + "=" + time_end;
                    }
                    break;
                default:
                    break;
            }
        }
        stringA = stringA.substring(1);
        stringA += "&key=" + serverConfig.getProperty("app.wxpay.secret");

        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            crypt.update(stringA.getBytes("UTF-8"));
            String signature = byteToHex(crypt.digest()).toUpperCase();
            return signature;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    protected String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public WxPaymentResult parsePaymentResult(String xml){
        StringReader sr = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            sr = new StringReader(xml);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();

            Node return_codeNode = root.getElementsByTagName("return_code").item(0);
            Node return_msgNode = root.getElementsByTagName("return_msg").item(0);
            Node appidNode = root.getElementsByTagName("appid").item(0);
            Node mch_idNode = root.getElementsByTagName("mch_id").item(0);
            Node device_infoNode = root.getElementsByTagName("device_info").item(0);
            Node nonce_strNode = root.getElementsByTagName("nonce_str").item(0);
            Node signNode = root.getElementsByTagName("sign").item(0);
            Node result_codeNode = root.getElementsByTagName("result_code").item(0);
            Node err_codeNode = root.getElementsByTagName("err_code").item(0);
            Node err_code_desNode = root.getElementsByTagName("err_code_des").item(0);
            Node trade_typeNode = root.getElementsByTagName("trade_type").item(0);
            Node openidNode = root.getElementsByTagName("openid").item(0);
            Node is_subscribeNode = root.getElementsByTagName("is_subscribe").item(0);
            Node bank_typeNode = root.getElementsByTagName("bank_type").item(0);
            Node total_feeNode = root.getElementsByTagName("total_fee").item(0);
            Node settlement_total_feeNode = root.getElementsByTagName("settlement_total_fee").item(0);
            Node fee_typeNode = root.getElementsByTagName("fee_type").item(0);
            Node cash_feeNode = root.getElementsByTagName("cash_fee").item(0);
            Node cash_fee_typeNode = root.getElementsByTagName("cash_fee_type").item(0);
            Node coupon_feeNode = root.getElementsByTagName("coupon_fee").item(0);
            Node coupon_countNode = root.getElementsByTagName("coupon_count").item(0);
            Node coupon_type_$nNode = root.getElementsByTagName("coupon_type_$n").item(0);
            Node coupon_id_$nNode = root.getElementsByTagName("coupon_id_$n").item(0);
            Node coupon_fee_$nNode = root.getElementsByTagName("coupon_fee_$n").item(0);
            Node transaction_idNode = root.getElementsByTagName("transaction_id").item(0);
            Node out_trade_noNode = root.getElementsByTagName("out_trade_no").item(0);
            Node attachNode = root.getElementsByTagName("attach").item(0);
            Node time_endNode = root.getElementsByTagName("time_end").item(0);

            WxPaymentResult pr = new WxPaymentResult();
            if(return_codeNode != null){
                pr.setReturn_code(return_codeNode.getTextContent());
            }
            if(return_msgNode != null){
                pr.setReturn_msg(return_msgNode.getTextContent());
            }
            if(result_codeNode != null){
                pr.setResult_code(result_codeNode.getTextContent());
            }
            if(err_codeNode != null){
                pr.setErr_code(err_codeNode.getTextContent());
            }
            if(err_code_desNode != null){
                pr.setErr_code_des(err_code_desNode.getTextContent());
            }
            if(appidNode != null){
                pr.setAppid(appidNode.getTextContent());
            }
            if(mch_idNode != null){
                pr.setMch_id(mch_idNode.getTextContent());
            }
            if(device_infoNode != null){
                pr.setDevice_info(device_infoNode.getTextContent());
            }
            if(nonce_strNode != null){
                pr.setNonce_str(nonce_strNode.getTextContent());
            }
            if(signNode != null){
                pr.setSign(signNode.getTextContent());
            }
            if(trade_typeNode != null){
                pr.setTrade_type(trade_typeNode.getTextContent());
            }
            if(openidNode != null){
                pr.setOpenid(openidNode.getTextContent());
            }
            if(is_subscribeNode != null){
                pr.setIs_subscribe(is_subscribeNode.getTextContent());
            }
            if(bank_typeNode != null){
                pr.setBank_type(bank_typeNode.getTextContent());
            }
            if(total_feeNode != null){
                pr.setTotal_fee(total_feeNode.getTextContent());
            }
            if(settlement_total_feeNode != null){
                pr.setSettlement_total_fee(settlement_total_feeNode.getTextContent());
            }
            if(fee_typeNode != null){
                pr.setFee_type(fee_typeNode.getTextContent());
            }
            if(cash_feeNode != null){
                pr.setCash_fee(cash_feeNode.getTextContent());
            }
            if(cash_fee_typeNode != null){
                pr.setCash_fee_type(cash_fee_typeNode.getTextContent());
            }
            if(coupon_feeNode != null){
                pr.setCoupon_fee(coupon_feeNode.getTextContent());
            }
            if(coupon_countNode != null){
                pr.setCoupon_count(coupon_countNode.getTextContent());
            }
            if(coupon_type_$nNode != null){
                pr.setCoupon_type_$n(coupon_type_$nNode.getTextContent());
            }
            if(coupon_id_$nNode != null){
                pr.setCoupon_id_$n(coupon_id_$nNode.getTextContent());
            }
            if(coupon_fee_$nNode != null){
                pr.setCoupon_fee_$n(coupon_fee_$nNode.getTextContent());
            }
            if(transaction_idNode != null){
                pr.setTransaction_id(transaction_idNode.getTextContent());
            }
            if(out_trade_noNode != null){
                pr.setOut_trade_no(out_trade_noNode.getTextContent());
            }
            if(attachNode != null){
                pr.setAttach(attachNode.getTextContent());
            }
            if(time_endNode != null){
                pr.setTime_end(time_endNode.getTextContent());
            }

            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




}
