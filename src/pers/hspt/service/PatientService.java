package pers.hspt.service;

import pers.hspt.entity.Patient;

public interface PatientService {
	
	public boolean add(Patient patient);
	
	public Patient get(String name);

}
