package edu.fudan.sfexpress.pojo;

public class ServiceTime {

	private Address srcAddr;
	private Address dstAddr;

	private AddTimeInfo addTimeInfo;

	public AddTimeInfo getAddTimeInfo() {
		return addTimeInfo;
	}

	public void setAddTimeInfo(AddTimeInfo addTimeInfo) {
		this.addTimeInfo = addTimeInfo;
	}

	private double t1_time = -1;

	public double getT1_time() {
		return t1_time;
	}

	public void setT1_time(double t1_time) {
		this.t1_time = t1_time;
	}

	public double getT4_time() {
		return t4_time;
	}

	public void setT4_time(double t4_time) {
		this.t4_time = t4_time;
	}

	public double getT6_time() {
		return t6_time;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(srcAddr.toString());
		sb.append("\n					 " + dstAddr.toString());
		sb.append("\n					 	t1_time: " + t1_time);
		sb.append("\n					 	t4_time: " + t4_time);
		sb.append("\n					 	sp4_time: " + sp4_time);
		sb.append("\n					 	t801_time: " + t801_time);
		sb.append("\n					 	sp5_time: " + sp5_time);
		sb.append("\n					 	t6_time: " + t6_time);
		sb.append("\n					 	add_time: " + addTimeInfo.getAddTime());
		sb.append("\n					 	add_addr: " + addTimeInfo.getAddAddr());
		return sb.toString();
	}

	public void setT6_time(double t6_time) {
		this.t6_time = t6_time;
	}

	public double getSp4_time() {
		return sp4_time;
	}

	public void setSp4_time(double sp4_time) {
		this.sp4_time = sp4_time;
	}

	public double getSp5_time() {
		return sp5_time;
	}

	public void setSp5_time(double sp5_time) {
		this.sp5_time = sp5_time;
	}

	public double getT801_time() {
		return t801_time;
	}

	public void setT801_time(double t801_time) {
		this.t801_time = t801_time;
	}

	private double t4_time = -1;
	private double t6_time = -1;
	private double sp4_time = -1;
	private double sp5_time = -1;
	private double t801_time = -1;

	public Address getSrcAddr() {
		return srcAddr;
	}

	public void setSrcAddr(Address srcAddr) {
		this.srcAddr = srcAddr;
	}

	public Address getDstAddr() {
		return dstAddr;
	}

	public void setDstAddr(Address dstAddr) {
		this.dstAddr = dstAddr;
	}

}
