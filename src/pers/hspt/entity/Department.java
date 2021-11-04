package pers.hspt.entity;

public class Department {
	
	private int depId;
	private String depName;
	
	private String color;
	public Department(){};
	public Department(int depId, String depName) {
		super();
		this.depId = depId;
		this.depName = depName;
	}
	public int getdepId() {
		return depId;
	}
	public void setdepId(int depId) {
		this.depId = depId;
	}
	public String getdepName() {
		return depName;
	}
	public void setdepName(String depName) {
		this.depName = depName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
