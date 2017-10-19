package com.wally.hiread.util;

import java.util.regex.Pattern;

public class RegExpValidator {
	public static final String REGEX_NUMBER="^\\d+(\\.\\d+)?$";
	public static final String REGEX_INTEGER="^\\d+$";
	 /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
 
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^.{6,20}$";
    /**
     * 正则表达式：验证图片验证码
     */
    public static final String REGEX_CAPTCHA = "^[0-9a-zA-Z]{4}$";
 
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^[1][0-9]\\d{9}$";
    /**
     * 正则表达式：验证手机验证码
     */
    public static final String REGEX_MOBILE_VERIFY_CODE = "^[0-9]{4}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
 
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
 
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    
    /**
     * 正则表达式：验证QQ
     */
    public static final String REGEX_QQ = "[1-9][0-9]{4,}";
    
    /**
     * 正则表达式：验证日期
     */
    public static final String REGEX_DATE = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
    
    
    public static boolean isNumber(String number) {
        return Pattern.matches(REGEX_NUMBER, number);
    }
    public static boolean isInteger(String number) {
        return Pattern.matches(REGEX_INTEGER, number);
    }
    /**
     * 校验用户名
     * 
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    //用户名不能有特殊字符
    public static boolean isUsernameWithoutSpe(String username){
        return Pattern.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5\\\\s·]+$", username);
    }
    /**
     * 校验密码
     * 
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    /**
     * 校验图片验证码
     * 
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isCaptcha(String captcha) {
        return Pattern.matches(REGEX_CAPTCHA, captcha);
    }
 
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
    /**
     * 校验手机验证码
     * 
     * @param mobileVerifyCode
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobileVerifyCode(String mobileVerifyCode) {
        return Pattern.matches(REGEX_MOBILE_VERIFY_CODE, mobileVerifyCode);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * 校验汉字
     * 
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
 
    /**
     * 校验URL
     * 
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * 校验IP地址
     * 
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
    /**
     * 校验QQ
     * 
     * @param qq
     * @return
     */
    public static boolean isQQ(String qq) {
        return Pattern.matches(REGEX_QQ, qq);
    }
    /**
     * 校验日期
     * 
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        return Pattern.matches(REGEX_DATE, date);
    }
    
}
