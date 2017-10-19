package com.wally.hiread.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test/video")
public class VideoController {
	@RequestMapping(value = "/playbackControl", method = RequestMethod.GET)
	public String vue() {
		return "test/video/playbackControl";
	}
	@RequestMapping(value = "/videojs/videojs", method = RequestMethod.GET)
	public String videojs() {
		return "test/video/videojs/videojs";
	}
}
