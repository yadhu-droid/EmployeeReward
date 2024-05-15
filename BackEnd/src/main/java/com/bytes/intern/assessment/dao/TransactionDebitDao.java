package com.bytes.intern.assessment.dao;

import org.springframework.stereotype.Repository;

import com.bytes.intern.assessment.model.TransactionDebit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TransactionDebitDao extends JpaRepository<TransactionDebit, Long> {
	
	//query for inputting the sum of debit points to debit column for the employee
	@Modifying
	@Query(value = "UPDATE employee_table SET employee_total_debit_points = COALESCE( ( SELECT SUM(transaction_debit_table.merchendise_cost) FROM transaction_debit_table WHERE transaction_debit_table.employee_id = employee_table.employee_id), 0)", nativeQuery=true)
	//if the subquery returns NULL, the COALESCE function will replace it with the value 0
	void updatePointDebit();

	List<TransactionDebit> findByEmployeeEmployeeId(long employeeId);

}
