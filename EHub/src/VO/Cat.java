package VO;

import java.io.Serializable;
import java.util.Set;

//import java.io.Serializable;
public class Cat implements Serializable {

		private String catName;
		  private String catDes;
		  private long id;
		//  Set subcatset;
		
		  public String getCatName() {
			return catName;
		}
		
		public void setCatName(String catName) {
			this.catName = catName;
		}
		public String getCatDes() {
			return catDes;
		}
		public void setCatDes(String catDes) {
			this.catDes = catDes;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		  
	
	
}
