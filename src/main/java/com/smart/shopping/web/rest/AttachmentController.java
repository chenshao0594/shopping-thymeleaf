package com.smart.shopping.web.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.smart.shopping.domain.Attachment;
import com.smart.shopping.service.AttachmentService;

@Controller
@RequestMapping("/attachments")
public class AttachmentController {

	private final Logger log = LoggerFactory.getLogger(AttachmentController.class);

	private static final String ENTITY_NAME = "attachment";

	private final AttachmentService attachmentService;

	public AttachmentController(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@PostMapping(value = "")
	public void createImage(@Valid Attachment attachment, BindingResult result, SessionStatus status, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		this.attachmentService.save(attachment);

		System.out.println(attachment.getContent());
		response.sendRedirect(request.getRequestURI());
	}

	@GetMapping()
	public String images(HttpServletResponse response) throws IOException {
		// MultipartFile image = reqModel.getImage();
		// if (null == image) {
		// response.sendRedirect(request.getRequestURI() + "s");
		// }
		// if (null == item.getMainImageId()) {
		// item.setMainImageId(newImage.getId());
		// this.repository.saveAndFlush(item);
		// }
		// response.sendRedirect(request.getRequestURI());
		return "attachment/list";
	}

	@GetMapping(value = "/{id}")
	public void findOne(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
		response.sendRedirect("");
	}

	@GetMapping(value = "/{id}/attachments")
	public void queryImages(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
		// MultipartFile image = reqModel.getImage();
		// if (null == image) {
		// response.sendRedirect(request.getRequestURI() + "s");
		// }
		// if (null == item.getMainImageId()) {
		// item.setMainImageId(newImage.getId());
		// this.repository.saveAndFlush(item);
		// }
		// response.sendRedirect(request.getRequestURI());
	}

}
