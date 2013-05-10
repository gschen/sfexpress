package edu.fudan.sfexpress.utils;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DstProvinceListUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<String> provinceList = DstProvinceListUtils.getDstProvinceList();
		System.out.println("Province size is: " + provinceList.size());
		for (String province : provinceList)
			System.out.println(province);
	}

}
