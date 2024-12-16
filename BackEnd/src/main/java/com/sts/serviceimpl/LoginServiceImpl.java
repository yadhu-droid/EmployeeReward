package com.sts.serviceimpl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.dao.StudentDao;
import com.sts.dto.LoginDto;
import com.sts.model.Student;
import com.sts.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	StudentDao studentDao;
	
	@Override
	public Student validateStudent(LoginDto loginDto) {
		
		Student student = studentDao.findByEmployeeEmail(loginDto.getEmail());
		String dtoPassword = loginDto.getPassword(); 
		String hashedRealPassword = student.getEmployeePassword();
		boolean passwordSame = BCrypt.checkpw(dtoPassword, hashedRealPassword);
		
		String status = student.getEmployeeStatus();

        if (student != null && passwordSame && status.equals("active")) {
            return student;
        } else {
            return null;
        }

	}

}
