package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Department;
import pers.hspt.util.PageData;


public interface DepartmentService {
	
	public void add(Department department);
	
	public int getRowsCount(String name);
	
	public List<Department> getList(String depName,PageData pageData); 
	
	public void delete(int depId);
	
	public Department get(int depId);
	
	public Department get(String name);
	
	public void modify(Department Department, int depId);

}
