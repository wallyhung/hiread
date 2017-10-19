package com.wally.hiread.components.wxmp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.service.sys.AppUtil;
import com.wally.hiread.util.DateUtil;
import com.wally.hiread.util.HttpUtil;
import java.util.Date;
import java.util.Properties;

/**
 * Created by eric on 14/07/2017.
 */
public class AccessTokenManager {
    private static AccessToken accessToken;
    private static final int DATE_EXPIRED_THRESHOLD = 10 * 60;// 失效时间阈值。单位，秒。

    public synchronized static String getAccessTokenString() throws Exception {
        if(accessToken == null){
            fetch();
        } else {
            if(!isValid()){
                fetch();
            }
        }
        return accessToken.getValue();
    }

    private static void fetch() throws Exception {
        Properties serverConfig = (Properties) AppUtil.getApplicationContext().getBean("serverConfig");
        String appid = serverConfig.getProperty("app.wxmp.appid");
        String secret = serverConfig.getProperty("app.wxmp.secret");

        StringBuffer url = new StringBuffer();
        url.append(WxmpConst.WXMP_API_ADDRESS).append("/cgi-bin/token?grant_type=client_credential")
                .append("&appid=").append(appid).append("&secret=").append(secret);

        String result = HttpUtil.get(url.toString());

        JSONObject jsonResult = JSON.parseObject(result);
        if (!jsonResult.containsKey("access_token")) {
            if(accessToken == null){
                accessToken = new AccessToken();
            }
            accessToken.setValue(jsonResult.getString("access_token"));
            Date currentDate = DateUtil.currentDate();
            accessToken.setDateCreated(currentDate);
            accessToken.setDateModified(currentDate);
            accessToken.setValidTime(jsonResult.getInteger("expires_in"));
        } else {
            throw new WxmpException(WxmpException.GET_ACCESS_TOKEN_FAIL, jsonResult.toString());
        }
    }

    private static boolean isValid() {
        if (accessToken != null) {
            String value = accessToken.getValue();
            Date dateModified = accessToken.getDateModified();
            int validDate = accessToken.getValidTime();

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
}
