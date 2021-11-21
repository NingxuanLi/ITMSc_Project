package pers.hspt.dto;

import java.util.Date;

public class DoctorAppDto {
	
	private String pName;
	private String pRealName;
	private String pGender;
	private String pTelNum;
	private String pBrpNum;
	
	private Date appTime;

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpRealName() {
		return pRealName;
	}

	public void setpRealName(String pRealName) {
		this.pRealName = pRealName;
	}

	public String getpGender() {
		return pGender;
	}

	public void setpGender(String pGender) {
		this.pGender = pGender;
	}

	public String getpTelNum() {
		return pTelNum;
	}

	public void setpTelNum(String pTelNum) {
		this.pTelNum = pTelNum;
	}

	public String getpBrpNum() {
		return pBrpNum;
	}

	public void setpBrpNum(String pBrpNum) {
		this.pBrpNum = pBrpNum;
	}

	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}

}
