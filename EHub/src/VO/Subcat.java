package VO;

import java.io.Serializable;

public class Subcat implements Serializable {
	
	private String subcatName;
	  private String subcatDes;
	  private Cat id; 
	  private long subcatid;
	public String getSubcatName() {
		return subcatName;
	}
	public void setSubcatName(String subcatName) {
		this.subcatName = subcatName;
	}
	public String getSubcatDes() {
		return subcatDes;
	}
	public void setSubcatDes(String subcatDes) {
		this.subcatDes = subcatDes;
	}
	public Cat getId() {
		return id;
	}
	public void setId(Cat id) {
		this.id = id;
	}
	public long getSubcatid() {
		return subcatid;
	}
	public void setSubcatid(long subcatid) {
		this.subcatid = subcatid;
	}
	  
	  
}
	