package edu.fudan.sfexpress.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import edu.fudan.sfexpress.pojo.Params;

public class CountryListUtils {
	public static List<String> getCountryList(String province, String city,
			String area) {

		String url = "http://www.sf-express.com/dwr/call/plaincall/ServiceTimeManager.getCountryList.dwr";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Params.CALLCOUNT, "1"));
		params.add(new BasicNameValuePair(Params.PAGE,
				"/cn/sc/delivery_step/enquiry/serviceTime.html"));
		params.add(new BasicNameValuePair(Params.HTTPSESSIONID,
				"85CEBCF3254841F5D84FE8127EEB34D3 "));
		params.add(new BasicNameValuePair(Params.SCRIPTSESSIONID,
				"FAFFC11E811AAFE380763629185DB777837"));
		params.add(new BasicNameValuePair(Params.C0SCRIPTNAME,
				"ServiceTimeManager"));
		params.add(new BasicNameValuePair(Params.C0METHODNAME, "getCountryList"));
		params.add(new BasicNameValuePair(Params.C0ID, "0"));
		params.add(new BasicNameValuePair(Params.C0PARAM0, "string:" + province));
		params.add(new BasicNameValuePair(Params.C0PARAM1, "string:" + city));
		params.add(new BasicNameValuePair(Params.C0PARAM2, "string:" + area));
		params.add(new BasicNameValuePair(Params.C0PARAM3, "string:CN"));
		params.add(new BasicNameValuePair(Params.C0PARAM4, "string:SC"));
		params.add(new BasicNameValuePair(Params.BATCHID, "7"));

		HttpClientUtils.doPost(url, params);
		String rtn = HttpClientUtils.getResponseAsString();
		// System.out.println(rtn);

		return StringParserUtils.parseString(rtn,
				"(?:s\\d{1,}.key=\")(.*?)(?:\";)", province + " - " + city
						+ " - " + area + " - ");
	}
}
