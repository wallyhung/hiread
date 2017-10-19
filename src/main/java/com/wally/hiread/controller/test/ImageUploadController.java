package com.wally.hiread.controller.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ImageUploadController {
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public @ResponseBody Object phoneTest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Part part = request.getPart("myImage");
			String name = part.getName();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/imageUpload2", method = RequestMethod.POST)
	public @ResponseBody Object phoneTest2(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, org.apache.commons.fileupload.FileUploadException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			int maxMemSize = (2 * 1024 * 1024);
			DiskFileItemFactory factory = new DiskFileItemFactory();
		    factory.setSizeThreshold(maxMemSize);
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    upload.setSizeMax(1000000L);
		    try {
		        List items = upload.parseRequest(request);
		        int size = items.size();
		        Iterator iterator = items.iterator();
		        while (iterator.hasNext()) {
		            FileItem item = (FileItem) iterator.next();
		            if (!item.isFormField()) {
		                String fileName = item.getName();

		            }
		        }
		    } catch (Exception e) {
		    	return null;
		    }
		}
		return null;
		
	}
	
	@RequestMapping(value = "/toImageUpload", method = RequestMethod.GET)
	public String toPhoneTest(HttpServletRequest request, HttpServletResponse response) {
		return "test/imageUpload";
	}
}
