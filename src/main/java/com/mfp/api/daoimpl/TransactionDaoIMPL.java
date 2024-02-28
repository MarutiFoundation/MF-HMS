package com.mfp.api.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mfp.api.dao.TransactionDao;
import com.mfp.api.entity.TransactionDetails;

@Repository
public class TransactionDaoIMPL implements TransactionDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public int generateSalaryForUser(TransactionDetails transactionDetails) {
		return 0;
	}

	@Override
	public TransactionDetails getTransactionDetails(String transactionId) {
		return null;
	}
	
	@Override
	public List<TransactionDetails> getTransactionDetails(String username, int from, int to) {
		return null;
	}

}
