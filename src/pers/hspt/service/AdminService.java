package pers.hspt.service;


import java.util.List;

import pers.hspt.entity.Admin;
import pers.hspt.util.PageData;

public interface AdminService {
	
	//get the admin by the name string
	public Admin getAdmin(String adminName);
	
	public List<Admin> getAdminList(String adminName, PageData pageData);
	
	public boolean addAdmin(Admin admin);
	
	public int getRowsCount(String name);
	
	public boolean delete(int adminId);
	
	public void modify(Admin admin);
	
	public Admin getAdmin(int adminId);

}
