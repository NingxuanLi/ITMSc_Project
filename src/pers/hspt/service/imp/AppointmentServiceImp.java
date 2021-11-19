package pers.hspt.service.imp;

import pers.hspt.dao.AppointmentDao;
import pers.hspt.entity.Appointment;
import pers.hspt.service.AppointmentService;
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
 
}
