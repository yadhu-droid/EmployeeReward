package com.bytes.intern.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bytes.intern.assessment.model.TransactionCredit;

@Repository
public interface TransactionDao extends JpaRepository<TransactionCredit, Long> {

	List<TransactionCredit> findByEmployeeEmployeeId(long employeeId);
	
	//query for inputting the sum to table for the employee
	@Modifying
	@Query(value = "UPDATE employee_table SET employee_total_points = COALESCE((SELECT SUM(tc.numberof_points) FROM transaction_credit_table tc WHERE tc.employee_id = employee_table.employee_id), 0);", nativeQuery=true)
	void updateSumOfPoints();
}
