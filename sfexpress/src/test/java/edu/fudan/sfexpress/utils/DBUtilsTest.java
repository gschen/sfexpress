package edu.fudan.sfexpress.utils;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBUtilsTest {

	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		// DBUtils.connect();
	}

	@After
	public void tearDown() throws Exception {
		// DBUtils.close();
	}

	// @Test
	public void testSaveProvice() {
		for (int i = 0; i < 10; ++i) {
			DBUtils.saveProvince("湖南" + i);
			DBUtils.saveCity("湖南" + i);
			DBUtils.saveArea("湖南" + i);
			DBUtils.saveCountry("湖南" + i);
		}
	}

	@Test
	public void testCreateDb() {
		DBUtils.createDb("D:\\1234a4.accdb");
		DBUtils.connect();
		DBUtils.createTable();
		DBUtils.close();
	}

}
