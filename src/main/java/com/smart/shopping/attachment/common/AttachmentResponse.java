package com.smart.shopping.attachment.common;

import java.util.List;

public class AttachmentResponse {
	private boolean append = true;
	private List<String> initialPreview;
	private List<PreviewConfig> previewConfig;

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public List<String> getInitialPreview() {
		return initialPreview;
	}

	public void setInitialPreview(List<String> initialPreview) {
		this.initialPreview = initialPreview;
	}

	public List<PreviewConfig> getPreviewConfig() {
		return previewConfig;
	}

	public void setPreviewConfig(List<PreviewConfig> previewConfig) {
		this.previewConfig = previewConfig;
	}

}
