package edu.fudan.sfexpress.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CityListUtilsTest {
	private static final Logger log = Logger
			.getLogger(AgingTypeListUtilsTest.class);

	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCityList() {
		// System.out.println(StringEscapeUtils.unescapeJava("\u5E02"));
		CityListUtils.getCityList("上海");
	}
}
