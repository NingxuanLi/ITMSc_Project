package pers.hspt.entity;

import java.util.Date;

public class Appointment {
	
	private int appId;
	private String appNum;
	private int pId;
	private int docId;
	private Date createTime;
	private Date appTime;
	private String appState;
	
	public Appointment(){}
	public Appointment(String appNum, int pId, int docId, Date createTime,
			Date appTime) {
		super();
		this.appNum = appNum;
		this.pId = pId;
		this.docId = docId;
		this.createTime = createTime;
		this.appTime = appTime;
	}
	
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	
	public String getAppNum() {
		return appNum;
	}
	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}
	
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getAppTime() {
		return appTime;
	}
	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}
	
	public String getAppState() {
		return appState;
	}
	public void setAppState(String appState) {
		this.appState = appState;
	}

}
