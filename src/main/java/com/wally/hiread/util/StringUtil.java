package com.wally.hiread.util;

import java.util.Random;

/**
 * Created by eric on 20/06/2017.
 */
public class StringUtil {

    /**
     * 生成随机数
     * @param length
     * @return
     */
    public static String random(int length){
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
