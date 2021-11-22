package pers.hspt.dto;

import java.util.Date;

public class DoctorDto {
	
	private int docId;
	private String docName;
	private String docPassword;
	private int money;
	private Date docTime;
	private String docStatus;
	private String depName;  //ÐèÒªÃû×Ö
	
	private String color;
	public Date getDocTime() {
		return docTime;
	}
	public void setDocTime(Date docTime) {
		this.docTime = docTime;
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
	
	public String getDepName() {
		return depName;
	}
	public void setDepName(String offName) {
		this.depName = offName;
	}

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDocPassword() {
		return docPassword;
	}
	public void setDocPassword(String docPassword) {
		this.docPassword = docPassword;
	}

}
