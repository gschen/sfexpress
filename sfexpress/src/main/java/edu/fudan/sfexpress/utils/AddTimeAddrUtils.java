package edu.fudan.sfexpress.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import edu.fudan.sfexpress.pojo.AddTimeInfo;
import edu.fudan.sfexpress.pojo.Address;
import edu.fudan.sfexpress.pojo.Params;

// 计算加时区域信息工具
public class AddTimeAddrUtils {
	private static final Logger log = Logger.getLogger(AddTimeAddrUtils.class);

	public static AddTimeInfo getAddTimeAddr(Address dstAddr) {

		String url = "http://www.sf-express.com/dwr/call/plaincall/ServiceTimeManager.queryAddTimeAddr.dwr";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Params.CALLCOUNT, "1"));
		params.add(new BasicNameValuePair(Params.PAGE,
				"/cn/sc/delivery_step/enquiry/serviceTime.html"));
		params.add(new BasicNameValuePair(Params.HTTPSESSIONID,
				"AA221E633D34390E22017CDF44103228"));
		params.add(new BasicNameValuePair(Params.SCRIPTSESSIONID,
				"ABCFB15977C207121D3B56939B23DAE5952"));
		params.add(new BasicNameValuePair(Params.C0SCRIPTNAME,
				"ServiceTimeManager"));
		params.add(new BasicNameValuePair(Params.C0METHODNAME,
				"queryAddTimeAddr"));
		params.add(new BasicNameValuePair(Params.C0ID, "0"));
		params.add(new BasicNameValuePair(Params.C0PARAM0, "string:"
				+ dstAddr.getProvince()));
		params.add(new BasicNameValuePair(Params.C0PARAM1, "string:"
				+ dstAddr.getCity()));
		params.add(new BasicNameValuePair(Params.C0PARAM2, "string:"
				+ dstAddr.getArea()));
		params.add(new BasicNameValuePair(Params.C0PARAM3, "string:"
				+ dstAddr.getCountry()));
		params.add(new BasicNameValuePair(Params.C0PARAM4, "string:"));

		params.add(new BasicNameValuePair(Params.C0PARAM5, "string:CN"));
		params.add(new BasicNameValuePair(Params.C0PARAM6, "string:SC"));
		params.add(new BasicNameValuePair(Params.BATCHID, "26"));

		HttpClientUtils.doPost(url, params);
		String rtn = HttpClientUtils.getResponseAsString();
		// System.out.println(rtn);

		if (rtn.contains("new AddTimeAddrInfo();")) {
			return new AddTimeInfo(
					Double.parseDouble(StringParserUtils.parseString(rtn,
							"(?:s\\d{1,}.addTime=)(.*?)(?:;)").get(0)),
					StringParserUtils.parseString(rtn,
							"(?:s\\d{1,}\\[\\d{1,}\\]=\")(.*?)(?:\";)").get(0));
		} else {
			return new AddTimeInfo();
		}

		// log.info(StringParserUtils.parseString(rtn,
		// "(?:s\\d{1,}.addTime=)(.*?)(?:;)").get(0));
		//
		// log.info(StringParserUtils.parseString(rtn,
		// "(?:s\\d{1,}\\[\\d{1,}\\]=\")(.*?)(?:\";)").get(0));

	}
}
