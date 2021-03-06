package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Appointment;
import pers.hspt.util.PageData;

public interface AppointmentService {
	
	public Appointment getByDocId(int docId);
	
	public Appointment getByPatientId(int id); 
	
	public boolean add(Appointment appointment);
	
	public void delete(int appId);
	
	public int getRowsCount();
	
	public int getArchiveRowsCount();
	
	public int getPatientRowsCount(int id);
	
	public int getDoctorRowsCount(int id);
	
	public List<Appointment> getList(PageData pageData);
	
	public List<Appointment> getArchiveList(PageData pageData);
	
	public List<Appointment> getPatientList(int id);
	
	public List<Appointment> getDoctorList(int id, PageData pageData);
	
	public void approve(int appId);
	
	public void disapprove(int appId);
	
	
}
