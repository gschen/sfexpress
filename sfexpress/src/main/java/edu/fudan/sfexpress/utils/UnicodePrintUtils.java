package edu.fudan.sfexpress.utils;

import org.apache.commons.lang3.StringEscapeUtils;

public class UnicodePrintUtils {

	public static void main(String[] args) {

		String str = "\u516B\u5ED3\u8857\u9053";
		System.out.println(StringEscapeUtils.unescapeJava(str));
	}

}
