package com.wally.hiread.service.sys;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.setting.sys.AppCons;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Properties;

@Service
public class FileService {
	@Autowired
	Properties serverConfig;
	@Autowired
	LogService log;
	private String resourceContext;//joget file read context
	private String resourceApp;//joget file read app
	private String resourceSave;//joget standard file save path
	private int imageMaxSizeKB; 
	private long imageMaxSize;
	private int attachMaxSizeKB;
	private long attachMaxSize;
	@PostConstruct
	public void initProcess(){
		try{
			resourceSave=serverConfig.getProperty("joget.resource.save");
			resourceApp=serverConfig.getProperty("joget.resource.app");
			resourceContext=serverConfig.getProperty("joget.resource.request");
			String imageMaxSizeKBConfig=serverConfig.getProperty("joget.resource.image.maxsize.kb");
			imageMaxSizeKB=Integer.parseInt(imageMaxSizeKBConfig);
			imageMaxSize=imageMaxSizeKB*1024;
			String attachMaxSizeKBConfig=serverConfig.getProperty("joget.resource.attach.maxsize.kb");
			attachMaxSizeKB=Integer.parseInt(attachMaxSizeKBConfig);
			attachMaxSize=attachMaxSizeKB*1024;
		}catch(Exception ex){
			log.systemError(AppCons.SYSTEM_ERROR_SETTING, "server config error", ex);
		}
	}
	
	public String getFileUrl(String filetype,String recordId,String fileName){
		String formId="";
		if(filetype.equals(AppCons.FILE_TYPE_BUSINESS_LICENSE)
				||filetype.equals(AppCons.FILE_TYPE_TAX_LICENSE)
				||filetype.equals(AppCons.FILE_TYPE_ORG_LICENSE)){
			formId="user_extra";
		}
		String url=resourceContext+"/"+"web/client/app/"+resourceApp+"/form/download/"+formId+"/"+recordId+"/"+fileName+".";
		return url;
	}
	
	public String getFileName(MultipartFile multipartFile,String filetype){
		String fileName=null;
		File originalFile=new File(multipartFile.getOriginalFilename());
		String originalFileName=originalFile.getName();
		String suffix=originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
		if(filetype.equals(AppCons.FILE_TYPE_BUSINESS_LICENSE)){
			fileName=AppCons.FILE_TYPE_BUSINESS_LICENSE+suffix;
		}else if(filetype.equals(AppCons.FILE_TYPE_TAX_LICENSE)){
			fileName=AppCons.FILE_TYPE_TAX_LICENSE+suffix;
		}else if(filetype.equals(AppCons.FILE_TYPE_ORG_LICENSE)){
			fileName=AppCons.FILE_TYPE_ORG_LICENSE+suffix;
		}else{
			fileName=originalFileName;
		}
		return fileName;
	}
	public SR validateFile(MultipartFile multipartFile,String filetype){
		SR sr=new SR();
		sr.setStatus(AppCons.SR_FAIL);
		sr.setMsg("验证失败");
		try {
			Tika tika=new Tika();
			File originalFile=new File(multipartFile.getOriginalFilename());
			String oriName=originalFile.getName();
			String contentType=tika.detect(multipartFile.getInputStream(),oriName);
			long size=multipartFile.getSize();

			if(filetype.equals(AppCons.FILE_TYPE_AUDIO_USER_READ)||filetype.equals(AppCons.FILE_TYPE_AUDIO_FREE_TALK)){
				if(!"audio/wav".equals(contentType)
						&&!"audio/x-wav".equals(contentType)
						&&!"audio/wave".equals(contentType)
						&&!"audio/x-wave".equals(contentType)
						&&!"audio/vnd.wave".equals(contentType)
						&&!"audio/mp3".equals(contentType)
						&&!"audio/mpeg".equals(contentType)
						&&!"application/x-matroska".equals(contentType)){
					sr.setMsg("音频格式不正确");
					return sr;
				}
				if(size>10*1024*1024){
					sr.setMsg("音频过大");
					return sr;
				}
			}else{
				sr.setMsg("不支持的类型");
				return sr;
			}
			sr.setStatus(AppCons.SR_SUCCESS);

		} catch (Exception e) {
			log.systemError(AppCons.SYSTEM_ERROR_RUNTIME, "validate file exception", e);
			return sr;
		}
		return sr;
		
	}
	
	public SR<File> getFile(String tableName,String id,String fileName){
		SR<File> sr=new SR<File>();
		sr.setStatus(AppCons.SR_FAIL);
		sr.setMsg("文件获取失败");
		File file=null;
		try{
			String fileSavePath = this.resourceSave + File.separator + tableName + File.separator + id;
			file = new File(fileSavePath, fileName);
		}catch(Exception ex){
			log.systemError(AppCons.SYSTEM_ERROR_RUNTIME, ex.getMessage(), ex);
			return sr;
		}
		sr.setStatus(AppCons.SR_SUCCESS);
		sr.setEntity(file);
		return sr;
	}


	public SR<String> uploadFile(MultipartFile multipartFile,String filetype,String tableName,String id){
		SR<String> sr=new SR<String>();
		sr.setStatus(AppCons.SR_FAIL);
		sr.setMsg("上传失败");
		try{
			SR validateFileResult = this.validateFile(multipartFile, filetype);
			if(validateFileResult.getStatus().equals(AppCons.SR_FAIL)){
				sr=validateFileResult;
				return sr;
			}
			String fileSavePath = this.resourceSave + File.separator + tableName + File.separator + id;
			String fileName=this.getFileName(multipartFile, filetype);
			File file = new File(fileSavePath, fileName);
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
			sr.setEntity(fileName);
		}catch(Exception ex){
			log.systemError(AppCons.SYSTEM_ERROR_RUNTIME, ex.getMessage(), ex);
			return sr;
		}
		sr.setStatus(AppCons.SR_SUCCESS);
		return sr;
	}
	
}
