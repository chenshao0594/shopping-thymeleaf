package com.smart.shopping.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.attachment.common.AttachmentResponse;
import com.smart.shopping.attachment.common.PreviewConfig;
import com.smart.shopping.domain.Attachment;
import com.smart.shopping.service.AttachmentService;

@Controller
@RequestMapping("/attachments")
public class AttachmentController {

	private final Logger log = LoggerFactory.getLogger(AttachmentController.class);

	private static final String ENTITY_NAME = "attachment";

	private static final String InitialPreview = "<img src='/attachments/attachment_id' class='file-preview-image kv-preview-data rotate-1'>";
	private final AttachmentService attachmentService;

	public AttachmentController(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Timed
	@PostMapping()
	public @ResponseBody ResponseEntity<AttachmentResponse> createImage(@RequestParam("file") MultipartFile file,
			String boName, Long boId, RedirectAttributes redirectAttributes, HttpServletResponse response)
			throws IOException, URISyntaxException {
		Attachment attachment = new Attachment(boName, boId);
		attachment.setContent(file.getBytes());
		attachment.setContentType(file.getContentType());
		attachment.setSize(file.getSize());
		attachment.setName(file.getOriginalFilename());
		this.attachmentService.save(attachment);

		PreviewConfig previewConfig = new PreviewConfig(attachment.getName());
		previewConfig.setKey(Long.toString(attachment.getId()));
		previewConfig.addSize(attachment.getSize());
		previewConfig.addUrl("/attachments/" + attachment.getId());
		previewConfig.addWidth("180px");
		AttachmentResponse body = new AttachmentResponse();
		body.getPreviewConfig().add(previewConfig);
		switch (attachment.getAttachmentType()) {
		case AVATOR:
			body.getInitialPreview().add(InitialPreview.replace("attachment_id", Long.toString(attachment.getId())));
			break;
		default:
			body.getInitialPreview().add("/attachments/" + attachment.getId());
		}

		return ResponseEntity.created(new URI("/attachments/" + attachment.getId())).body(body);
		/*
		 * this.attachmentService.save(attachment);
		 *
		 */
	}

	@Timed
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Void> deleteImage(@PathVariable("id") Long id) throws IOException {
		System.out.println("delete attachment by " + id);
		this.attachmentService.delete(id);
		return ResponseEntity.ok().build();
	}

	@Timed
	@GetMapping()
	public String images(Model model, Pageable pageable) throws IOException {
		Page<Attachment> page = this.attachmentService.findAll(null);
		model.addAttribute("attachments", page.getContent());
		List<PreviewConfig> inititalPreviewConfigs = new LinkedList<PreviewConfig>();
		for (Attachment attachment : page.getContent()) {
			PreviewConfig config = new PreviewConfig(attachment.getName());
			config.addKey(Long.toString(attachment.getId()));
			config.addSize(attachment.getSize());
			config.addUrl("/attachments/" + attachment.getId());
			inititalPreviewConfigs.add(config);
		}
		model.addAttribute("priviewConfig", inititalPreviewConfigs);
		return "attachment/list";
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> findOne(@PathVariable("id") Long id) throws IOException {
		Attachment attachment = this.attachmentService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(attachment.getContent(), headers, HttpStatus.OK);
	}

}
