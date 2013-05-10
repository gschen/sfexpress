package edu.fudan.sfexpress.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLDecoderUtils {

	public static void main(String[] args) {

		try {
			String str = URLDecoder.decode("%E8%A5%BF%E8%97%8F", "utf-8");
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
