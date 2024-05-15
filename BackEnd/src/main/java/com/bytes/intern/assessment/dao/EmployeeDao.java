package com.bytes.intern.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bytes.intern.assessment.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

	Employee findByEmployeeEmail(String email);
	
	@Query(value="SELECT * FROM employee_table order by employee_total_points desc",nativeQuery = true)
	List<Employee> findAllByOrderByemployeeTotalPointsDesc();

	Employee findByEmployeeId(Long employeeId);

	@Query(value="SELECT COUNT(*) FROM company_table WHERE employee_email =:email",nativeQuery = true)
	int findIfBytestroneEmployee(String email);

	boolean existsByEmployeeEmail(String email);
	
	@Modifying
	@Query(value="UPDATE employee_table SET status='inactive' where employee_id=:employeeId",nativeQuery = true)
	void deleteStatusById(long employeeId);

	boolean existsByEmployeeEmailAndStatus(String email, String string);

	@Modifying
	@Query(value="UPDATE employee_table SET employee_total_debit_points=employee_total_debit_points+:totalPoints where employee_id=:employeeId",nativeQuery=true)
	void updatePoints(long totalPoints, long employeeId);

	@Modifying
	@Query(value="UPDATE employee_table SET employee_password =:newPassword where employee_id =:employeeId", nativeQuery=true)
	void changePassword(long employeeId, String newPassword);

}
