package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Admin;
import pers.hspt.util.PageData;


public interface AdminDao {
	
	//��¼ʱ�����ݹ���Ա���ֵõ�����Ա
	public Admin getAdmin(String adminName);

}
