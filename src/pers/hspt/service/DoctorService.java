package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Doctor;

public interface DoctorService {
	
	public List<Doctor> getByDepId(int depid);

}
