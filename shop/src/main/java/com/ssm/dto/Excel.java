package com.ssm.dto;

public class Excel {
	
	private int id;
	private String hangye;
	private String zhongduankehu;
	private String zigongsi;
	private String province;
	private String city;
	private String address;
	private String work;
	private String productivity;
	private String income;
	private String output;
	private String x;
	private String y;
	private String description;
	
	private String distance;
	public Excel(){}
	
	public Excel(String x,String y,String address){
		this.x =x;
		this.y=y;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHangye() {
		return hangye;
	}
	public void setHangye(String hangye) {
		this.hangye = hangye;
	}
	public String getZhongduankehu() {
		return zhongduankehu;
	}
	public void setZhongduankehu(String zhongduankehu) {
		this.zhongduankehu = zhongduankehu;
	}
	public String getZigongsi() {
		return zigongsi;
	}
	public void setZigongsi(String zigongsi) {
		this.zigongsi = zigongsi;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getProductivity() {
		return productivity;
	}
	public void setProductivity(String productivity) {
		this.productivity = productivity;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Excel [hangye=" + hangye + ", zhongduankehu=" + zhongduankehu + ", zigongsi=" + zigongsi + ", province="
				+ province + ", city=" + city + ", address=" + address + ", work=" + work + ", productivity="
				+ productivity + ", income=" + income + ", output=" + output + ", x=" + x + ", y=" + y
				+ ", description=" + description + ", distance=" + distance + "]";
	}

}
