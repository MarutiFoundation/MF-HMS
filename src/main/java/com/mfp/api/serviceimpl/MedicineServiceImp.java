package com.mfp.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfp.api.dao.MedicineDao;
import com.mfp.api.entity.Medicine;
import com.mfp.api.service.MedicineService;

@Service
public class MedicineServiceImp implements MedicineService {

	@Autowired
	private MedicineDao medicineDao;

	@Override
	public Medicine addMedicine(Medicine medicine) {
		return null;
	}

	@Override
	public boolean deleteMedicineById(String id) {
		return false;
	}

	@Override
	public Medicine getMedicineById(String id) {
		return null;
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) {
		return null;
	}

	@Override
	public List<Medicine> getAllMedicine() {
		return null;
	}

	@Override
	public List<Medicine> getMedicinesByName(String medicineName) {
		return null;
	}

	@Override
	public Medicine getMedicineByName(String medicineName) {
		return null;
	}

	@Override
	public List<Medicine> getMedicinesWithQuantityMoreThanZero(int quantity) {
		return null;
	}

	@Override
	public Long getCountOfMedicineByDateAdded(String dateAdded) {
		return null;
	}

	@Override
	public Long getMedicinesTotalCount() {
		return null;
	}

	@Override
	public List<Medicine> getTop5MedicineAddedByDate(String date) {
		return null;
	}

}
