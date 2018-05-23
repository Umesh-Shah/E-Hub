package VO;

import java.io.Serializable;

public class City implements Serializable{

	private String cityName;
	private long cityid;
	private Country countid;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public long getCityid() {
		return cityid;
	}
	public void setCityid(long cityid) {
		this.cityid = cityid;
	}
	public Country getCountid() {
		return countid;
	}
	public void setCountid(Country countid) {
		this.countid = countid;
	}
	
}
