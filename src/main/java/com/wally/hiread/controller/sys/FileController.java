package com.wally.hiread.controller.sys;

import com.wally.hiread.model.product.*;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.sys.Testing;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.*;
import com.wally.hiread.service.sys.FileService;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.service.sys.TestingService;
import com.wally.hiread.setting.sys.AppCons;
import com.wally.hiread.util.MultipartFileSender;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/sys/file")
public class FileController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	UnitService unitService;
	@Autowired
	ProductService productService;
	@Autowired
	PractiseService practiseService;
	@Autowired
	FileService fileService;
	@Autowired
	LogService logService;
	@Autowired
	UnitAudioService uaService;
	@Autowired
	PractiseOptService practiseOptService;
	@Autowired
	UserReadService userReadService;
	@Autowired
	ReadPractiseService readPractiseService;
	@Autowired
	UserFreeTalkService talkService;
	@Autowired
	TestingService testingService;
	@Autowired
	TokenService tokenService;

	@RequestMapping(value = "/sec", method = RequestMethod.POST)
	public @ResponseBody SR<HiToken> token(){
		SR<HiToken> sr=new SR<HiToken>();
		HiToken token = tokenService.gen();
		sr.setEntity(token);
		sr.setStatus(SR.SUCCESS);
		return sr;

	}

	@RequestMapping(value = "/video", method = RequestMethod.GET)
	public void video(HttpServletResponse response, HttpServletRequest request,String type, String id,String t){
		if(StringUtils.isEmpty(type)||StringUtils.isEmpty(id)){
			return;
		}
		String fileName = null;
		String tableName=null;
		if(AppCons.FILE_TYPE_VIDEO_UNIT.equals(type)){
			String range = request.getHeader("Range");
			if(range==null||range.equals("bytes=0-")){
				if(StringUtils.isEmpty(t)){
					return;
				}
				HiToken hiToken = tokenService.get(t);
				if(hiToken==null){
					return;
				}
				tokenService.remove(t);
			}
		}
		if(AppCons.FILE_TYPE_VIDEO_UNIT.equals(type)||AppCons.FILE_TYPE_VIDEO_UNIT_PREVIEW.equals(type)){
			Unit unit = unitService.load(id,false,false,false,false,false);
			if(unit==null){
				return;
			}
			if(AppCons.FILE_TYPE_VIDEO_UNIT.equals(type)){
				fileName = unit.getVideo();
			}else if(AppCons.FILE_TYPE_VIDEO_UNIT_PREVIEW.equals(type)){
				fileName=unit.getPreviewVideo();
			}
			tableName=Unit.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_VIDEO_PRACTISE_ANALYSIS.equals(type)){
			Practise practise = practiseService.load(id, false);
			if(practise==null){
				return;
			}
			fileName = practise.getAnalysisVideo();
			tableName=Practise.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_VIDEO_TESTING_QUESTIONG_VIDEO.equals(type)){
			Testing testing = testingService.load(id);
			if(testing==null){
				return;
			}
			fileName=testing.getQuestionVideo();
			tableName=Testing.TABLE_NAME;
		}

		if(StringUtils.isEmpty(fileName)){
			return;
		}
		SR<File> fileResult=fileService.getFile(tableName, id, fileName);
		if(fileResult.getStatus().equals(AppCons.SR_FAIL)){
			return;
		}
		File file=fileResult.getEntity();
		try {
			MultipartFileSender.fromPath(file.toPath())
					.with(request)
					.with(response)
					.serveResource();

		} catch(IOException e){
			if(e.getMessage().equals("Broken pipe")){
				log.info("video broken pipe");
			}
		} catch(Exception e) {
			logService.systemError(AppCons.SYSTEM_ERROR_RUNTIME, "video read error", e);
		}
	}
	@RequestMapping(value = "/audio", method = RequestMethod.GET)
	public void audio(HttpServletResponse response, HttpServletRequest request,String type, String id){
		if(StringUtils.isEmpty(type)||StringUtils.isEmpty(id)){
			return;
		}
		String fileName = null;
		String tableName=null;
		if(AppCons.FILE_TYPE_AUDIO_PRACTISE_QUESTION.equals(type)
				||AppCons.FILE_TYPE_AUDIO_PRACTISE_ANALYSIS.equals(type)
				||AppCons.FILE_TYPE_AUDIO_PRACTISE_CARD_EN_SAMPLE_AUDIO.equals(type)){
			Practise practise = practiseService.load(id, false);
			if(practise==null){
				return;
			}
			if(AppCons.FILE_TYPE_AUDIO_PRACTISE_QUESTION.equals(type)){
				fileName = practise.getQuestionAudio();
			}else if(AppCons.FILE_TYPE_AUDIO_PRACTISE_ANALYSIS.equals(type)){
				fileName = practise.getAnalysisAudio();
			}else if(AppCons.FILE_TYPE_AUDIO_PRACTISE_CARD_EN_SAMPLE_AUDIO.equals(type)){
				fileName = practise.getCardEnSampleAudio();
			}
			tableName=Practise.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_AUDIO_UNIT_AUDIO.equals(type)){
			UnitAudio unitAudio = uaService.load(id);
			if(unitAudio==null){
				return;
			}
			fileName = unitAudio.getAudio();
			tableName=UnitAudio.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_AUDIO_UNIT_PREVIEW.equals(type)){
			Unit unit = unitService.load(id,false,false,false,false,false);
			if(unit==null){
				return;
			}
			fileName=unit.getPreviewAudio();
			tableName=Unit.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_AUDIO_READ_PRACTISE.equals(type)){
			ReadPractise rp=readPractiseService.load(id);
			if(rp==null){
				return;
			}
			fileName=rp.getAudio();
			tableName=rp.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_AUDIO_USER_READ.equals(type)){
			UserRead userRead = userReadService.load(id);
			if(userRead==null){
				return;
			}
			fileName=userRead.getAudioFile();
			tableName=userRead.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_AUDIO_TESTING_QUESTIONG_AUDIO.equals(type)){
			Testing testing = testingService.load(id);
			if(testing==null){
				return;
			}
			fileName=testing.getQuestionAudio();
			tableName=Testing.TABLE_NAME;
		}

		if(StringUtils.isEmpty(fileName)){
			return;
		}
		SR<File> fileResult=fileService.getFile(tableName, id, fileName);
		if(fileResult.getStatus().equals(AppCons.SR_FAIL)){
			return;
		}
		File file=fileResult.getEntity();
		try {
			MultipartFileSender.fromPath(file.toPath())
					.with(request)
					.with(response)
					.serveResource();

		} catch(IOException e){
			if(e.getMessage().equals("Broken pipe")){
				log.info("audio broken pipe");
			}
		} catch (Exception e) {
			logService.systemError(AppCons.SYSTEM_ERROR_RUNTIME, "audio read error", e);
		}
	}

	@RequestMapping(value = "/img", method = RequestMethod.GET)
	public void videoImg(HttpServletResponse response, HttpServletRequest request,String type, String id,String name){
		if(StringUtils.isEmpty(type)||StringUtils.isEmpty(id)){
			return;
		}
		String fileName = null;
		String tableName=null;
		if(AppCons.FILE_TYPE_IMG_PRODUCT.equals(type)){
			Product product = productService.load(id,false);
			if(product==null){
				return;
			}
			fileName = product.getProdIcon();
			tableName=Product.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_IMG_PRACTICE_OPT_IMG.equals(type)||AppCons.FILE_TYPE_IMG_PRACTICE_OPT_PAIR1_IMG.equals(type)||AppCons.FILE_TYPE_IMG_PRACTICE_OPT_PAIR2_IMG.equals(type)){
			PractiseOpt practiseOpt = practiseOptService.load(id);
			if(practiseOpt==null){
				return;
			}
			if(AppCons.FILE_TYPE_IMG_PRACTICE_OPT_IMG.equals(type)){
				fileName = practiseOpt.getOptionImg();
			}else if(AppCons.FILE_TYPE_IMG_PRACTICE_OPT_PAIR1_IMG.equals(type)){
				fileName = practiseOpt.getPair1Img();
			}else if(AppCons.FILE_TYPE_IMG_PRACTICE_OPT_PAIR2_IMG.equals(type)){
				fileName = practiseOpt.getPair2Img();
			}

			tableName=PractiseOpt.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_IMG_PRACTICE_QUESTION_IMG.equals(type)){
			Practise practise = practiseService.load(id, false);
			if(practise==null||StringUtils.isEmpty(name)){
				return;
			}
			fileName=name;
			tableName=Practise.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_IMG_UNIT_PREVIEW.equals(type)){
			Unit unit = unitService.load(id,false,false,false,false,false);
			if(unit==null){
				return;
			}
			fileName=unit.getPreviewImage();
			tableName=Unit.TABLE_NAME;
		}else if(AppCons.FILE_TYPE_IMG_TESTING_QUESTIONG_IMG.equals(type)){
			Testing testing = testingService.load(id);
			if(testing==null){
				return;
			}
			fileName=testing.getQuestionImg();
			tableName=Testing.TABLE_NAME;
		}


		if(StringUtils.isEmpty(fileName)){
			return;
		}
		SR<File> fileResult=fileService.getFile(tableName, id, fileName);
		if(fileResult.getStatus().equals(AppCons.SR_FAIL)){
			return;
		}
		File file=fileResult.getEntity();
		try {
			BufferedImage im = ImageIO.read(file);
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			FileInputStream input = new FileInputStream(file);
			Tika tika=new Tika();
			String contentType=tika.detect(input);
			response.setContentType(contentType);
			ImageIO.write(im, formatName, response.getOutputStream());

		} catch(IOException e){
			if(e.getMessage().equals("Broken pipe")){
				log.info("img broken pipe");
			}
		} catch (Exception e) {
			logService.systemError(AppCons.SYSTEM_ERROR_RUNTIME, "image read error", e);
		}
	}

	@RequestMapping(value = "/audio", method = RequestMethod.POST)
	public @ResponseBody  SR<String> audioPost(MultipartFile file, String type, String id){
		SR<String> sr=new SR<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("请登录");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();

		if(StringUtils.isEmpty(type)||StringUtils.isEmpty(id)){
			sr.setMsg("参数不合法");
			return sr;
		}
		String tableName=null;
		if(AppCons.FILE_TYPE_AUDIO_USER_READ.equals(type)){
			UserRead userRead = userReadService.load(id);
			if(userRead==null){
				sr.setMsg("记录不存在");
				return sr;
			}
			if(!userRead.getUserId().equals(userId)){
				sr.setMsg("非法操作");
				return sr;
			}
			tableName=userRead.TABLE_NAME;
			sr=fileService.uploadFile(file,type,tableName,id);
			if(sr.getStatus().equals(SR.SUCCESS)){
				String fileName = sr.getEntity();
				userRead.setAudioFile(fileName);
				userReadService.update(userRead);
			}

		}else if(AppCons.FILE_TYPE_AUDIO_FREE_TALK.equals(type)){
			UserFreeTalk talk = talkService.load(id);
			if(talk==null){
				sr.setMsg("记录不存在");
				return sr;
			}
			if(!talk.getUserId().equals(userId)){
				sr.setMsg("非法操作");
				return sr;
			}
			tableName=talk.TABLE_NAME;
			sr=fileService.uploadFile(file,type,tableName,id);
			if(sr.getStatus().equals(SR.SUCCESS)){
				String fileName = sr.getEntity();
				talk.setAudio(fileName);
				talkService.update(talk);
			}

		}else{
			sr.setMsg("不支持的类型");
			return sr;
		}

		return sr;
	}

}
