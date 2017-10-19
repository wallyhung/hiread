package com.wally.hiread.controller.index;

import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.dao.user.UserMapper;
import com.wally.hiread.service.user.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/")
public class IndexController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	UnitMapper uMapper;
	@Autowired
	private Environment environment;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String root() {
		return "index";
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}


}
