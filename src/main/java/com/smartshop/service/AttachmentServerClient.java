package com.smartshop.service;

import com.smartshop.model.AttachmentInfo;

public interface AttachmentServerClient {
	String save(AttachmentInfo fileinfo);
	void delete(String key);
	AttachmentInfo get(String key);
}
