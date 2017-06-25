package com.smartshop.shop.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartshop.domain.Attachment;
import com.smartshop.service.AttachmentService;
import com.smartshop.utils.AttachmentUtils;

@Controller("ShopImageController")
@RequestMapping("/images")
public class ShoppingImageController {

	@Inject
	private AttachmentService attachmentService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> findOne(@PathVariable("id") Long id, HttpServletResponse response)
			throws IOException {
		Attachment attachment = this.attachmentService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(attachment.getContentType()));
		return new ResponseEntity<byte[]>(AttachmentUtils.getAttachmentContent(attachment), headers, HttpStatus.OK);
	}

	@GetMapping(value = "/{roName}/{id}/thumbnail")
	public ResponseEntity<byte[]> findThumbnail(@PathVariable("roName") String roName, @PathVariable("id") Long id)
			throws IOException {
		Attachment attachment = this.attachmentService.findThumbnail(roName, id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		if (attachment == null) {
			return new ResponseEntity<byte[]>(null, headers, HttpStatus.OK);
		}
		return new ResponseEntity<byte[]>(AttachmentUtils.getAttachmentContent(attachment), headers, HttpStatus.OK);
	}

}
