package com.kapphk.web.bean.system;

import java.util.List;

public class BeanStandardResource {
	
	private Long id;
	private String text;
	private String state;
	private String url;
	private List<BeanStandardResource> children;
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<BeanStandardResource> getChildren() {
		return children;
	}
	public void setChildren(List<BeanStandardResource> children) {
		this.children = children;
	}

}
