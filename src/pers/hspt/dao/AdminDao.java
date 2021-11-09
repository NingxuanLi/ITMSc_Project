package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Admin;
import pers.hspt.util.PageData;


public interface AdminDao {
	
	//get admin by name string
	public Admin getAdmin(String adminName);

}
