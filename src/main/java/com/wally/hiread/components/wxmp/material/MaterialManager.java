package com.wally.hiread.components.wxmp.material;

import com.wally.hiread.components.wxmp.AccessToken;
import com.wally.hiread.components.wxmp.AccessTokenManager;
import com.wally.hiread.components.wxmp.WxmpConst;
import com.wally.hiread.components.wxmp.WxmpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by eric on 07/06/2017.
 */
@Service
public class MaterialManager {
    @Autowired
    Properties serverConfig;

    public String downloadTempMaterial(String type, String mediaId) throws Exception {
        if (!WxmpConst.TEMP_MATERIAL_FORMAT_AUDIO.equals(type)) {
            throw new WxmpException(WxmpException.FILE_TYPE_ERROR);
        }

        File targetDir = new File(serverConfig.getProperty("file.save.path.audio"));
        String accessTokenString = AccessTokenManager.getAccessTokenString();
        return downloadAudio(accessTokenString, mediaId, targetDir);
    }

    private String downloadAudio(String accessToken, String mediaId, File targetDir) throws IOException, WxmpException {
        StringBuffer urlStr = new StringBuffer();
        urlStr.append(WxmpConst.WXMP_API_ADDRESS).append("/cgi-bin/media/get?access_token=")
                .append(accessToken).append("&media_id=").append(mediaId);

        URL url = null;
        BufferedReader br = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        HttpsURLConnection connection = null;
        try {
            url = new URL(urlStr.toString());
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpServletResponse.SC_OK) {
                String contentType = connection.getContentType();
                if (contentType.contains("audio")) {
                    String ContentDisposition = connection.getHeaderFields().get("Content-disposition").get(0);
                    String filename = ContentDisposition.substring(ContentDisposition.indexOf("filename") + 10, ContentDisposition.length() - 1);

                    // 如果目标文件夹错误，则将文件保存到临时文件夹
                    if((targetDir == null) || !targetDir.isDirectory()){
                        throw new WxmpException(WxmpException.TARGET_DIRECTORY_ERROR, contentType);
                    }

                    bis = new BufferedInputStream(connection.getInputStream());
                    bos = new BufferedOutputStream(new FileOutputStream(new File(targetDir, filename)));
                    int length = 0;
                    byte[] buffer = new byte[1024];
                    while ((length = bis.read(buffer)) != -1){
                        bos.write(buffer, 0, length);
                    }

                    return filename;
                } else if (contentType.contains("json")) {
                    br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String result = "";
                    String line;
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                    throw new WxmpException(WxmpException.DOWNLOAD_AUDIO_FAIL, result);
                } else {
                    throw new WxmpException(WxmpException.UNKNOW_CONTENT_TYPE, contentType);
                }
            } else {
                throw new WxmpException(WxmpException.WX_SERVER_RESPONSE_FAIL);
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if(bis != null){
                bis.close();
            }
            if(bos != null){
                bos = null;
            }
            if(connection != null){
                connection.disconnect();
            }
        }
    }


}
