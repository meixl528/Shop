package com.ssm.common.service;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface FtpService{
	
	Map<String,String> upload(String folder, CommonsMultipartFile file) throws Exception;
	
	boolean delete(String folder, String fileName) throws Exception;

	void download(String fileName, String newName) throws Exception;

}
