package com.wally.hiread.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class CssController {
	@RequestMapping(value = "/css", method = RequestMethod.GET)
	public String css() {
		return "test/css";
	}
}
