package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Doctor;

public interface DoctorDao {
	
	public List<Doctor> getByDepId(int offId);

}
