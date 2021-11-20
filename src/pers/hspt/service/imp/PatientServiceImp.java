package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.PatientDao;
import pers.hspt.dao.imp.PatientDaoImp;
import pers.hspt.entity.Patient;
import pers.hspt.service.PatientService;
import pers.hspt.util.PageData;

public class PatientServiceImp implements PatientService{
	
	private PatientDao patientDao = new PatientDaoImp();
	
	public boolean add(Patient patient) {
		return patientDao.add(patient);
	}
	
	public Patient get(String name) {
		return patientDao.get(name);
	}
	
	public int getRowsCount(String checkName) {
		return patientDao.getRowsCount(checkName);
	}
	
	public List<Patient> getList(String checkName, PageData pageData){
		return patientDao.getList(checkName, pageData);
	}
	
	public boolean delete(int id) {
		return patientDao.delete(id);
	}

}
