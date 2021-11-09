package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Admin;
import pers.hspt.util.PageData;


public interface AdminDao {
	
	//登录时，根据管理员名字得到管理员
	public Admin getAdmin(String adminName);

}
