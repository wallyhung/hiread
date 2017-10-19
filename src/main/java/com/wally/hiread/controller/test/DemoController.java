package com.wally.hiread.controller.test;

import com.wally.hiread.model.sys.SR;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


@Controller
@RequestMapping("/test/demo")
public class DemoController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	Properties serverConfig;
			
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "test/demo/index";
	}
	
	@RequestMapping(value = "/audio", method = RequestMethod.GET)
	public String mediaStreamRecorder() {
		return "test/demo/audio";
	}
	@RequestMapping(value = "/audio/upload", method = RequestMethod.POST)
	public @ResponseBody SR<String> audioUpload(MultipartFile file) throws IOException {
		SR<String> sr=new SR<String>();
		String audioSavePath = serverConfig.getProperty("file.save.path.audio");
		String name = file.getOriginalFilename();
		File f = new File(audioSavePath, name);
		FileUtils.copyInputStreamToFile(file.getInputStream(),f);
		sr.setStatus("success");
		
		String audioHttpPath = serverConfig.getProperty("http.view.path.audio");
		
		sr.setEntity(audioHttpPath+"/"+name);
		
		return sr;
		
	}
	@RequestMapping(value = "/audioTest", method = RequestMethod.GET)
	public String audioTest() {
		return "test/demo/audioTest";
	}
	
	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public String video() {
		return "test/demo/video";
	}
	@RequestMapping(value = "/video-360", method = RequestMethod.GET)
	public String video3() {
		return "test/demo/video-360";
	}
}
