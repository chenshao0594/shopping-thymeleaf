package com.smart.shopping.attachment.common;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AttachmentResponse {
	private boolean append = true;
	private List<String> initialPreview = new LinkedList<String>();
	private Set<PreviewConfig> previewConfig = new HashSet<PreviewConfig>();

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

	public Set<PreviewConfig> getPreviewConfig() {
		return previewConfig;
	}

	public void setPreviewConfig(Set<PreviewConfig> previewConfig) {
		this.previewConfig = previewConfig;
	}

}
