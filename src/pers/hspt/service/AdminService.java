package pers.hspt.service;


import java.util.List;

import pers.hspt.entity.Admin;
import pers.hspt.util.PageData;

public interface AdminService {
	
	//get the admin by the name string
	public Admin getAdmin(String adminName);

}
