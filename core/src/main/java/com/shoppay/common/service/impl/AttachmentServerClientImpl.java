package com.shoppay.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.shoppay.common.exception.FileNotFoundException;
import com.shoppay.common.exception.UploadFailException;
import com.shoppay.common.service.AttachmentServerClient;
import com.shoppay.core.attachment.model.AttachmentInfo;



@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class AttachmentServerClientImpl implements  AttachmentServerClient {

	private static Logger logger = LoggerFactory.getLogger(AttachmentServerClientImpl.class);

	private static String realPathToUpload=System.getProperty("user.home")+"/application-attachments";

	@PostConstruct
	public void init() {
		logger.info("init UploadProductsImagesStrategy bean");
		if (!new File(realPathToUpload).exists()) {
			logger.info("create path to products images");
			new File(realPathToUpload).mkdirs();
			System.out.println("realPathToUpload is " +realPathToUpload);
		}
	}


	@Override
	public String save(AttachmentInfo attachmentInfo) {
		try {
			File resultFile = buildResultFile(attachmentInfo.getContentType());
			Files.write(resultFile.toPath(), attachmentInfo.getContent(), StandardOpenOption.CREATE);
			return resultFile.getAbsolutePath();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
			throw new UploadFailException();
		}
	}

	@Override
	public void delete(String path) {
		if(StringUtils.isBlank(path)) {
			return;
		}
		File file = new File(path);
		if(file.exists() && file.canWrite()) {
			file.delete();
		}
	}

	@Override
	public AttachmentInfo get(String key) {
		AttachmentInfo info = null;
		if(StringUtils.isBlank(key)) {
			return info;
		}
		try {
			File file = new File(realPathToUpload, key);
			if(file.exists() && file.canRead()){
				String contentType = Files.probeContentType(file.toPath());
				byte[] content = Files.readAllBytes(file.toPath());
				info = new AttachmentInfo(file.length(), contentType, content);
			} else {
				throw new FileNotFoundException();
			}
		} catch (IOException ex) {
			logger.error(ex.getMessage());
			throw new UploadFailException();
		}
		return info;
	}

	private File buildResultFile(String contentType){
		String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(12), contentType.replace("image/", ""));
		return new File(realPathToUpload, name);
	}
}
