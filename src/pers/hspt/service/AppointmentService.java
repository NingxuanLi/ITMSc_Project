package pers.hspt.service;

import java.util.List;

import pers.hspt.entity.Appointment;
import pers.hspt.util.PageData;

public interface AppointmentService {
	
	public Appointment getByDocId(int docId);
	
	public boolean add(Appointment appointment);
	
	public Appointment getByPatientId(int id); 
	
	public int getRowsCount();
	
	public List<Appointment> getList(PageData pageData);
	
	public void approve(int appId);
	
	public void disapprove(int appId);
	
	public List<Appointment> getPersonalList(int id);
}
