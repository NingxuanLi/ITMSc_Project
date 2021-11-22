package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Doctor;
import pers.hspt.util.PageData;

public interface DoctorService {
	
	public List<Doctor> getByDepId(int depid);
	
	public void add(Doctor doctor);
	
	public int getRowsCount(String name);
	
	public int getRowsCountForOneDep(int depId);
	
	public List<Doctor> getList(String docName, PageData pagedata);
	
	public Doctor get(int docId);
	
	public Doctor get(String name);
	
	public void delete(int docId);
	
	public void modify(Doctor doctor);

}
