package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.DoctorDao;
import pers.hspt.dao.imp.DoctorDaoImp;

import pers.hspt.entity.Doctor;
import pers.hspt.service.DoctorService;

public class DoctorServiceImp implements DoctorService{

	private DoctorDao doctorDao=new DoctorDaoImp();

	@Override
	public List<Doctor> getByDepId(int depId) {
		// TODO Auto-generated method stub
		return doctorDao.getByDepId(depId);
	}

}
