package pers.hspt.service.imp;

import java.util.List;

import pers.hspt.dao.AppointmentDao;
import pers.hspt.entity.Appointment;
import pers.hspt.service.AppointmentService;
import pers.hspt.util.PageData;
import pers.hspt.dao.imp.AppointmentDaoImp;

public class AppointmentServiceImp implements AppointmentService{
	
	private AppointmentDao appointmentDao = new AppointmentDaoImp();

	@Override
	public Appointment getByDocId(int docId) {
		return appointmentDao.getByDocId(docId);
	}
	
	public boolean add(Appointment appointment) {
		return appointmentDao.add(appointment);
	}
	
	public Appointment getByPatientId(int id) {
		return appointmentDao.getByPatientId(id);
	}
	
	public int getRowsCount() {
		return appointmentDao.getRowsCount();
	}
	
	public List<Appointment> getList(PageData pageData){
		return appointmentDao.getList(pageData);
	}
	
	public void approve(int appId) {
		appointmentDao.approve(appId);
	}
	
	public void disapprove(int appId) {
		appointmentDao.disapprove(appId);
	}
	
	public List<Appointment> getPersonalList(int id){
		return appointmentDao.getPersonalList(id);
	}
 
}
