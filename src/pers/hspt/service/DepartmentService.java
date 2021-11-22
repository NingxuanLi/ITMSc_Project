package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Department;
import pers.hspt.util.PageData;


public interface DepartmentService {
	
	public void add(Department department);
	
	//��ҳ���õ�������
	public int getRowsCount(String name);
	
	//��ѯ���к�ģ��
	public List<Department> getList(String depName,PageData pageData); //���Դ��յ�
	
	public void delete(int depId);
	
	public Department get(int depId);
	
	public Department get(String name);
	
	public void modify(Department Department, int depId);

}
