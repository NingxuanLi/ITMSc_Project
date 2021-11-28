package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Doctor;
import pers.hspt.util.PageData;

public interface DoctorDao {
	
	public List<Doctor> getByDepId(int depId, PageData pageData);
	
	public void add(Doctor doctor);
	
	public int getRowsCount(String name);
	
	public List<Doctor> getList(String docName, PageData pageData);
	
	public Doctor get(int docId);
	
	public Doctor get(String name);
	
	public void delete(int docId);
	
	public void modify(Doctor doctor);
	
	public int getRowsCountForOneDep(int depId);

}
