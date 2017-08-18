package com.ssm.common.dto;

import java.io.Serializable;

public class SysMenus implements Serializable{

	private static final long serialVersionUID = -4731857314738517854L;
	private int id;
	private String name;
	private String url;
	private int pId;
	public SysMenus(){}
	
	public SysMenus(String name, String url, int pId) {
		this.name = name;
		this.url = url;
		this.pId = pId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}

	@Override
	public String toString() {
		return "SysMenus [id=" + id + ", name=" + name + ", url=" + url + ", pId=" + pId + "]";
	}
	
	
}
