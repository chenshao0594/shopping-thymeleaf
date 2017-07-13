package com.smartshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.smartshop.core.attachment.model.AttachmentInfo;
import com.smartshop.exception.FileNotFoundException;
import com.smartshop.exception.UploadFailException;
import com.smartshop.service.AttachmentServerClient;



@Component
@Scope(value="request", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class AttachmentServerClientImpl implements  AttachmentServerClient {

	private static Logger logger = LoggerFactory.getLogger(AttachmentServerClientImpl.class);

	@Autowired
	private HttpServletRequest request;

	private final String folderProducts="/static/images/products";
	private String realPathtoUploads;

	@PostConstruct
	public void init() {
		logger.info("init UploadProductsImagesStrategy bean");
		realPathtoUploads = request.getServletContext().getRealPath(folderProducts);
		if (!new File(realPathtoUploads).exists()) {
			logger.info("create path to products images");
			new File(realPathtoUploads).mkdirs();
		}
	}


	@Override
	public String save(AttachmentInfo attachmentInfo) {
		try {
			File resultFile = buildResultFile(attachmentInfo.getContentType());
			Files.write(resultFile.toPath(), attachmentInfo.getContent(), StandardOpenOption.CREATE);
			return resultFile.getName();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
			throw new UploadFailException();
		}
	}

	@Override
	public void delete(String key) {
		if(StringUtils.isBlank(key)) {
			return;
		}
		File file = new File(realPathtoUploads, key);
		if(file.exists() && file.canWrite())
			file.delete();

	}

	@Override
	public AttachmentInfo get(String key) {
		AttachmentInfo info = null;
		if(StringUtils.isBlank(key)) {
			return info;
		}
		try {
			File file = new File(realPathtoUploads, key);
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
		return new File(realPathtoUploads, name);
	}
}
