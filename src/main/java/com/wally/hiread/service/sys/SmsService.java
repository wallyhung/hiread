package com.wally.hiread.service.sys;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SmsService {
    @Autowired
    LogService log;
    @Autowired
    SetupService setupService;
    @Autowired
    JogetService jogetService;

    public SR sendSms(String mobile, String[] replaceParams, String serviceType) {
        SR sr = new SR();
        try {
            Setup setup = setupService.loadSetup();
            if (setup == null) {
                sr.setStatus(AppCons.SR_FAIL);
                log.systemError(AppCons.SYSTEM_ERROR_SETTING, "系统表未设置", null);
                sr.setMsg("系统异常");
                return sr;
            }
            String url = setup.getSmsUrl();
            url += "?action=send&userid=&extno=";

            String account = setup.getSmsAccount();
            url += "&account=" + account;

            String password = setup.getSmsPassword();
            /* 密码暂设置成明文，不需要解密 */
//			SR<String> decryptResult=jogetService.decrypt(password);
//			if(decryptResult.getStatus().equals(AppCons.SR_FAIL)){
//				sr.setStatus("fail");
//				sr.setMsg(decryptResult.getMsg());
//				return sr;
//			}
//			url+="&password="+decryptResult.getEntity();
            url += "&password=" + password;

            mobile = mobile.replaceAll(";", ",");
            url += "&mobile=" + mobile;

            String content = setup.getSignature();

            switch (serviceType) {
                case AppCons.SMS_TYPE_REGISTER:
                    content += "您的注册申请验证码为" + replaceParams[0] + "，请勿告诉他人。";
                    break;
                case AppCons.SMS_TYPE_FIND_PASS:
                    content += "您正在找回密码，验证码为" + replaceParams[0] + "，请勿告诉他人。";
                    break;
                default:
                    content += "您的验证码为" + replaceParams[0] + "，请勿告诉他人。";
                    break;
            }
            url += "&content=" + URLEncoder.encode(content, "UTF-8");

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sendTime = fmt.format(new Date());
            url += "&sendTime=" + URLEncoder.encode(sendTime, "UTF-8");

            String result = HttpUtil.get(url);
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(result));

            Document document = db.parse(is);

            NodeList returnstatusNode = document.getElementsByTagName("returnstatus");
            if (returnstatusNode != null && "Success".equals(returnstatusNode.item(0).getTextContent())) {
                sr.setStatus(AppCons.SR_SUCCESS);
                return sr;
            } else {
                sr.setStatus(AppCons.SR_FAIL);
                log.systemError(AppCons.SYSTEM_ERROR_SERVICE, "", null);
                sr.setMsg("发送失败");
            }

            return sr;
        } catch (Exception e) {
            sr.setStatus(AppCons.SR_FAIL);
            sr.setMsg("系统异常");
            log.systemError(AppCons.SYSTEM_ERROR_RUNTIME, e.getMessage(), e);
            return sr;
        }
    }

}
