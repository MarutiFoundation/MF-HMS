package com.mfp.api.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mfp.api.dao.MedicineCompanyDao;
import com.mfp.api.entity.MedicineCompany;

@Repository
public class MedicineCompanyDaoIMPL implements MedicineCompanyDao {
	@Autowired
	private SessionFactory sf;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MedicineCompany> findByNameContainingIgnoreCase(String companyName) {
		return null;
	}

	@Override
	public Long countByRegisterdate(String dateAdded) {
		return null;
	}

	@Override
	public List<MedicineCompany> findTop5ByIdDesc(String date) {
		return null;
	}

	@Override
	public MedicineCompany addMedicineCompany(MedicineCompany medicineCompany) {
		return null;
	}

	@Override
	public boolean deleteMedicineCompanyById(String id) {
		return false;
	}

	@Override
	public MedicineCompany getMedicineCompanyById(String id) {
		return null;
	}

	@Override
	public MedicineCompany updateMedicineCompany(MedicineCompany medicineCompany) {
		return null;
	}

	@Override
	public List<MedicineCompany> getAllMedicineCompanys() {
		return null;
	}

	@Override
	public MedicineCompany findByName(String companyName) {
		return null;
	}

}
