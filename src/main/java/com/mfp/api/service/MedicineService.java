package com.mfp.api.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mfp.api.entity.Medicine;

@Transactional
public interface MedicineService {
	Medicine addMedicine(Medicine medicine);
	boolean deleteMedicineById(String id);
	Medicine getMedicineById(String id);
	Medicine updateMedicine(Medicine medicine);
	List<Medicine> getAllMedicine();
	List<Medicine> getMedicinesByName(String medicineName);
	Medicine getMedicineByName(String medicineName);
	List<Medicine> getMedicinesWithQuantityMoreThanZero(int quantity);
	Long getCountOfMedicineByDateAdded(String dateAdded);
    Long getMedicinesTotalCount();
    List<Medicine> getTop5MedicineAddedByDate(String date);
}
