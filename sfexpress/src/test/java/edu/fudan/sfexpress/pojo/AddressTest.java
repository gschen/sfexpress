package edu.fudan.sfexpress.pojo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {
	private Address addr;

	@Before
	public void setUp() throws Exception {
		addr = new Address("安徽", "安庆市", "庐山县", "率听证");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetProvinceStr() {
		System.out.println(addr.getProvinceStr());
	}

	@Test
	public void testGetCityStr() {
		System.out.println(addr.getCityStr());
	}

	@Test
	public void testGetAreaStr() {
		System.out.println(addr.getAreaStr());
	}

	@Test
	public void testGetCountryStr() {
		System.out.println(addr.getCountryStr());
	}

}
