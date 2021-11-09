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

}
