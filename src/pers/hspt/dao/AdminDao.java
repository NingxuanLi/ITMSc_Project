package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Admin;
import pers.hspt.util.PageData;


public interface AdminDao {
	
	public Admin getAdmin(String adminName);
	
	public List<Admin> getAdminList(String adminName, PageData pageData);
	
	public void addAdmin(Admin admin);
	
	public int getRowsCount(String name);
	
	public void delete(int adminId);
	
	public void modify(Admin admin);
	
	public Admin getAdmin(int adminId);

}
