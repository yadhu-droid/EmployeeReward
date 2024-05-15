package com.bytes.intern.assessment.serviceimpl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.intern.assessment.dao.EmployeeDao;
import com.bytes.intern.assessment.dto.LoginDto;
import com.bytes.intern.assessment.model.Employee;
import com.bytes.intern.assessment.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public Employee validateEmployee(LoginDto loginDto) {
		
		Employee employee = employeeDao.findByEmployeeEmail(loginDto.getEmail());
		String dtoPassword = loginDto.getPassword(); 
		String hashedRealPassword = employee.getEmployeePassword();
		boolean passwordSame = BCrypt.checkpw(dtoPassword, hashedRealPassword);
		
		String status = employee.getEmployeeStatus();

        if (employee != null && passwordSame && status.equals("active")) {
            return employee;
        } else {
            return null;
        }

	}

}
