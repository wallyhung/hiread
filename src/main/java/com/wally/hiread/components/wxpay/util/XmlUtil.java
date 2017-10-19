package com.wally.hiread.components.wxpay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eric on 20/06/2017.
 */
public class XmlUtil {

    /**
     * Convert sample Java Bean to XML string.
     * @param javaBean
     * @return
     */
    public static String sampleJavaBean2Xml(Object javaBean){
        JAXBContext jaxbContext = null;
        ByteArrayOutputStream baos = null;
        try {
            jaxbContext = JAXBContext.newInstance(javaBean.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            baos = new ByteArrayOutputStream();
            marshaller.marshal(javaBean, baos);

            String result = decorate(baos.toString(), javaBean.getClass().getName());

            return result;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected static String decorate(String contentXml, String rootClassName){
        if(contentXml != null && rootClassName != null){
            String rootElement = rootClassName.substring(rootClassName.lastIndexOf(".") + 1);
            String lowerRootElement = rootElement.substring(0, 1).toLowerCase() + rootElement.substring(1);

            Map<String, String> replace = new HashMap<String, String>();
            replace.put("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
            replace.put("<" + lowerRootElement + ">", "<xml>");
            replace.put("</" + lowerRootElement + ">", "</xml>");

            for (Map.Entry<String, String> entry : replace.entrySet()) {
                if(contentXml.contains(entry.getKey())){
                    contentXml = contentXml.replace(entry.getKey(), entry.getValue());
                }
            }
        }

        return contentXml;
    }

    /**
     * convert xml to json object
     * @param xmlContent
     * @return
     */
    public static JSONObject toJsonObject(String xmlContent){
        return JSON.parseObject(xmlContent);
    }

}
