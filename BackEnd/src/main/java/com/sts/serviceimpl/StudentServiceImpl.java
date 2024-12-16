package com.sts.serviceimpl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import com.sts.dao.StudentDao;
import com.sts.dto.StudentDto;
import com.sts.model.Student;
import com.sts.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDao studentDao;
	
	@Override
	public int addStudent(StudentDto studentDto) {

		boolean employeeAlreadyPresent = studentDao.existsByEmployeeEmail(studentDto.getEmail());
		boolean isEmployeeActive = studentDao.existsByEmployeeEmailAndStatus(studentDto.getEmail(),"active");
		if(employeeAlreadyPresent && isEmployeeActive) {
			return 2;
		}
			Student student = new Student();
			student.setEmployeeFirstName(studentDto.getFname());
			student.setEmployeeLastName(studentDto.getLname());
			student.setEmployeeEmail(studentDto.getEmail());
			student.setEmployeePhone(studentDto.getPhone());
			student.setEmployeeAge(studentDto.getAge());
			student.setEmployeePosition(studentDto.getDesignation());
			student.setEmployeeGender(studentDto.getGender());
			student.setEmployeeTotalPoints(BigInteger.ZERO); //as its a BigInteger, we have to use BigInteger.Zero to make it 0
			student.setEmployeeRole("Employee"); //since every registered user is an employee, and admin can only be added on the database level, we can hardcode role as employee here
			student.setEmployeeStatus("active");
			
			String hashedPassword = BCrypt.hashpw(studentDto.getPassword(), BCrypt.gensalt());
			student.setEmployeePassword(hashedPassword);
			
			studentDao.save(student);
			return 1;
	}

	@Override
	public Student getStudentDetails(Long employeeId) {
		Student student = studentDao.findByEmployeeId(employeeId);
		return student;
	}

}

