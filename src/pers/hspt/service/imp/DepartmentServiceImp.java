package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.DepartmentDao;
import pers.hspt.dao.imp.DepartmentDaoImp;
import pers.hspt.entity.Department;
import pers.hspt.service.DepartmentService;
import pers.hspt.util.PageData;

public class DepartmentServiceImp implements DepartmentService{
	
	private DepartmentDao departmentDao = new DepartmentDaoImp();
	
	public void add(Department department) {
		departmentDao.add(department);
	}
	
	public List<Department> getList(String depName,PageData pageData) {
		return departmentDao.getList(depName,pageData);
	}

	@Override
	public int getRowsCount(String name) {
		
		return departmentDao.getRowsCount(name);
	}
	
	
	public void delete(int depId) {
		departmentDao.delete(depId);
	}
	
	public Department get(int depId) {
		return departmentDao.get(depId);
	}
	
	public Department get(String name) {
		return departmentDao.get(name);
	}
	
	
	public void modify(Department department, int depId) {
		departmentDao.modify(department, depId);
	}

}
