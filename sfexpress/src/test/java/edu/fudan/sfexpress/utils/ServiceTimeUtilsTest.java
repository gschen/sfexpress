package edu.fudan.sfexpress.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.fudan.sfexpress.pojo.Address;
import edu.fudan.sfexpress.pojo.ServiceTime;

public class ServiceTimeUtilsTest {
	private static final Logger log = Logger
			.getLogger(ServiceTimeUtilsTest.class);

	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		DBUtils.createDb("D:\\199.accdb");
		DBUtils.connect();
		DBUtils.createTable();
	}

	@After
	public void tearDown() throws Exception {
		DBUtils.close();
	}

	@Test
	public void testGetServiceTime() {
		ServiceTime st = ServiceTimeUtils.getServiceTime(new Address("安徽",
				"安庆市", "枞阳县", ""), new Address("上海", "上海市", "长宁区", "虹桥街道"));
		DBUtils.saveServiceTime(st);
	}

	// @Test
	public void testCalDays() {
		ServiceTimeUtils.calHours("2013-05-04 13:45:00");
	}
}
