package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.DoctorDao;
import pers.hspt.dao.imp.DoctorDaoImp;

import pers.hspt.entity.Doctor;
import pers.hspt.service.DoctorService;
import pers.hspt.util.PageData;

public class DoctorServiceImp implements DoctorService{

	private DoctorDao doctorDao=new DoctorDaoImp();

	@Override
	public List<Doctor> getByDepId(int depId) {
		// TODO Auto-generated method stub
		return doctorDao.getByDepId(depId);
	}
	
	public boolean add(Doctor doctor) {
		return doctorDao.add(doctor);
	}
	
	public int getRowsCount(String name) {
		return doctorDao.getRowsCount(name);
	}
	
	public List<Doctor> getList(String docName, PageData pageData){
		return doctorDao.getList(docName, pageData);
	}
	
	public Doctor get(int docId) {
		return doctorDao.get(docId);
	}
	
	public Doctor get(String name) {
		return doctorDao.get(name);
	}
	
	public boolean delete(int docId) {
		return doctorDao.delete(docId);
	}
	
	public boolean modify(Doctor doctor) {
		return doctorDao.modify(doctor);
	}
	
	public int getRowsCountForOneDep(int depId) {
		return doctorDao.getRowsCountForOneDep(depId);
	}

}
