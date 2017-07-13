package com.smartshop.web.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import com.smartshop.attachment.common.AttachmentEnum;
import com.smartshop.attachment.common.AttachmentResponse;
import com.smartshop.attachment.common.PreviewConfig;
import com.smartshop.constants.AppConstants;
import com.smartshop.core.attachment.model.AttachmentInfo;
import com.smartshop.core.attachment.utils.AttachmentUtils;
import com.smartshop.domain.Attachment;
import com.smartshop.service.AttachmentServerClient;
import com.smartshop.service.AttachmentService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/attachments")
public class AttachmentController {

	private final Logger log = LoggerFactory.getLogger(AttachmentController.class);

	private static final String ENTITY_NAME = "attachment";

	private static final String InitialPreview = "<img src='" + AppConstants.ADMIN_PREFIX
			+ "/attachments/attachment_id' class='file-preview-image kv-preview-data rotate-1'>";
	private final AttachmentService attachmentService;
	
	@Inject
	private AttachmentServerClient attachmentClient;

	public AttachmentController(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Timed
	@PostMapping()
	public @ResponseBody ResponseEntity<AttachmentResponse> createImage(@RequestParam("file") MultipartFile file,
			String boName, Long boId, String attachmentType, RedirectAttributes redirectAttributes,
			HttpServletResponse response) throws IOException, URISyntaxException {

		Attachment attachment = new Attachment(boName, boId);
		attachment.setContentType(file.getContentType());
		attachment.setSize(file.getSize());
		attachment.setName(file.getOriginalFilename());
		attachment.setAttachmentType(AttachmentEnum.valueOf(attachmentType));
		String filePath = this.attachmentClient.save( new AttachmentInfo(file.getBytes(), file.getContentType(), file.getOriginalFilename()));
		attachment.setPath(filePath);
		this.attachmentService.save(attachment);
		PreviewConfig previewConfig = new PreviewConfig(attachment.getName());
		previewConfig.setKey(Long.toString(attachment.getId()));
		previewConfig.addSize(attachment.getSize());
		previewConfig.addUrl(AppConstants.ADMIN_PREFIX + "/attachments/" + attachment.getId());
		previewConfig.addWidth("180px");
		AttachmentResponse body = new AttachmentResponse();
		body.getInitialPreviewConfig().add(previewConfig);
		switch (attachment.getAttachmentType()) {
		case AVATAR:
			this.createThumbnail(attachment, file.getBytes());
			break;
		case IMAGE:
			this.createThumbnail(attachment, file.getBytes());
			break;
		default:

		}
		body.getInitialPreview().add(AppConstants.ADMIN_PREFIX + "/attachments/" + attachment.getId());
		return ResponseEntity.created(new URI(AppConstants.ADMIN_PREFIX + "/attachments/" + attachment.getId()))
				.body(body);
	}

	private void createThumbnail(Attachment attachment, byte[] content) {
		List<Attachment> list = this.attachmentService.findAllByBOInfo(attachment.getBoName(), attachment.getBoId(),
				AttachmentEnum.THUMBNAIL);
		if (!CollectionUtils.isEmpty(list)) {
			return;
		}
		Attachment thumbnailAttachment = new Attachment();
		BeanUtils.copyProperties(attachment, thumbnailAttachment);
		thumbnailAttachment.setId(null);
		thumbnailAttachment.setAttachmentType(AttachmentEnum.THUMBNAIL);
		ByteArrayInputStream in = null;
		ByteArrayOutputStream baos = null;
		try {
			in = new ByteArrayInputStream(content);
			BufferedImage originalImage = ImageIO.read(in);
			BufferedImage thumbnail = Thumbnails.of(originalImage).size(200, 200).asBufferedImage();
			baos = new ByteArrayOutputStream();
			String[] typeArr = attachment.getContentType().split("/");
			ImageIO.write(originalImage, typeArr[1], baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			String thumbnailPath =this.attachmentClient.save( new AttachmentInfo(imageInByte, thumbnailAttachment.getContentType(),
					thumbnailAttachment.getName()));
			thumbnailAttachment.setPath(thumbnailPath);
			thumbnailAttachment.setSize((long) imageInByte.length);
			this.attachmentService.save(thumbnailAttachment);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeWhileHandlingException(in);
			IOUtils.closeWhileHandlingException(baos);
		}

	}

	@Timed
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Map> deleteImage(@PathVariable("id") Long id) throws IOException {
		this.attachmentService.delete(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleteThumb", "file delete");
		return ResponseEntity.ok().body(map);
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
			config.addUrl(AppConstants.ADMIN_PREFIX + "/attachments/" + attachment.getId());
			inititalPreviewConfigs.add(config);
		}
		model.addAttribute("priviewConfig", inititalPreviewConfigs);
		return "attachment/list";
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<byte[]> findOne(@PathVariable("id") Long id, HttpServletResponse response)
			throws IOException {
		Attachment attachment = this.attachmentService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(attachment.getContentType()));
		byte[] attachmentContent = AttachmentUtils.getAttachmentContent(attachment);
		return new ResponseEntity<byte[]>(attachmentContent, headers, HttpStatus.OK);
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
