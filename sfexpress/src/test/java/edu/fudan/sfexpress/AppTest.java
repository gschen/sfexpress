package edu.fudan.sfexpress;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.fudan.sfexpress.utils.HttpClientUtils;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);

		HttpClientUtils.doGet("http://www.witsun.cn");
		String rtn = HttpClientUtils.getResponseAsString();
		System.out.println(rtn);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
