package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.AdminDao;
import pers.hspt.dao.imp.AdminDaoImp;
import pers.hspt.entity.Admin;
import pers.hspt.service.AdminService;
import pers.hspt.util.PageData;

public class AdminServiceImp implements AdminService{
	
	private AdminDao adminDao=new AdminDaoImp();
	
	public Admin getAdmin(String adminName){
		return adminDao.getAdmin(adminName);
	}
	
	public List<Admin> getAdminList(String adminName, PageData pageData){
		return adminDao.getAdminList(adminName, pageData);
	}
	
	public boolean addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}
	
	public int getRowsCount(String name) {
		return adminDao.getRowsCount(name);
	}
	
	public boolean delete(int adminId) {
		return adminDao.delete(adminId);
	}
	
	public boolean modify(Admin admin) {
		return adminDao.modify(admin);
	}
	
	public Admin getAdmin(int adminId) {
		return adminDao.getAdmin(adminId);
	}
}
