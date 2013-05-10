package edu.fudan.sfexpress.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertiesUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Properties props = new Properties();
		String filePath = "D:\\test.txt";
		String key = "hello";
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			String value = props.getProperty(key);
			System.out.println(key + " = " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
