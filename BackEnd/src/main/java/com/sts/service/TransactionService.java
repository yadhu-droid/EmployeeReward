package com.bytes.intern.assessment.service;

import java.util.List;

import com.bytes.intern.assessment.dto.TransactionDebitDto;
import com.bytes.intern.assessment.dto.TransactionDto;
import com.bytes.intern.assessment.model.TransactionCredit;
import com.bytes.intern.assessment.model.TransactionDebit;

public interface TransactionService {

	public void addTransactionCredit(List<TransactionDto> transactionDtoList);
	
	List<TransactionCredit> getPerformance(long employeeId);

	public void addTransactionDebit(TransactionDebitDto transactionDebitDto);

	public List<TransactionDebit> getMyOrders(long employeeId);

	public void buyBulkProducts(List<TransactionDebitDto> transactionDebitDtoList);

}
