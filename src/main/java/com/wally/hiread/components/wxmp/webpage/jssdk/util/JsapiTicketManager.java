package com.wally.hiread.components.wxmp.webpage.jssdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.components.wxmp.AccessTokenManager;
import com.wally.hiread.components.wxmp.WxmpConst;
import com.wally.hiread.components.wxmp.WxmpException;
import com.wally.hiread.components.wxmp.webpage.jssdk.model.JsapiTicket;
import com.wally.hiread.util.DateUtil;
import com.wally.hiread.util.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by eric on 07/06/2017.
 */
@Service
public class JsapiTicketManager {
    private static JsapiTicket jsapiTicket;
    private static final int DATE_EXPIRED_THRESHOLD = 10 * 60;// 失效时间阈值。单位，秒。



    public synchronized static String getJsapiTicketString() throws Exception {
        if(jsapiTicket == null){
            fetch();
        } else {
            if(!isValid()){
                fetch();
            }
        }
        return jsapiTicket.getValue();
    }

    private static boolean isValid() {
        if (jsapiTicket != null) {
            String value = jsapiTicket.getValue();
            Date dateModified = jsapiTicket.getDateModified();
            int validDate = jsapiTicket.getValidTime();

            if ((value != null) && (dateModified != null) && (validDate != 0)) {
                Date currentDate = DateUtil.currentDate();
                long timeExpired = dateModified.getTime() + validDate * 1000 - DATE_EXPIRED_THRESHOLD * 1000;
                if (timeExpired >= currentDate.getTime()) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void fetch() throws Exception {
        StringBuffer url = new StringBuffer();
        url.append(WxmpConst.WXMP_API_ADDRESS).append("/cgi-bin/ticket/getticket?type=jsapi")
                .append("&access_token=").append(AccessTokenManager.getAccessTokenString());

        String result = HttpUtil.get(url.toString());

        JSONObject jsonResult = JSON.parseObject(result);
        if (!jsonResult.containsKey("errcode") && jsonResult.getInteger("errcode") == 0) {
            if(jsapiTicket == null){
                jsapiTicket = new JsapiTicket();
            }
            jsapiTicket.setValue(jsonResult.getString("ticket"));
            Date currentDate = DateUtil.currentDate();
            jsapiTicket.setDateCreated(currentDate);
            jsapiTicket.setDateModified(currentDate);
            jsapiTicket.setValidTime(jsonResult.getInteger("expires_in"));
        } else {
            throw new WxmpException(WxmpException.GET_ACCESS_TOKEN_FAIL, jsonResult.toString());
        }
    }
}
