package com.mfp.api.serviceimpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfp.api.dao.AppointmentDao;
import com.mfp.api.entity.Appointment;
import com.mfp.api.service.AppointmentService;

@Service()
public class AppointmentServiceImp implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public Appointment addAppointment(Appointment appointment) {

		// Creating Appointment taken time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		// Creating AppointmentPatientId (17 DIGIT)
		//String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		long id = System.currentTimeMillis();
		
		//Creating Appointment taken date
		Date date = Date.valueOf(LocalDate.now());

		appointment.setAppointmentpatientid(Long.toString(id));
		appointment.setAppointmenttakentime(dtf.format(now));
		appointment.setAppointmenttakendate(date);

		return appointmentDao.addAppointment(appointment);
	}
		
	@Override
	public Appointment updateAppointment(Appointment appointment) {
		return appointmentDao.updateAppointment(appointment);
	
	}

	@Override
	public Appointment getAppointmentById(String appointmentId) {
		return appointmentDao.getAppointmentById(appointmentId);
	}

	@Override
	public List<Appointment> getAppointmentsByPatientsIds(List<String> patientsId) {
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByDoctorIdAndAppointmentDate(String doctorId, Date appointmentDate) {
		return appointmentDao.getAppointmentsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
		
	}

	@Override
	public List<Appointment> getAppointmentsByDoctorIdAndAppointmentDate(String doctorId, Date appointmentDate,
			String appointmentTime) {
		return appointmentDao.getAppointmentsByDoctorIdAndAppointmentDate(doctorId, appointmentDate, appointmentTime);
	
	}

	@Override
	public List<Appointment> getAppointmentsByDate(Date date) {
		return this.appointmentDao.getAppointmentsByDate(date);
	}

	@Override
	public Long getCountByAppointmentDate(Date appointmentDate) {
		return appointmentDao.getCountByAppointmentDate(appointmentDate);
	}

	@Override
	public List<Appointment> getAppointmentsByBillingDate(Date billingDate) {
		
		return appointmentDao.getAppointmentsByBillingDate(billingDate);
	}

	@Override
	public Long getAppointmentsTotalCount() {
		return appointmentDao.getAppointmentsTotalCount();
	}

	@Override
	public Long getCountByAppointmentTakenDate(Date appointmentTakenDate) {
		return appointmentDao.getCountByAppointmentTakenDate(appointmentTakenDate);
	}

	@Override
	public Long getCountByTreatmentStatusAndBillingDate(String treatmentStatus, Date billingDate) {
		return appointmentDao.getCountByTreatmentStatusAndBillingDate(treatmentStatus, billingDate);
		
	}

	@Override
	public List<Appointment> getAllAppointments() {
		 return this.appointmentDao.getAllAppointments();
	}
	

	@Override
	public List<Appointment> getTop5AppointmentsByDate(Date date) {
		
		 return this.appointmentDao.getTop5AppointmentsByDate(date);
		
	}

	@Override
	public Appointment getAppointmentByDoctorId(String dId) {
		return this.appointmentDao.getAppointmentByDoctorId(dId);
	}

}
