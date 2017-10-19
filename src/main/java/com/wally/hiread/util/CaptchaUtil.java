package com.wally.hiread.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaUtil {
	private Random random = new Random();
	public String getMobileVerifyCode(){
		String range="0123456789";
		int size=4;
		
		return getRandom(range,size);
	}
	public String getCaptchaCode(){
		String range="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int size=4;
		
		return getRandom(range,size);
	}
	public BufferedImage getCaptchaImg(String captchaCode){
		int width=70;
		int height=34;
		int lineSize=40;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));
        //绘制干扰线
        for(int i=0;i<=lineSize;i++){
            drowLine(width,height,g);
        }  
        for(int i=0;i<captchaCode.length();i++){
            drowString(g,String.valueOf(captchaCode.charAt(i)),i);
        }
        g.dispose();
        return image;
	}
	/*
	 * range:随机产生的字符串源
	 * size:随机产生字符数量
	 */
	private String getRandom(String range,int size){
		String result="";
		for(int i=0;i<size;i++){
			int position = random.nextInt(range.length());
			String str = String.valueOf(range.charAt(position));
			result+=str;
		}
        return result;
    }
	/*
     * 获得颜色
     */
    private Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    /*
     * 绘制干扰线
     */
    private void drowLine(int width,int height,Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }
    /*
     * 绘制字符串
     */
    private void drowString(Graphics g,String str,int i){
        g.setFont(new Font("Fixedsys",Font.CENTER_BASELINE,18));
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(str, 16*i, 20);
    }
	
}
