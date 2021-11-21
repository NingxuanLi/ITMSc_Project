package pers.hspt.dao;

import java.util.List;

import pers.hspt.entity.Appointment;
import pers.hspt.util.PageData;

public interface AppointmentDao {
	public Appointment getByDocId(int dodId);
	
	public boolean add(Appointment appointment);
	
	public Appointment getByPatientId(int id);
	
	public int getRowsCount();
	
	public List<Appointment> getList(PageData pageData);
	
	public void approve(int appId);
	
	public void disapprove(int appId);
	
	public List<Appointment> getPatientList(int id);
	
	public List<Appointment> getDoctorList(int id);

}
