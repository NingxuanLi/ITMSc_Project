package pers.hspt.service.imp;

import pers.hspt.dao.PatientDao;
import pers.hspt.dao.imp.PatientDaoImp;
import pers.hspt.entity.Patient;
import pers.hspt.service.PatientService;

public class PatientServiceImp implements PatientService{
	
	private PatientDao patientDao = new PatientDaoImp();
	
	public boolean add(Patient patient) {
		return patientDao.add(patient);
	}

}
