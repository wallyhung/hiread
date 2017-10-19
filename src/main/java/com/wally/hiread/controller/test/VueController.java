package com.wally.hiread.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test/vue")
public class VueController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String vue() {
		return "test/vue/index";
	}
}
