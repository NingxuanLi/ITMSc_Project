package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Patient;
import pers.hspt.util.PageData;

public interface PatientDao {
	
	public boolean add(Patient patient);
	
	public Patient get(String name);
	
	public int getRowsCount(String name);
	
	public List<Patient> getList(String checkName, PageData pageData);
	
	public boolean delete(int id);

}
