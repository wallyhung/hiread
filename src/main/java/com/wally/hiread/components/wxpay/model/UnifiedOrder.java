package com.wally.hiread.components.wxpay.model;

import com.wally.hiread.components.wxpay.WxpayConstant;
import com.wally.hiread.service.sys.AppUtil;
import com.wally.hiread.util.StringUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Properties;

/**
 * Created by eric on 19/06/2017.
 */
@XmlRootElement
public class UnifiedOrder {
    /**
     * 公众账号 ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名类型
     */
    private String sign_type;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 标价币种
     */
    private String fee_type;
    /**
     * 标价金额
     */
    private int total_fee;
    /**
     * 终端 IP
     */
    private String spbill_create_ip;
    /**
     * 交易起始时间
     */
    private String time_start;
    /**
     * 交易结束时间
     */
    private String time_expire;
    /**
     * 订单优惠标记
     */
    private String goods_tag;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 商品 ID
     */
    private String product_id;
    /**
     * 指定支付方式
     */
    private String limit_pay;
    /**
     * 用户标识
     */
    private String openid;

    public UnifiedOrder(String body, String out_trade_no, int total_fee, String notify_url) {
        this.init();

        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.notify_url = notify_url;
    }

    public UnifiedOrder(String body, String out_trade_no, int total_fee, String notify_url,
                        String detail, String attach, String goods_tag, String product_id) {
        this.init();

        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.notify_url = notify_url;
        this.detail = detail;
        this.attach = attach;
        this.goods_tag = goods_tag;
        this.product_id = product_id;
    }

    public UnifiedOrder() {
        this.init();
    }

    private void init() {
        Properties serverConfig = (Properties) AppUtil.getApplicationContext().getBean("serverConfig");
        this.appid = serverConfig.getProperty("app.wxmp.appid");
        this.mch_id = serverConfig.getProperty("app.wxpay.mchid");
        this.device_info = WxpayConstant.DEVICE_INFO_WEB;
        this.nonce_str = StringUtil.random(32);
        this.sign = WxpayConstant.SIGN_TYPE_MD5;
        this.trade_type = WxpayConstant.TRADE_TYPE_NATIVE;
    }

    @XmlElement
    public String getAppid() {
        return appid;
    }

    private void setAppid(String appid) {
        this.appid = appid;
    }

    @XmlElement
    public String getMch_id() {
        return mch_id;
    }

    private void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    @XmlElement
    public String getDevice_info() {
        return device_info;
    }

    private void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    @XmlElement
    public String getNonce_str() {
        return nonce_str;
    }

    private void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    @XmlElement
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @XmlElement
    public String getSign_type() {
        return sign_type;
    }

    private void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    @XmlElement
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @XmlElement
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @XmlElement
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    @XmlElement
    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @XmlElement
    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    @XmlElement
    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    @XmlElement
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    @XmlElement
    public String getTime_start() {
        return time_start;
    }

    private void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    @XmlElement
    public String getTime_expire() {
        return time_expire;
    }

    private void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    @XmlElement
    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    @XmlElement
    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    @XmlElement
    public String getTrade_type() {
        return trade_type;
    }

    private void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    @XmlElement
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @XmlElement
    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    @XmlElement
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
