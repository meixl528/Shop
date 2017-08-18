package com.ssm.common.dto;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 3323649742604434578L;
	private int id;
	private String name;
	private String pass;
	private String telephone;
	private String address;
	
	public User(){}
	
	public User(String name,String pass){
		this.name = name;
		this.pass = pass;
	}

	public User(String name, String pass, String telephone, String address) {
		this.name = name;
		this.pass = pass;
		this.telephone = telephone;
		this.address = address;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [\"id\"="+ id + ",\"name\"=\"" + name + "\", \"pass\"=\"" + pass + "\", \"telephone\"=\"" + telephone + "\", \"address\"= \""
				+ address + "\"]";
	}
	
	public String toJson(){
		return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"pass\":\"" + pass + "\", \"telephone\":\"" + telephone + "\", \"address\":\""+ address + "\"}";
	}
	
}
