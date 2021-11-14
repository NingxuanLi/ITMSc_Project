package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.DepartmentDao;
import pers.hspt.dao.imp.DepartmentDaoImp;
import pers.hspt.entity.Department;
import pers.hspt.service.DepartmentService;
import pers.hspt.util.PageData;

public class DepartmentServiceImp implements DepartmentService{
	
	private DepartmentDao departmentDao = new DepartmentDaoImp();
	
	public boolean add(Department department) {
		return departmentDao.add(department);
	}
	
	public List<Department> getList(String depName,PageData pageData) {
		return departmentDao.getList(depName,pageData);
	}

	@Override
	public int getRowsCount(String name) {
		
		return departmentDao.getRowsCount(name);
	}
	
	
	public boolean delete(int depId) {
		return departmentDao.delete(depId);
	}
	
	public Department get(int depId) {
		return departmentDao.get(depId);
	}
	
	
	public boolean modify(Department department, int depId) {
		return departmentDao.modify(department, depId);
	}

}
