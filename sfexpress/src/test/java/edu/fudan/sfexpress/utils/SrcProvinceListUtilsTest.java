package edu.fudan.sfexpress.utils;

import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SrcProvinceListUtilsTest {

	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		DBUtils.createDb("D:\\t1111122.accdb");
		DBUtils.connect();
		DBUtils.createTable();
	}

	@After
	public void tearDown() throws Exception {
		DBUtils.close();
	}

	@Test
	public void testGetSrcProvinceList() {
		List<String> provinceList = SrcProvinceListUtils.getSrcProvinceList();
		for (String province : provinceList) {
			System.out.println(province);
			DBUtils.saveProvince(province);

		}
	}

}
