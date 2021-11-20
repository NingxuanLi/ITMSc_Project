package pers.hspt.service;

import pers.hspt.entity.Appointment;

public interface AppointmentService {
	
	public Appointment getByDocId(int docId);
	
	public boolean add(Appointment appointment);
	
	public Appointment getByPatientId(int id); 

}
