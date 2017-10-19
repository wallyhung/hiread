package com.wally.hiread.components.wxmp.webpage.authorization.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.components.wxmp.webpage.authorization.model.WxmpUserInfo;
import com.wally.hiread.components.wxmp.webpage.authorization.model.WebpageAuthorizationConst;
import com.wally.hiread.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by eric on 04/07/2017.
 */
@Service
public class WebpageAuthorizationService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    Properties serverConfig;
    /**
     * 授权
     *
     * @param type        授权类型。参见 WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_SCOPE_*
     * @param state       自定义参数。
     * @param redirectUri 授权成功后重定向地址。
     */
    public String authorize(String type, String state, String redirectUri) {
        String baseUrl = serverConfig.getProperty("app.url.base");
        try {
            String encodedRedirectUri = URLEncoder.encode(redirectUri, "UTF-8");
            String redirect_uri = baseUrl + "?redirectUri=" + encodedRedirectUri;
            String wxAuthUrl=generateReferenceUrl(redirect_uri, type, state);
            log.info("wx auth url:"+wxAuthUrl);
            return wxAuthUrl;
        } catch (UnsupportedEncodingException e) {}
        return null;
    }


    /**
     * 生成微信授权链接
     *
     * @param redirectUri 回调地址
     * @param scope       授权作用域
     * @param state       自定义参数
     * @return
     */
    public String generateReferenceUrl(String redirectUri, String scope, String state) {
        StringBuilder url = null;
        String appid = serverConfig.getProperty("app.wxmp.appid");
        try {
            redirectUri = URLEncoder.encode(redirectUri, "UTF-8");
            url = new StringBuilder();
            url.append(WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_BASEURL)
                    .append("/connect/oauth2/authorize")
                    .append("?appid=").append(appid)
                    .append("&redirect_uri=").append(redirectUri)
                    .append("&response_type=").append(WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_RESPONSE_TYPE_CODE)
                    .append("&scope=").append(scope)
                    .append("&state=").append(state)
                    .append("#wechat_redirect");
            return url.toString();
        } catch (UnsupportedEncodingException e) {}
        return null;
    }

    /**
     * 获取网页授权 access_token
     *
     * @param code
     * @return
     */
    public Map<String, String> getWebpageAuthorizationAccessToken(String code) {
        Map<String, String> accessToken = new HashMap<String, String>();
        StringBuilder url = new StringBuilder();
        String appid = serverConfig.getProperty("app.wxmp.appid");
        String secret = serverConfig.getProperty("app.wxmp.secret");

        url.append(WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_API_BASEURL)
                .append("/sns/oauth2/access_token")
                .append("?appid=").append(appid)
                .append("&secret=").append(secret)
                .append("&code=").append(code)
                .append("&grant_type=").append(WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_GRANT_TYPE);
        try {
            String result = HttpUtil.get(url.toString());
            JSONObject resultObj = JSON.parseObject(result);
            // 如果返回 access_token,表示成功
            if (resultObj.containsKey("access_token")) {
                accessToken.put("access_token", resultObj.getString("access_token"));
                accessToken.put("openid", resultObj.containsValue("openid") ? resultObj.getString("openid") : null);
                return accessToken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     *
     * @param accessToken 接口调用凭证
     * @param openid      用户在公众号下的唯一标识
     * @return
     */
    public WxmpUserInfo getUserInfo(String accessToken, String openid) {
        StringBuilder url = new StringBuilder();
        url.append(WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_API_BASEURL)
                .append("/sns/userinfo")
                .append("?access_token=").append(accessToken)
                .append("&openid=").append(openid)
                .append("&lang=").append(WebpageAuthorizationConst.WEBPAGE_AUTHORIZATION_LANG_CN);
        try {
            String result = HttpUtil.get(url.toString());
            JSONObject resultObj = JSON.parseObject(result);
            // 如果返回 openid,表示成功
            if (resultObj.containsValue("openid")) {
                WxmpUserInfo wxmpUserInfo = new WxmpUserInfo();
                wxmpUserInfo.setOpenid(resultObj.getString("openid"));
                wxmpUserInfo.setNickname(resultObj.containsValue("nickname") ? resultObj.getString("nickname") : null);
                wxmpUserInfo.setSex(resultObj.containsValue("sex") ? resultObj.getString("sex") : null);
                wxmpUserInfo.setProvince(resultObj.containsValue("province") ? resultObj.getString("province") : null);
                wxmpUserInfo.setCity(resultObj.containsValue("city") ? resultObj.getString("city") : null);
                wxmpUserInfo.setCountry(resultObj.containsValue("country") ? resultObj.getString("country") : null);
                wxmpUserInfo.setHeadimgurl(resultObj.containsValue("headimgurl") ? resultObj.getString("headimgurl") : null);
                wxmpUserInfo.setPrivilege(resultObj.containsValue("privilege") ? resultObj.getJSONArray("privilege").toString() : null);
                wxmpUserInfo.setUnionid(resultObj.containsValue("unionid") ? resultObj.getString("unionid") : null);
                return wxmpUserInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
