package com.mfp.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfp.api.dao.MedicineDistributorDao;
import com.mfp.api.entity.MedicineDistributor;
import com.mfp.api.service.MedicineDistributorService;

@Service
public class MedicineDistributorServiceImp implements MedicineDistributorService {

	@Autowired
	private MedicineDistributorDao medicineDistributorDao;
	
	@Override
	public MedicineDistributor addMedicineDistributor(MedicineDistributor medicineDistributor) {
		return null;
	}

	@Override
	public boolean deleteMedicineDistributorById(String id) {
		return false;
	}

	@Override
	public MedicineDistributor getMedicineDistributorById(String id) {
		return null;
	}

	@Override
	public MedicineDistributor updateMedicineDistributor(MedicineDistributor medicineDistributor) {
		return null;
	}

	@Override
	public List<MedicineDistributor> getAllDistributors() {
		return null;
	}

	@Override
	public List<MedicineDistributor> getDistributorsByName(String distributorName) {
		return null;
	}

	@Override
	public MedicineDistributor getDistributorByName(String distributorName) {
		return null;
	}

	@Override
	public Long getCountByRegisteredDate(String registeredDate) {
		return null;
	}

	@Override
	public List<MedicineDistributor> getTop5CompanyAddedByDate(String date) {
		return null;
	}

}
