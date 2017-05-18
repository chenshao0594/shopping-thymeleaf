package com.smartshop.attachment.common;

public enum AttachmentEnum {
	AVATAR("avatar"), IMAGE("image"), ATTACHMENT("attachment"), THUMBNAIL("thumbnail");
	private String value;

	AttachmentEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
