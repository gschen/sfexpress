package edu.fudan.sfexpress.pojo;

public class Address {

	public Address() {

	}

	@Override
	public String toString() {
		return province + " - " + city + " - " + area + " - " + country;
	}

	private String province;
	private String city;
	private String area;
	private String country;

	private int provinceNo;
	private int cityNo;
	private int areaNo;
	private int countryNo;

	public String getProvinceStr() {
		return province;
	}

	public String getCityStr() {
		return province + " - " + city;
	}

	public String getAreaStr() {
		return province + " - " + city + " - " + area;
	}

	public String getCountryStr() {
		return toString();
	}

	public int getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(int provinceNo) {
		this.provinceNo = provinceNo;
	}

	public int getCityNo() {
		return cityNo;
	}

	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}

	public int getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}

	public int getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(int countryNo) {
		this.countryNo = countryNo;
	}

	public Address(String province, String city, String area, String country) {
		this.province = province;
		this.city = city;
		this.area = area;
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
