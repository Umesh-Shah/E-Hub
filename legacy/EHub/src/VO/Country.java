package VO;

import java.io.Serializable;

public class Country implements Serializable{

	private String countName;
	private long countid;
	public String getCountName() {
		return countName;
	}
	public void setCountName(String countName) {
		this.countName = countName;
	}
	public long getCountid() {
		return countid;
	}
	public void setCountid(long countid) {
		this.countid = countid;
	}
	
	
}
