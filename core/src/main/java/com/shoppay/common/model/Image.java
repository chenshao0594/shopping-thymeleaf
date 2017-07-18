package com.shoppay.common.model;

import java.util.Arrays;

public class Image {

	private String name;

	private byte[] content;
	private Long size;
	private String contentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "Image [name=" + name + ", content=" + Arrays.toString(content) + ", size=" + size + ", contentType="
				+ contentType + "]";
	}

}
