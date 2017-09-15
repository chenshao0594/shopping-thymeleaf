package com.shoppay.core.attachment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.shoppay.common.domain.Attachment;

public final class AttachmentUtils {

	public static byte[] getAttachmentContent(Attachment attachment) {
		byte[] content = null;
		InputStream attachmentStream = null;
		try {
			File file = new File(attachment.getPath());
			attachmentStream = new FileInputStream(file);
			content = IOUtils.toByteArray(attachmentStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(attachmentStream);
		}
		return content;

	}

}
