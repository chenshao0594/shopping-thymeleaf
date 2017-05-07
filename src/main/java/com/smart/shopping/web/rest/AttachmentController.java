package com.smart.shopping.web.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.codahale.metrics.annotation.Timed;
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

	@Timed
	@PostMapping()
	public void createImage(@Valid Attachment attachment, BindingResult result, SessionStatus status, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.attachmentService.save(attachment);
		response.sendRedirect(request.getRequestURI());
	}

	@Timed
	@GetMapping()
	public String images(Model model, Pageable pageable) throws IOException {
		Page<Attachment> page = this.attachmentService.findAll(null);
		model.addAttribute("page", page);
		return "attachment/list";
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> findOne(@PathVariable("id") Long id) throws IOException {
		Attachment attachment = this.attachmentService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		// headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// headers.setContentDispositionFormData("attachment",
		// attachment.getName());
		return new ResponseEntity<byte[]>(attachment.getContent(), headers, HttpStatus.OK);

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
