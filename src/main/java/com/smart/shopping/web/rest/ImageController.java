/*
 * The MIT License
 *
 * Copyright 2013 jdmr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.smart.shopping.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smart.shopping.service.AttachmentService;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/test")
public class ImageController {

	private static final Logger log = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private AttachmentService attachmentService;

	private String fileUploadDirectory;

	@RequestMapping
	public String index() {
		log.debug("ImageController home");
		return "image/index";
	}

	@GetMapping()
	public @ResponseBody Map list() {
		// log.debug("uploadGet called");
		// List<Image> list = this.attachmentService.list();
		// for (Image image : list) {
		// image.setUrl("/picture/" + image.getId());
		// image.setThumbnailUrl("/thumbnail/" + image.getId());
		// image.setDeleteUrl("/delete/" + image.getId());
		// image.setDeleteType("DELETE");
		// }
		Map<String, Object> files = new HashMap<>();
		// files.put("files", list);
		log.debug("Returning: {}", files);
		return files;
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, String boName, Long BoId,
			RedirectAttributes redirectAttributes) {

		System.out.println("boName : " + boName);
		System.out.println(file.getName());
		System.out.println(file.getSize());

		System.out.println(">>>>>>>>>>>   upload  work !!" + file);
		log.debug("uploadPost called");
		// Iterator<String> itr = request.getFileNames();
		// MultipartFile mpf;
		// List<Attachment> list = new LinkedList<>();
		//
		// while (itr.hasNext()) {
		// mpf = request.getFile(itr.next());
		// log.debug("Uploading {}", mpf.getOriginalFilename());
		//
		// String newFilenameBase = UUID.randomUUID().toString();
		// String originalFileExtension = mpf.getOriginalFilename()
		// .substring(mpf.getOriginalFilename().lastIndexOf("."));
		// String newFilename = newFilenameBase + originalFileExtension;
		// String storageDirectory = fileUploadDirectory;
		// String contentType = mpf.getContentType();
		//
		// File newFile = new File(storageDirectory + "/" + newFilename);
		// try {
		// mpf.transferTo(newFile);
		//
		// BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
		// String thumbnailFilename = newFilenameBase + "-thumbnail.png";
		// File thumbnailFile = new File(storageDirectory + "/" +
		// thumbnailFilename);
		// ImageIO.write(thumbnail, "png", thumbnailFile);
		//
		// Image image = new Image();
		// image.setName(mpf.getOriginalFilename());
		// image.setThumbnailFilename(thumbnailFilename);
		// image.setNewFilename(newFilename);
		// image.setContentType(contentType);
		// image.setSize(mpf.getSize());
		// image.setThumbnailSize(thumbnailFile.length());
		// image = imageDao.create(image);
		//
		// image.setUrl("/picture/" + image.getId());
		// image.setThumbnailUrl("/thumbnail/" + image.getId());
		// image.setDeleteUrl("/delete/" + image.getId());
		// image.setDeleteType("DELETE");
		//
		// list.add(image);
		//
		// } catch (IOException e) {
		// log.error("Could not upload file " + mpf.getOriginalFilename(), e);
		// }
		//
		// }
		//
		// Map<String, Object> files = new HashMap<>();
		// files.put("files", list);
		// return files;
		return "/";
	}

	@GetMapping(value = "/{id}")
	public void picture(HttpServletResponse response, @PathVariable Long id) {
		// Attachment image = imageDao.get(id);
		// File imageFile = new File(fileUploadDirectory + "/" +
		// image.getNewFilename());
		// response.setContentType(image.getContentType());
		// response.setContentLength(image.getSize().intValue());
		// try {
		// InputStream is = new FileInputStream(imageFile);
		// IOUtils.copy(is, response.getOutputStream());
		// } catch (IOException e) {
		// log.error("Could not show picture " + id, e);
		// }
	}

	@RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
	public void thumbnail(HttpServletResponse response, @PathVariable Long id) {
		// Image image = imageDao.get(id);
		// File imageFile = new File(fileUploadDirectory + "/" +
		// image.getThumbnailFilename());
		// response.setContentType(image.getContentType());
		// response.setContentLength(image.getThumbnailSize().intValue());
		// try {
		// InputStream is = new FileInputStream(imageFile);
		// IOUtils.copy(is, response.getOutputStream());
		// } catch (IOException e) {
		// log.error("Could not show thumbnail " + id, e);
		// }
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody List delete(@PathVariable Long id) {

		return new ArrayList<>();
		// Image image = imageDao.get(id);
		// File imageFile = new File(fileUploadDirectory + "/" +
		// image.getNewFilename());
		// imageFile.delete();
		// File thumbnailFile = new File(fileUploadDirectory + "/" +
		// image.getThumbnailFilename());
		// thumbnailFile.delete();
		// imageDao.delete(image);
		// List<Map<String, Object>> results = new ArrayList<>();
		// Map<String, Object> success = new HashMap<>();
		// success.put("success", true);
		// results.add(success);
		// return results;
	}
}
