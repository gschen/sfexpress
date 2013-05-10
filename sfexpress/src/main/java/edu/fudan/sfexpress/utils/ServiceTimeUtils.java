package edu.fudan.sfexpress.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import edu.fudan.sfexpress.constants.SendTime;
import edu.fudan.sfexpress.pojo.AddTimeInfo;
import edu.fudan.sfexpress.pojo.Address;
import edu.fudan.sfexpress.pojo.Params;
import edu.fudan.sfexpress.pojo.ServiceTime;

public class ServiceTimeUtils {
	private static final Logger log = Logger.getLogger(ServiceTimeUtils.class);

	public static ServiceTime getServiceTime(Address srcAddr, Address dstAddr) {
		ServiceTime serviceTime = new ServiceTime();
		serviceTime.setSrcAddr(srcAddr);
		serviceTime.setDstAddr(dstAddr);
		String url = "http://www.sf-express.com/dwr/call/plaincall/ServiceTimeManager.getServiceTime_omp.dwr";
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
				"getServiceTime_omp"));
		params.add(new BasicNameValuePair(Params.C0ID, "0"));
		params.add(new BasicNameValuePair(Params.C0PARAM0, "string:"
				+ srcAddr.getProvince()));
		params.add(new BasicNameValuePair(Params.C0PARAM1, "string:"
				+ srcAddr.getCity()));
		params.add(new BasicNameValuePair(Params.C0PARAM2, "string:"
				+ srcAddr.getArea()));
		params.add(new BasicNameValuePair(Params.C0PARAM3, "string:"
				+ srcAddr.getCountry()));
		params.add(new BasicNameValuePair(Params.C0PARAM4, "string:"
				+ dstAddr.getProvince()));
		params.add(new BasicNameValuePair(Params.C0PARAM5, "string:"
				+ dstAddr.getCity()));
		params.add(new BasicNameValuePair(Params.C0PARAM6, "string:"
				+ dstAddr.getArea()));
		params.add(new BasicNameValuePair(Params.C0PARAM7, "string:"
				+ dstAddr.getCountry()));
		params.add(new BasicNameValuePair(Params.C0PARAM8, "string:"
				+ SendTime.day));
		params.add(new BasicNameValuePair(Params.C0PARAM9, "string:"
				+ SendTime.hour));
		params.add(new BasicNameValuePair(Params.C0PARAM10, "string:"
				+ SendTime.minute));
		params.add(new BasicNameValuePair(Params.C0PARAM11, "string:CN"));
		params.add(new BasicNameValuePair(Params.C0PARAM12, "string:SC"));
		params.add(new BasicNameValuePair(Params.BATCHID, "24"));

		HttpClientUtils.doPost(url, params);
		String rtn = HttpClientUtils.getResponseAsString();
		// System.out.println(rtn);

		// map<快递编号, 时间差(day)>
		Map<String, Double> serviceTmMap = new HashMap<String, Double>();

		List<String> blearDeliverTmList = StringParserUtils.parseString(rtn,
				"(?:s\\d{1,}.blearDeliverTm=\")(.*?)(?:\";)");
		List<String> productTypeList = StringParserUtils.parseString(rtn,
				"(?:s\\d{1,}.productTypeCode=\")(.*?)(?:\";)");
		log.info(srcAddr.toString() + " >> " + dstAddr.toString());

		for (int i = 0; i < blearDeliverTmList.size(); ++i) {
			serviceTmMap.put(productTypeList.get(i),
					calHours(blearDeliverTmList.get(i) + ":00"));
			// serviceTmList.add(new ServiceTime(srcAddr, dstAddr,
			// blearDeliverTmList.get(i), productTypeList.get(i)));
			// log.info(productTypeList.get(i) + " - " +
			// blearDeliverTmList.get(i));
		}

		Set<String> keys = serviceTmMap.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			// System.out.println(key);
			// System.out.println(serviceTmMap.get(key));

			if ("T1".equals(key)) {
				serviceTime.setT1_time(serviceTmMap.get("T1"));
			}
			if ("T4".equals(key)) {
				serviceTime.setT4_time(serviceTmMap.get("T4"));
			}
			if ("SP4".equals(key)) {
				serviceTime.setSp4_time(serviceTmMap.get("SP4"));
			}
			if ("SP5".equals(key)) {
				serviceTime.setSp5_time(serviceTmMap.get("SP5"));
			}
			if ("T6".equals(key)) {
				serviceTime.setT6_time(serviceTmMap.get("T6"));
			}
			if ("T801".equals(key)) {
				serviceTime.setT801_time(serviceTmMap.get("T801"));
			}
		}

		// 加时信息
		AddTimeInfo addTimeInfo = AddTimeAddrUtils.getAddTimeAddr(dstAddr);
		serviceTime.setAddTimeInfo(addTimeInfo);

		return serviceTime;
	}

	public static double calHours(String deliverTm) {
		String sendTime = SendTime.getTime();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		double hours = 0.0;
		try {
			Date sendDate = df.parse(sendTime);
			Date deliverDate = df.parse(deliverTm);
			double diff = deliverDate.getTime() - sendDate.getTime();
			hours = diff / (1000 * 60 * 60);
			// log.info("deliver time needs: " + hours + " hours.");

		} catch (Exception e) {
		}
		return hours;

	}
}
