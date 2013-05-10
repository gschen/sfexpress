package edu.fudan.sfexpress.pojo;

//快递加时信息
public class AddTimeInfo {
	// 加时时间，单位为天
	private double addTime = -1.0;
	// 加时区域
	private String addAddr = "-1";

	public AddTimeInfo(double addTime, String addAddr) {

		this.addTime = addTime;
		this.addAddr = addAddr;
	}

	public AddTimeInfo() {

	}

	public double getAddTime() {
		return addTime;
	}

	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	public String getAddAddr() {
		return addAddr;
	}

	public void setAddAddr(String addAddr) {
		this.addAddr = addAddr;
	}

}
