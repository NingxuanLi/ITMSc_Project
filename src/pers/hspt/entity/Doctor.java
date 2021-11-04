package pers.hspt.entity;

import java.util.Date;

public class Doctor {
	
	private int docId;
	private String docName;
	private String docPassword;
	private String docImg;
	private int money;
	private Date docTime;
	private int sumCount;
	private int remainCount;
	private String docStatus;
	private int depId;
	

	//color
	private String color;
	
	
	public Doctor(){}
	
	
	public Doctor(String docName, String docImg, int money, Date docTime,
			int sumCount, int remainCount, String docStatus, int depId) {
		super();
		this.docName = docName;
		this.docImg = docImg;
		this.money = money;
		this.docTime = docTime;
		this.sumCount = sumCount;
		this.remainCount = remainCount;
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


	
	public String getDocImg() {
		return docImg;
	}
	public void setDocImg(String docImg) {
		this.docImg = docImg;
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
	
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	
	public int getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
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
