package pers.hspt.dao;

import pers.hspt.entity.Appointment;

public interface AppointmentDao {
	public Appointment getByDocId(int dodId);
	
	public boolean add(Appointment appointment);

}
