package com.mfp.api.daoimpl;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mfp.api.dao.PatientDao;
import com.mfp.api.entity.Patient;

@Repository
@SuppressWarnings({ "deprecation", "unchecked" })
public class PatientDaoIMPL implements PatientDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Patient> findByFirstnameContainingIgnoreCase(String patientName) {
		return null;
	}

	@Override
	public Long getPatientsCount() {
		return null;
	}

	@Override
	public Long getPatientsCountByDate(Date registeredDate) {
		return null;
	}

	@Override
	public List<Patient> getTop5PatientAddedByDate() {
		return null;
	}

	@Override
	public Patient addPatient(Patient patient) {
		return null;
	}

	@Override
	public boolean deletePatientById(String id) {
		return false;
	}

	@Override
	public Patient getPatientById(String id) {
		return null;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		return null;
	}

	@Override
	public List<Patient> getAllPatients() {
		return null;
	}

	@Override
	public List<String> getAllPatientsIds() {
		return null;
	}

}
