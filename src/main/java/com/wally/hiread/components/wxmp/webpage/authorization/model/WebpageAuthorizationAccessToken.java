package com.wally.hiread.components.wxmp.webpage.authorization.model;

/**
 * Created by eric on 04/07/2017.
 */
@Deprecated
public class WebpageAuthorizationAccessToken {
    private static final WebpageAuthorizationAccessToken webpageAuthorizationAccessToken = new WebpageAuthorizationAccessToken();
    private String value;

    private WebpageAuthorizationAccessToken() {}

    public static WebpageAuthorizationAccessToken getInstance(){
        return webpageAuthorizationAccessToken;
    }

    public synchronized String getValue(String code){

        return null;
    }



}
