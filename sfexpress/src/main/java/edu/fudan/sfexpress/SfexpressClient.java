package edu.fudan.sfexpress;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import edu.fudan.sfexpress.pojo.Address;
import edu.fudan.sfexpress.utils.AreaListUtils;
import edu.fudan.sfexpress.utils.CityListUtils;
import edu.fudan.sfexpress.utils.CountryListUtils;
import edu.fudan.sfexpress.utils.DBUtils;
import edu.fudan.sfexpress.utils.PropUtils;
import edu.fudan.sfexpress.utils.ServiceTimeUtils;
import edu.fudan.sfexpress.utils.SrcProvinceListUtils;

public class SfexpressClient {
	private static final Logger log = Logger.getLogger(SfexpressClient.class);
	private List<String> provinceList;
	private List<String> cityList;
	private List<String> areaList;
	private List<String> countryList;

	public static void main(String[] args) {
		new SfexpressClient().execute();

		// List<String> mylist = new ArrayList<String>();
		// mylist.add("name1");
		// mylist.add("name2");
		// mylist.add("name3");
		// mylist.add("name4");
		// mylist.add("name5");
		//
		// System.out.println(mylist.indexOf("name5") + 1);
	}

	private void getSrcProvince() {
		provinceList = SrcProvinceListUtils.getSrcProvinceList();
		for (String ss : provinceList) {
			DBUtils.saveProvince(ss);
		}

	}

	private void getCity() {

		for (String p : provinceList) {
			List<String> cl = CityListUtils.getCityList(p);
			cityList.addAll(cl);
			for (String ss : cl) {
				DBUtils.saveCity(ss);
			}
		}
	}

	private void getArea() {
		for (String s : cityList) {
			List<String> list = AreaListUtils.getAreaList(s.split(" - ")[0],
					s.split(" - ")[1]);
			areaList.addAll(list);
			for (String ss : list) {
				DBUtils.saveArea(ss);
			}

		}
	}

	private void getCountry() {
		for (String s : areaList) {
			List<String> list = CountryListUtils.getCountryList(
					s.split(" - ")[0], s.split(" - ")[1], s.split(" - ")[2]);
			countryList.addAll(list);
			for (String ss : list) {
				DBUtils.saveCountry(ss);
			}
		}
	}

	private void queryServiceTime() {
		for (String src : countryList)
			for (String dst : countryList) {
				Address srcAddr = new Address();
				Address dstAddr = new Address();
				srcAddr.setProvince(src.split(" - ")[0]);
				srcAddr.setProvinceNo(provinceList.indexOf(src.split(" - ")[0]) + 1);
				srcAddr.setCity(src.split(" - ")[1]);
				srcAddr.setCityNo(cityList.indexOf(src.split(" - ")[0] + " - "
						+ src.split(" - ")[1]) + 1);
				srcAddr.setArea(src.split(" - ")[2]);
				srcAddr.setAreaNo(areaList.indexOf(src.split(" - ")[0] + " - "
						+ src.split(" - ")[1] + " - " + src.split(" - ")[2]) + 1);

				dstAddr.setProvince(dst.split(" - ")[0]);
				dstAddr.setProvinceNo(provinceList.indexOf(dst.split(" - ")[0]) + 1);
				dstAddr.setCity(dst.split(" - ")[1]);
				dstAddr.setCityNo(cityList.indexOf(dst.split(" - ")[0] + " - "
						+ dst.split(" - ")[1]) + 1);
				dstAddr.setArea(dst.split(" - ")[2]);
				dstAddr.setAreaNo(areaList.indexOf(dst.split(" - ")[0] + " - "
						+ dst.split(" - ")[1] + " - " + dst.split(" - ")[2]) + 1);

				if (src.split(" - ").length == 3) {
					srcAddr.setCountry("");
					srcAddr.setCountryNo(-1);
				} else {
					srcAddr.setCountry(src.split(" - ")[3]);
					srcAddr.setCountryNo(countryList.indexOf(src));
				}

				if (dst.split(" - ").length == 3) {
					dstAddr.setCountry("");
					dstAddr.setCountryNo(-1);
				} else {
					dstAddr.setCountry(dst.split(" - ")[3]);
					dstAddr.setCountryNo(countryList.indexOf(dst));
				}
				ServiceTimeUtils.getServiceTime(srcAddr, dstAddr);
				DBUtils.saveServiceTime(ServiceTimeUtils.getServiceTime(
						srcAddr, dstAddr));
			}
	}

	private void execute() {
		init();
		getSrcProvince();
		getCity();
		getArea();
		getCountry();
		queryServiceTime();
		shutdown();
	}

	private void shutdown() {
		DBUtils.close();

		log.info("complete all tasks!");
	}

	private void init() {
		DOMConfigurator.configure("log4j.xml");
		DBUtils.createDb(PropUtils.getValue("dbName"));
		DBUtils.connect();
		DBUtils.createTable();
		cityList = new ArrayList<String>();
		areaList = new ArrayList<String>();
		countryList = new ArrayList<String>();
	}
}
