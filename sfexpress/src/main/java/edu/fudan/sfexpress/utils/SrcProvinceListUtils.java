package edu.fudan.sfexpress.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import edu.fudan.sfexpress.pojo.Params;

public class SrcProvinceListUtils {

	/**
	 * 
	 * 获取初始的srcProvince列表
	 * 
	 * @param province
	 * @return
	 */
	public static List<String> getSrcProvinceList() {

		String url = "http://www.sf-express.com/dwr/call/plaincall/ServiceTimeManager.getSrcProvinceList.dwr";
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
		params.add(new BasicNameValuePair(Params.C0METHODNAME,
				"getSrcProvinceList"));
		params.add(new BasicNameValuePair(Params.C0ID, "0"));
		params.add(new BasicNameValuePair(Params.C0PARAM0, "string:CN"));
		params.add(new BasicNameValuePair(Params.C0PARAM1, "string:SC"));
		params.add(new BasicNameValuePair(Params.BATCHID, "0"));

		HttpClientUtils.doPost(url, params);
		String rtn = HttpClientUtils.getResponseAsString();
		// System.out.println(rtn);

		return StringParserUtils.parseString(rtn,
				"(?:s\\d{1,}.key=\")(.*?)(?:\";)");
	}

}
