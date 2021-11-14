package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Doctor;
import pers.hspt.util.PageData;

public interface DoctorDao {
	
	public List<Doctor> getByDepId(int offId);
	
	public boolean add(Doctor doctor);
	
	public int getRowsCount(String name);
	
	public List<Doctor> getList(String docName, PageData pageData);
	
	public Doctor get(int docId);
	
	public boolean delete(int docId);
	
	public boolean modify(Doctor doctor);

}
