package com.wally.hiread.components.wxmp;

/**
 * Created by eric on 07/06/2017.
 */
public class WxmpException extends Exception{
    public static final String GET_ACCESS_TOKEN_FAIL = "get access_token fail";
    public static final String WX_SERVER_RESPONSE_FAIL = "wechat server response fail";
    public static final String UNKNOW_CONTENT_TYPE = "unknow content type";
    public static final String DOWNLOAD_AUDIO_FAIL = "download audio fail";
    public static final String TARGET_DIRECTORY_ERROR = "target directory not exist or is not a directory";
    public static final String FILE_TYPE_ERROR = "file type error";

    public WxmpException(String message){
        super(message);
    }

    public WxmpException(String message, String description){
        super(message + ": " + description);
    }

}
