package com.wally.hiread.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private static Properties PROP = null;
	private PropertyReader(){
		
	}
	public static String getValue(String fileName,String key) {

		try {
			
			if (PROP == null) {
				InputStream in = PropertyReader.class.getResourceAsStream("/"+fileName);
				PROP = new Properties();
				PROP.load(in);
			}

			return PROP.getProperty(key);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}
}
