package com.wally.hiread.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wally.hiread.model.sys.SR;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtil {
	private static Logger logger =Logger.getLogger(HttpUtil.class);
	public static String get(String reqUrl) throws Exception{
		String result="";
		logger.info("url:"+reqUrl);
    	URL url = new URL(reqUrl);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("Charset", "UTF-8");
		
		// 获得响应状态
		int responseCode = httpConn.getResponseCode();
		if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
			// 当正确响应时处理数据
			StringBuffer sb = new StringBuffer();
			String readLine;
			BufferedReader responseReader;
			// 处理响应流，必须与服务器响应流输出的编码一致
			responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine);
			}
			responseReader.close();
			result=sb.toString();
		}
    	logger.info("请求返回:"+result);
	    return result;
	}
	
	public static SR<JSONObject> jogetWsPost(String actionUrl,String extraParams){
		SR<JSONObject> sr=new SR<JSONObject>();
		try{
			String result="";
	    	logger.info("请求url:"+actionUrl+",参数:"+extraParams);
	    	URL url = new URL(actionUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type", "application/octet-stream");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("Charset", "UTF-8");
			httpConn.setConnectTimeout(15000);  
			httpConn.setReadTimeout(15000);  
			
			byte[] requestStringBytes = extraParams.getBytes("UTF-8");
			httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
			DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
			out.write(requestStringBytes);
			out.close();
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine);
				}
				responseReader.close();
				result=sb.toString();
			}
	    	logger.info("请求返回:"+result);
	    	JSONObject json= JSON.parseObject(result);
			sr.setStatus("success");
		    sr.setMsg(result);
		    sr.setEntity(json);
		}catch(Exception ex){
			sr.setStatus("fail");
		    sr.setMsg("web service request exception:"+ex.getMessage());
		    logger.error(ex.getMessage(),ex );
		}
		return sr;
	}
	/**获取ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip="unknow"; 
		if (request!=null) {
			ip = request.getHeader("x-forwarded-for");  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    } 
		} 
	    return ip;  
	}

	public static String doXmlPost(String uri, String requestBody){
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(uri);
		ContentProducer cp = new ContentProducer() {
			@Override
			public void writeTo(OutputStream outputStream) throws IOException {
				Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
				writer.write(requestBody);
				writer.flush();
			}
		};
		HttpEntity requestEntity = new EntityTemplate(cp);
		httpPost.setEntity(requestEntity);

		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();

			if (responseEntity != null) {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(responseEntity.getContent()));
				String result = "";
				String text;
				while ((text = bufferedReader.readLine()) != null) {
					result += text;
				}
				EntityUtils.consume(responseEntity);
				EntityUtils.consume(requestEntity);

				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(response!=null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String getBaseUrl(HttpServletRequest request){
		String protocol = request.getProtocol().toLowerCase();
		protocol = protocol.substring(0, protocol.indexOf("/"));
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getContextPath();

		if(port==443){
			protocol="https";
		}
		if(port==443||port==80){
			return protocol + "://" + serverName  + contextPath + "/";
		}else{
			return protocol + "://" + serverName + ":" + String.valueOf(port) + contextPath + "/";
		}

	}

	public static String getInputStreamString(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		StringBuffer data = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String line;
		while((line = br.readLine()) != null){
			data.append(line);
		}
		br.close();

		return data.toString();
	}
}
