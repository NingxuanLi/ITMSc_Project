package pers.hspt.entity;

import java.util.Date;

public class Doctor {
	
	private int docId;
	private String docName;
	private String docPassword;
	private int money;
	private Date docTime;
	private String docStatus;
	private int depId;
	

	//color
	private String color;
	
	
	public Doctor(){}
	
	
	public Doctor(String docName, String password,int money, Date docTime, String docStatus, int depId) {
		super();
		this.docName = docName;
		this.docPassword = password;
		this.money = money;
		this.docTime = docTime;
		this.docStatus = docStatus;
		this.depId = depId;
	}


	
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public String getDocPassword() {
		return docPassword;
	}
	public void setDocPassword(String docPassword) {
		this.docPassword = docPassword;
	}


	

	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public Date getDocTime() {
		return docTime;
	}
	public void setDocTime(Date docTime) {
		this.docTime = docTime;
	}
	

	

	
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}


	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
