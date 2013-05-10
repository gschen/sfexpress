package edu.fudan.sfexpress.utils;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.fudan.sfexpress.pojo.Address;

public class AddTimeAddrUtilsTest {

	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAddTimeAddr() {
		AddTimeAddrUtils
				.getAddTimeAddr(new Address("西藏", "拉萨市", "城关区", "八廓街道"));
		// AddTimeAddrUtils.getAddTimeAddr(new Address("安徽", "安庆市", "枞阳县", ""));
	}

}
