package pers.hspt.entity;

public class Patient {
	
	private int pId;
	private String name;
	private String password;
	private String realName;
	private String gender;
	private String tel;
	private String brp;
	
//	color
	private String color;
	
	public Patient(){}
	public Patient(String name, String password, String realname, String gender,
			String tel, String brp) {
		super();
		this.name = name;
		this.password = password;
		this.realName = realname;
		this.gender = gender;
		this.tel = tel;
		this.brp = brp;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		return gender;
	}
	public void setSex(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBrp() {
		return brp;
	}
	public void setBrp(String brp) {
		this.brp = brp;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
