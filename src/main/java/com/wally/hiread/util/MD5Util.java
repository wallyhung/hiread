package com.wally.hiread.util;

import java.lang.Character.UnicodeBlock;
import java.security.MessageDigest;

public class MD5Util {
/**
 * MD5 单项加密
 * @param inStr
 * @return
 */
	  public static String string2MD5(String inStr){  
	        MessageDigest md5 = null;  
	        try{  
	            md5 = MessageDigest.getInstance("MD5");  
	        }catch (Exception e){  
	            System.out.println(e.toString());  
	            e.printStackTrace();  
	            return "";  
	        }  
	        char[] charArray = inStr.toCharArray();  
	        byte[] byteArray = new byte[charArray.length];  
	  
	        for (int i = 0; i < charArray.length; i++)  
	            byteArray[i] = (byte) charArray[i];  
	        byte[] md5Bytes = md5.digest(byteArray);  
	        StringBuffer hexValue = new StringBuffer();  
	        for (int i = 0; i < md5Bytes.length; i++){  
	            int val = ((int) md5Bytes[i]) & 0xff;  
	            if (val < 16)  
	                hexValue.append("0");  
	            hexValue.append(Integer.toHexString(val));  
	        }  
	        return hexValue.toString();  
	  
	    }  
	  
	  /**
	   * 双向加密
	   * @param inStr
	   * @return
	   */
	    public static String convertMD5(String inStr){  
	  
	        char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
	        }  
	        String s = new String(a);  
	        return s;  
	  
	    }  
	    /**
	     * utf-8 转换成 unicode
	     * @author fanhui
	     * 2007-3-15
	     * @param inStr
	     * @return
	     */
	    public static String utf8ToUnicode(String inStr) {
	           char[] myBuffer = inStr.toCharArray();
	           
	           StringBuffer sb = new StringBuffer();
	           for (int i = 0; i < inStr.length(); i++) {
	            UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
	               if(ub == UnicodeBlock.BASIC_LATIN){
	                //英文及数字等
	                sb.append(myBuffer[i]);
	               }else if(ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS){
	                //全角半角字符
	                int j = (int) myBuffer[i] - 65248;
	                sb.append((char)j);
	               }else{
	                //汉字
	                short s = (short) myBuffer[i];
	                   String hexS = Integer.toHexString(s  & 0xffff);
	                   String unicode = "\\u"+hexS;
	                sb.append(unicode.toLowerCase());
	               }
	           }
	           return sb.toString();
	       }
	    
	    /**
	     * unicode 转换成 utf-8
	     * @author fanhui
	     * 2007-3-15
	     * @param theString
	     * @return
	     */
	    public static String unicodeToUtf8(String theString) {
	     char aChar;
	     int len = theString.length();
	     StringBuffer outBuffer = new StringBuffer(len);
	     for (int x = 0; x < len;) {
	      aChar = theString.charAt(x++);
	      if (aChar == '\\') {
	       aChar = theString.charAt(x++);
	       if (aChar == 'u') {
	        // Read the xxxx
	        int value = 0;
	        for (int i = 0; i < 4; i++) {
	         aChar = theString.charAt(x++);
	         switch (aChar) {
	         case '0':
	         case '1':
	         case '2':
	         case '3':
	         case '4':
	         case '5':
	         case '6':
	         case '7':
	         case '8':
	         case '9':
	          value = (value << 4) + aChar - '0';
	          break;
	         case 'a':
	         case 'b':
	         case 'c':
	         case 'd':
	         case 'e':
	         case 'f':
	          value = (value << 4) + 10 + aChar - 'a';
	          break;
	         case 'A':
	         case 'B':
	         case 'C':
	         case 'D':
	         case 'E':
	         case 'F':
	          value = (value << 4) + 10 + aChar - 'A';
	          break;
	         default:
	          throw new IllegalArgumentException(
	            "Malformed   \\uxxxx   encoding.");
	         }
	        }
	        outBuffer.append((char) value);
	       } else {
	        if (aChar == 't')
	         aChar = '\t';
	        else if (aChar == 'r')
	         aChar = '\r';
	        else if (aChar == 'n')
	         aChar = '\n';
	        else if (aChar == 'f')
	         aChar = '\f';
	        outBuffer.append(aChar);
	       }
	      } else
	       outBuffer.append(aChar);
	     }
	     return outBuffer.toString();
	    }
	   
  
}
