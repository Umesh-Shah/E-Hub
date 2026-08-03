package VO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Link implements Serializable {
	private long linkID,dispatchDT,activationDT;
	private String link;
	private Signup userID;
	 
	private int status;

	public long getLinkID() {
		return linkID;
	}

	public void setLinkID(long linkID) {
		this.linkID = linkID;
	}

	public long getDispatchDT() {
		return dispatchDT;
	}

	public void setDispatchDT(long dispatchDT) {
		this.dispatchDT = dispatchDT;
	}

	public long getActivationDT() {
		return activationDT;
	}

	public void setActivationDT(long activationDT) {
		this.activationDT = activationDT;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Signup getUserID() {
		return userID;
	}

	public void setUserID(Signup userID) {
		this.userID = userID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}