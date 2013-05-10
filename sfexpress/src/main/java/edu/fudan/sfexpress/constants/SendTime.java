package edu.fudan.sfexpress.constants;

public class SendTime {

	public static String day = "2013-05-01";
	public static String hour = "16";
	public static String minute = "00";

	public final static String getTime() {
		return day + " " + hour + ":" + minute + ":00";
	}
}
