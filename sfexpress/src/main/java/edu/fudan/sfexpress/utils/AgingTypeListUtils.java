package edu.fudan.sfexpress.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import edu.fudan.sfexpress.pojo.Params;

public class AgingTypeListUtils {
	private static final Logger log = Logger
			.getLogger(AgingTypeListUtils.class);

	public static Map<String, String> getAgingTypeList() {

		String url = "http://www.sf-express.com/dwr/call/plaincall/AgingTypeManager.getAgingTypeList.dwr";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Params.CALLCOUNT, "1"));
		params.add(new BasicNameValuePair(Params.PAGE,
				"/cn/sc/delivery_step/enquiry/serviceTime.html"));
		params.add(new BasicNameValuePair(Params.HTTPSESSIONID,
				"85CEBCF3254841F5D84FE8127EEB34D3 "));
		params.add(new BasicNameValuePair(Params.SCRIPTSESSIONID,
				"FAFFC11E811AAFE380763629185DB777837"));
		params.add(new BasicNameValuePair(Params.C0SCRIPTNAME,
				"AgingTypeManager"));
		params.add(new BasicNameValuePair(Params.C0METHODNAME,
				"getAgingTypeList"));
		params.add(new BasicNameValuePair(Params.C0ID, "0"));
		params.add(new BasicNameValuePair(Params.C0PARAM0, "string:SC"));
		params.add(new BasicNameValuePair(Params.BATCHID, "16"));

		HttpClientUtils.doPost(url, params);
		String rtn = HttpClientUtils.getResponseAsString();
		System.out.println(rtn);

		List<String> code = StringParserUtils.parseString(rtn,
				"(?:s\\d{1,}.key=\")(.*?)(?:\";)");
		List<String> content = StringParserUtils.parseString(rtn,
				"(?:s\\d{1,}.value=\")(.*?)(?:\";)");

		int listSize = code.size();

		Map<String, String> agingTypeMap = new HashMap<String, String>();
		for (int i = 0; i < listSize; ++i) {
			log.info("--------------------------");
			log.info("code:	" + code.get(i));
			log.info("content: " + content.get(i));
			agingTypeMap.put(code.get(i), content.get(i));
		}
		return agingTypeMap;
	}
}
