package com.smartshop.attachment.common;

//fileinput preview config
public class PreviewConfig {

	private String caption;
	private Long size;
	// css width
	private String width;

	private String url;
	private String key;

	public PreviewConfig(String caption) {
		this.caption = caption;
	}

	public PreviewConfig addSize(Long size) {
		this.size = size;
		return this;
	}

	public PreviewConfig addWidth(String width) {
		this.width = width;
		return this;
	}

	public PreviewConfig addUrl(String url) {
		this.url = url;
		return this;
	}

	public PreviewConfig addKey(String key) {
		this.key = key;
		return this;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/*
	 * @Override public String toString() { return "PreviewConfig [caption=" +
	 * caption + ", size=" + size + ", width=" + width + ", url=" + url +
	 * ", key=" + key + "]"; }
	 */
}
