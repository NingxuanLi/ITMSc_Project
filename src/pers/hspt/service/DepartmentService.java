package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Department;
import pers.hspt.util.PageData;


public interface DepartmentService {
	
	public boolean add(Department department);
	
	//分页，得到总行数
	public int getRowsCount(String name);
	
	//查询所有和模糊
	public List<Department> getList(String depName,PageData pageData); //可以传空的
	
	public boolean delete(int depId);
	
	public Department get(int depId);
	
	public Department get(String name);
	
	public boolean modify(Department Department, int depId);

}
