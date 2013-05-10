package edu.fudan.sfexpress.utils;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AreaListUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<String> areaList = AreaListUtils.getAreaList("安徽", "安庆市");
		System.out.println("Area size is: " + areaList.size());
		for (String area : areaList)
			System.out.println(area);
	}

}
