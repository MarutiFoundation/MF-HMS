package com.mfp.api.serviceimpl;

import java.sql.Date; 
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfp.api.dao.PatientDao;
import com.mfp.api.entity.Patient;
import com.mfp.api.service.PatientService;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientDao patientDao;

	@Override
	public Patient addPatient(Patient patient) {
		Date date = Date.valueOf(LocalDate.now());
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		patient.setId(id);
		patient.setRegisterDate(date);

		Patient addedPatient = patientDao.addPatient(patient);
		System.out.println(addedPatient.getRegisterDate());
		return addedPatient;
	}

	@Override
	public boolean deletePatientById(String id) {
		return patientDao.deletePatientById(id);
	}

	@Override
	public Patient getPatientById(String id) {
		Patient patient = patientDao.getPatientById(id);
		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		return patientDao.updatePatient(patient);
	}

	@Override
	public List<Patient> findByFirstnameContainingIgnoreCase(String patientname) {
		return patientDao.findByFirstnameContainingIgnoreCase(patientname);
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}

	@Override
	public Long getPatientsCount() {
		return patientDao.getPatientsCount();
	}

	@Override
	public Long getPatientsCountByDate(Date registerDate) {
		return patientDao.getPatientsCountByDate(registerDate);
	}

	@Override
	public List<Patient> getTop5PatientAddedByDate() {
		return patientDao.getTop5PatientAddedByDate();
	}

	@Override
	public List<String> getAllPatientsIds() {
		return patientDao.getAllPatientsIds();
	}

}
