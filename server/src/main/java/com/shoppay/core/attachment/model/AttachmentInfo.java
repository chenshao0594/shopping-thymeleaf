package com.shoppay.core.attachment.model;

import java.io.Serializable;

public class AttachmentInfo implements Serializable {
    
    private byte[] content;
    private String contentType;
    private String originalName;
    
    private long size;

    public AttachmentInfo(byte[] content, String contentType, String originalName) {
        this.content = content;
        this.contentType = contentType;
        this.originalName = originalName;
    }
    
    
    public AttachmentInfo(long size, String contentType, byte[] content) {
        this.size = size;
        this.contentType = contentType;
        this.content = content;
    }
    
    
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

    

}
