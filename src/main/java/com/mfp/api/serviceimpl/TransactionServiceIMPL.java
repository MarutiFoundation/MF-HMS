package com.mfp.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfp.api.dao.TransactionDao;
import com.mfp.api.entity.TransactionDetails;
import com.mfp.api.service.TransactionService;

@Service
public class TransactionServiceIMPL implements TransactionService {

	@Autowired
	private TransactionDao dao;

	@Override
	public int generateSalaryForUser(TransactionDetails transactionDetails) {
		return 0;

	}

	@Override
	public String generateSalaryreportForUser(String username, int from, int to) {
		return null;

	}

}
