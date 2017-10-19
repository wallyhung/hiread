package com.wally.hiread.components.wxmp.webpage.jssdk.util;

import com.wally.hiread.components.wxmp.webpage.jssdk.model.Signature;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

/**
 * Created by eric on 07/06/2017.
 */
public class SignatureUtil {

    public static Signature generateSignature(String jsapiTicket, String url) {
        Signature signature = new Signature();

        String noncestr = createNoncestr();
        String timestamp = createTimestamp();
        String tempStr;
        String value = "";

        // 注意这里参数名必须全部小写，且必须有序
        tempStr = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(tempStr.getBytes("UTF-8"));
            value = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        signature.setJsapiTicket(jsapiTicket);
        signature.setNoncestr(noncestr);
        signature.setTimestamp(timestamp);
        signature.setUrl(url);
        signature.setValue(value);

        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String createNoncestr() {
        return UUID.randomUUID().toString();
    }

    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
