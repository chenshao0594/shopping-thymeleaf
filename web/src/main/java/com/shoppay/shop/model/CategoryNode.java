package com.shoppay.shop.model;

public class CategoryNode {
	private long id;
	private long pId;
	private String name;
	private boolean isParent;
	private boolean click;
	private boolean open;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isClick() {
		return click;
	}
	public void setClick(boolean click) {
		this.click = click;
	}
	
	
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", pId:" + pId + ", name:'" + name + "', isParent:" + isParent + ", click:"
				+ click + ", open:" + open+"}";
	}
	
	
}
