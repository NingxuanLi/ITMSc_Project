package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Department;
import pers.hspt.util.PageData;


public interface DepartmentService {
	
	public void add(Department department);
	
	//分页，得到总行数
	public int getRowsCount(String name);
	
	//查询所有和模糊
	public List<Department> getList(String depName,PageData pageData); //可以传空的
	
	public void delete(int depId);
	
	public Department get(int depId);
	
	public Department get(String name);
	
	public void modify(Department Department, int depId);

}
