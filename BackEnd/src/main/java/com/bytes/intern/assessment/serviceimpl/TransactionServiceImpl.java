package com.bytes.intern.assessment.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.intern.assessment.dao.CartDao;
import com.bytes.intern.assessment.dao.EmployeeDao;
import com.bytes.intern.assessment.dao.ProductDao;
import com.bytes.intern.assessment.dao.TransactionDao;
import com.bytes.intern.assessment.dao.TransactionDebitDao;
import com.bytes.intern.assessment.dto.TransactionDebitDto;
import com.bytes.intern.assessment.dto.TransactionDto;
import com.bytes.intern.assessment.model.Employee;
import com.bytes.intern.assessment.model.Product;
import com.bytes.intern.assessment.model.TransactionCredit;
import com.bytes.intern.assessment.model.TransactionDebit;
import com.bytes.intern.assessment.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	TransactionDebitDao transactionDebitDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CartDao cartDao;
	
	@Override
	public void addTransactionCredit(List<TransactionDto> transactionDtoList) {
		
		for (TransactionDto transactionDto : transactionDtoList) {

            // Fetch the associated Employee entity to know if employee is present or not and also to store that employee in employeeId of transaction (foreign key)
            Employee employee = employeeDao.findById(transactionDto.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + transactionDto.getEmployeeId()));

            // Update the transaction entity
            TransactionCredit transaction = new TransactionCredit();
            transaction.setEmployee(employee);
            transaction.setNumberofPoints(transactionDto.getNumberofPoints());
            transaction.setAward(transactionDto.getAward());
            transaction.setRemarks(transactionDto.getRemarks());
            
         // Get the current date and time
            Date currentDate = new Date();
            transaction.setTransactionDateTime(currentDate);
            
         // Save the transaction to the database
            transactionDao.save(transaction);
            
	        transactionDao.updateSumOfPoints(); //custom DAO that updates sum in employee table
			}
        }

	@Override
	public List<TransactionCredit> getPerformance(long employeeId) {
		return transactionDao.findByEmployeeEmployeeId(employeeId); //here, find by Employee(class)employeeId(field name)
	}
	
	@Transactional
	@Override
	public void addTransactionDebit(TransactionDebitDto transactionDebitDto) {
		
		// Fetch the associated Employee entity to know if employee is present or not and also to store that employee in employeeId of transaction (foreign key)
        Employee employee = employeeDao.findById(transactionDebitDto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + transactionDebitDto.getEmployeeId()));
        
     // Fetch the associated Product entity to know if employee is present or not and also to store that employee in employeeId of transaction (foreign key)
        Product product = productDao.findById(transactionDebitDto.getMerchendiseId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + transactionDebitDto.getMerchendiseId()));
        
        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setEmployee(employee);
        transactionDebit.setProduct(product);
        transactionDebit.setMerchendiseCost(transactionDebitDto.getMerchendiseCost());
        
     // Get the current date and time
        Date currentDate = new Date();
        transactionDebit.setTransactionDateTime(currentDate);
        
     // Save the transaction to the database
        transactionDebitDao.save(transactionDebit);
        
        transactionDebitDao.updatePointDebit();
        
        productDao.updateStatus(transactionDebitDto.getMerchendiseId());
        
	}

	@Override
	public List<TransactionDebit> getMyOrders(long employeeId) {
		return transactionDebitDao.findByEmployeeEmployeeId(employeeId);
	}

	@Override
	public void buyBulkProducts(List<TransactionDebitDto> transactionDebitDtoList) {
		long totalPoints = 0;
		long employeeId = 0;
		for (TransactionDebitDto transactionDebitDto : transactionDebitDtoList) {

            // Fetch the associated Employee entity to know if employee is present or not and also to store that employee in employeeId of transaction (foreign key)
            Employee employee = employeeDao.findById(transactionDebitDto.getEmployeeId())
                    .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + transactionDebitDto.getEmployeeId()));
            Product product = productDao.findById(transactionDebitDto.getMerchendiseId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + transactionDebitDto.getMerchendiseId()));

            // Update the transaction entity
            TransactionDebit transaction = new TransactionDebit();
            transaction.setEmployee(employee);
            transaction.setProduct(product);;
            transaction.setMerchendiseCost(transactionDebitDto.getMerchendiseCost());
            employeeId = transactionDebitDto.getEmployeeId();
            
         // Get the current date and time
            Date currentDate = new Date();
            transaction.setTransactionDateTime(currentDate);
            
         // Save the transaction to the database
            transactionDebitDao.save(transaction);
            
            totalPoints+=transactionDebitDto.getMerchendiseCost();
            
	        cartDao.updateStatusInactive();
			}
		
		employeeDao.updatePoints(totalPoints,employeeId);
	}
}
