package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Patient;
import pers.hspt.util.PageData;

public interface PatientService {
	
	public boolean add(Patient patient);
	
	public Patient get(String name);
	
	public int getRowsCount(String checkName);
	
	public List<Patient> getList(String checkName, PageData pageData);
	
	public boolean delete(int id);

}
