package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Department;
import pers.hspt.util.PageData;

public interface DepartmentDao {
	
	public boolean add(Department department);
	
	public List<Department> getList(String depName, PageData pageData);
	
	public int getRowsCount(String name);
	
	public boolean delete(int depId);
	
	public Department get(int depId);
	
	public Department get(String name);
	
	public boolean modify(Department department, int depId);

}
