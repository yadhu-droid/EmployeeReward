package com.bytes.intern.assessment.service;

import java.util.List;

import com.bytes.intern.assessment.dto.EmployeeDto;
import com.bytes.intern.assessment.dto.PasswordDto;
import com.bytes.intern.assessment.model.Employee;

public interface EmployeeService {
	
	public int addEmployee(EmployeeDto employeeDto);

	public List<Employee> getEmployeeList();

	public List<Employee> getBestEmployeeList();

	public long getAwardCount();

	public Employee getEmployeeDetails(Long employeeId);

	public void deleteEmployee(long employeeId);

	public int changePassword(PasswordDto passwordDto);
}
