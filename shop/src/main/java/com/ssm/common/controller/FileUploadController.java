package com.ssm.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileUploadController {

	// 上传到服务器位置
	// E:\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\shop\fileupload
	
	@RequestMapping("/upload")
	public String fileUpload(@RequestParam("file") CommonsMultipartFile files[],HttpServletRequest req) throws IOException{
		//获取文件名
		//file.getOriginalFilename();
		//获取上传文件的路径
		String path = req.getRealPath("/fileupload");
		InputStream is = null;
		OutputStream os = null;
		for(CommonsMultipartFile file :files){
			is = file.getInputStream();
			//os = new FileOutputStream(new File(path,file.getOriginalFilename())); 
			os = new FileOutputStream(new File(path,uuidName(file.getOriginalFilename())));
			int len = 0;
			byte[] byteArr = new byte[1024];
			while((len=is.read(byteArr)) != -1){
				os.write(byteArr, 0, len);
			}
		}
		os.close();
		is.close();
		//return "/fileupload.jsp";
		return "redirect:fileupload.jsp";
	}
	
	//生成uuid文件名
	private String uuidName(String fileName){
		UUID uuid = UUID.randomUUID();
		return uuid.toString() +"_"+fileName;
	}
	
}
