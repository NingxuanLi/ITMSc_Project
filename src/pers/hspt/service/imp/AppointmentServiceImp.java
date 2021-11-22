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
	
	public int getPatientRowsCount(int id) {
		return appointmentDao.getPatientRowsCount(id);
	}
	
	public int getDoctorRowsCount(int id) {
		return appointmentDao.getDoctorRowsCount(id);
	}
	
	public int getArchiveRowsCount() {
		return appointmentDao.getArchiveRowsCount();
	}
	
	public List<Appointment> getList(PageData pageData){
		return appointmentDao.getList(pageData);
	}
	
	public List<Appointment> getArchiveList(PageData pageData){
		return appointmentDao.getArchiveList(pageData);
	}
	
	public void approve(int appId) {
		appointmentDao.approve(appId);
	}
	
	public void disapprove(int appId) {
		appointmentDao.disapprove(appId);
	}
	
	public List<Appointment> getPatientList(int id){
		return appointmentDao.getPatientList(id);
	}
	
	public List<Appointment> getDoctorList(int id, PageData pageData){
		return appointmentDao.getDoctorList(id, pageData);
	}
	
	public void delete(int appId) {
		appointmentDao.delete(appId);
	}
 
}
