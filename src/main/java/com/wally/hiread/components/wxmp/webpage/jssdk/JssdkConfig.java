package com.wally.hiread.components.wxmp.webpage.jssdk;

import com.wally.hiread.components.wxmp.webpage.jssdk.model.JsapiTicket;
import com.wally.hiread.components.wxmp.webpage.jssdk.model.Signature;
import com.wally.hiread.components.wxmp.webpage.jssdk.util.JsapiTicketManager;
import com.wally.hiread.components.wxmp.webpage.jssdk.util.SignatureUtil;
import com.wally.hiread.service.sys.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by eric on 07/06/2017.
 */
@Service
public class JssdkConfig {
    public JssdkConfig getAuthConfiguration(String url) throws Exception {
        JssdkConfig jssdkConfig = new JssdkConfig();

        Properties serverConfig = (Properties) AppUtil.getApplicationContext().getBean("serverConfig");
        String appid = serverConfig.getProperty("app.wxmp.appid");

        Signature signature = SignatureUtil.generateSignature(JsapiTicketManager.getJsapiTicketString(), url);

        String noncestr = signature.getNoncestr();
        String timestamp = signature.getTimestamp();

        jssdkConfig.setAppid(appid);
        jssdkConfig.setNoncestr(noncestr);
        jssdkConfig.setTimestamp(timestamp);
        jssdkConfig.setSignature(signature.getValue());

        return jssdkConfig;
    }


    private String appid;
    private String timestamp;
    private String noncestr;
    private String signature;

    public String getAppid() {
        return appid;
    }

    private void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    private void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    private void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getSignature() {
        return signature;
    }

    private void setSignature(String signature) {
        this.signature = signature;
    }
}
