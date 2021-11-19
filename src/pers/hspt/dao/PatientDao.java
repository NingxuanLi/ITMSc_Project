package pers.hspt.dao;

import pers.hspt.entity.Patient;

public interface PatientDao {
	
	public boolean add(Patient patient);
	
	public Patient get(String name);

}
