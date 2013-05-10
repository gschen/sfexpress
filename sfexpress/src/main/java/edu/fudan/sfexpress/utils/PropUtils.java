package edu.fudan.sfexpress.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import edu.fudan.sfexpress.constants.SystemConstants;

public class PropUtils {

	public static String getValue(String key) {
		Properties props = new Properties();
		String value = null;
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					SystemConstants.PROP_FILE_PATH));
			props.load(in);
			value = props.getProperty(key);
			// System.out.println(key + " = " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
