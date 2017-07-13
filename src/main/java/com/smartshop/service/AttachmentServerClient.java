package com.smartshop.service;

import com.smartshop.core.attachment.model.AttachmentInfo;

public interface AttachmentServerClient {
	String save(AttachmentInfo fileinfo);
	void delete(String key);
	AttachmentInfo get(String key);
}
