package com.wally.hiread.controller.sys;

import com.wally.hiread.model.sys.SR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;


@Controller
@RequestMapping("/sys/config")
public class ConfigController {
	@Autowired
	Properties serverConfig;

	@RequestMapping(value = "/url/base", method = RequestMethod.GET)
	public @ResponseBody SR<String> getUrl() {
		SR<String> sr=new SR<String>();
		String url = serverConfig.getProperty("app.url.base");
		sr.setEntity(url);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	

	
}
