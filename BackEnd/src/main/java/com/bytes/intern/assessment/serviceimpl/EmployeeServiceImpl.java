package com.bytes.intern.assessment.serviceimpl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import com.bytes.intern.assessment.dao.EmployeeDao;
import com.bytes.intern.assessment.dao.TransactionDao;
import com.bytes.intern.assessment.dto.EmployeeDto;
import com.bytes.intern.assessment.dto.PasswordDto;
import com.bytes.intern.assessment.model.Employee;
import com.bytes.intern.assessment.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	TransactionDao transactionDao;

	@Override
	public int addEmployee(EmployeeDto employeeDto) {
		
		int bytestroneEmployee = employeeDao.findIfBytestroneEmployee(employeeDto.getEmail());
		boolean employeeAlreadyPresent = employeeDao.existsByEmployeeEmail(employeeDto.getEmail());
		boolean isEmployeeActive = employeeDao.existsByEmployeeEmailAndStatus(employeeDto.getEmail(),"active");
		if(employeeAlreadyPresent && isEmployeeActive) {
			return 2;
		}
		if(bytestroneEmployee==1) {
			Employee employee = new Employee();
			employee.setEmployeeFirstName(employeeDto.getFname());
			employee.setEmployeeLastName(employeeDto.getLname());
			employee.setEmployeeEmail(employeeDto.getEmail());
			employee.setEmployeePhone(employeeDto.getPhone());
			employee.setEmployeeAge(employeeDto.getAge());
			employee.setEmployeePosition(employeeDto.getDesignation());
			employee.setEmployeeGender(employeeDto.getGender());
			employee.setEmployeeTotalPoints(BigInteger.ZERO); //as its a BigInteger, we have to use BigInteger.Zero to make it 0
			employee.setEmployeeRole("Employee"); //since every registered user is an employee, and admin can only be added on the database level, we can hardcode role as employee here
			employee.setEmployeeStatus("active");
			
			String hashedPassword = BCrypt.hashpw(employeeDto.getPassword(), BCrypt.gensalt());
			employee.setEmployeePassword(hashedPassword);
			
			employeeDao.save(employee);
			return 1;
		}
		else {
			return 0;
		}
		
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeDao.findAll();
	}

	@Override
	public List<Employee> getBestEmployeeList() {
		return employeeDao.findAllByOrderByemployeeTotalPointsDesc();
	}

	@Override
	public long getAwardCount() {
		return transactionDao.count();
	}

	@Override
	public Employee getEmployeeDetails(Long employeeId) {
		Employee employee = employeeDao.findByEmployeeId(employeeId);
		return employee;
	}

	@Override
	@Transactional
	public void deleteEmployee(long employeeId) {
		employeeDao.deleteStatusById(employeeId);
	}

	@Modifying
	@Override
	@Transactional
	public int changePassword(PasswordDto passwordDto) {
		
		long employeeId = passwordDto.getEmployeeId();
		
		Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + passwordDto.getEmployeeId()));
		
		String newPassword = passwordDto.getNewPassword();
		String hashedNewPassword = BCrypt.hashpw(passwordDto.getNewPassword(), BCrypt.gensalt());
		
		String oldPassword = passwordDto.getOldPassword();	
		System.out.println(hashedNewPassword);
		String hashedRealPassword = employee.getEmployeePassword();
		boolean hashingSame = BCrypt.checkpw(oldPassword, hashedRealPassword);
		boolean passwordSame = BCrypt.checkpw(newPassword, hashedRealPassword);
		
		if(hashingSame&&passwordSame) {
			return 1;
		}
		else if(hashingSame) {
			employeeDao.changePassword(employeeId,hashedNewPassword);
			return 2;
		}
		else {
			return 0;
		}
		
	}

}

