package edu.fudan.sfexpress.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HttpClientUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	public void testGetResponseAsString() {
		HttpClientUtils.doGet("http://www.witsun.cn");
		String rtn = HttpClientUtils.getResponseAsString();
		System.out.println(rtn);
	}

	// @Test
	public void testDoPost() {

		String url = "http://www.sf-express.com/dwr/call/plaincall/ServiceTimeManager.getCityList.dwr";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("callCount", "1"));
		params.add(new BasicNameValuePair("page",
				"/cn/sc/delivery_step/enquiry/serviceTime.html"));
		params.add(new BasicNameValuePair("httpSessionId",
				"85CEBCF3254841F5D84FE8127EEB34D3 "));
		params.add(new BasicNameValuePair("scriptSessionId",
				"FAFFC11E811AAFE380763629185DB777837"));
		params.add(new BasicNameValuePair("c0-scriptName", "ServiceTimeManager"));
		params.add(new BasicNameValuePair("c0-methodName", "getCityList"));
		params.add(new BasicNameValuePair("c0-id", "0"));
		params.add(new BasicNameValuePair("c0-param0",
				"string:%E5%AE%89%E5%BE%BD"));
		params.add(new BasicNameValuePair("c0-param1", "string:CN"));
		params.add(new BasicNameValuePair("c0-param2", "string:SC"));
		params.add(new BasicNameValuePair("batchId", "3"));

		HttpClientUtils.doPost(url, params);
		String rtn = HttpClientUtils.getResponseAsString();
		System.out.println(rtn);
	}

	@Test
	public void test() {

	}

}
