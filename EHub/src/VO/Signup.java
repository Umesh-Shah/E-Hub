package VO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Signup implements Serializable {

	private long userID;
	private String firstName,lastName,password,email,location,service ;
	private Set linkset =	new HashSet(0);
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Set getLinkset() {
		return linkset;
	}
	public void setLinkset(Set linkset) {
		this.linkset = linkset;
	}
	/*public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
}
