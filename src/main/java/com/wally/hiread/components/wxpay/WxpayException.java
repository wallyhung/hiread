package com.wally.hiread.components.wxpay;

/**
 * Created by eric on 19/06/2017.
 */
public class WxpayException extends Exception{

    public WxpayException(String message){
        super(message);
    }

    public WxpayException(String message, String detail){
        super(message + " - " + detail);
    }
}
