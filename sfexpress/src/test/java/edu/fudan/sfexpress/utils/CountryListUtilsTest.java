package edu.fudan.sfexpress.utils;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountryListUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCountryList() {
		List<String> countryList = CountryListUtils.getCountryList("广东", "广州市",
				"白云区");
		System.out.println("Country size is: " + countryList.size());
		for (String country : countryList)
			System.out.println(country);
	}

}
