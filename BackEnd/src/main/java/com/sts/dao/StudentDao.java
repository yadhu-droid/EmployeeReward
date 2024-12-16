package com.sts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sts.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

	Student findByEmployeeEmail(String email);

	Student findByEmployeeId(Long employeeId);

	boolean existsByEmployeeEmail(String email);
	
	boolean existsByEmployeeEmailAndStatus(String email, String string);

}
