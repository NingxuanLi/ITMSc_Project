package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Doctor;
import pers.hspt.util.PageData;

public interface DoctorService {
	
	public List<Doctor> getByDepId(int depid);
	
	public boolean add(Doctor doctor);
	
	public int getRowsCount(String name);
	
	public List<Doctor> getList(String docName, PageData pagedata);
	
	public Doctor get(int docId);
	
	public boolean delete(int docId);
	
	public boolean modify(Doctor doctor);

}
