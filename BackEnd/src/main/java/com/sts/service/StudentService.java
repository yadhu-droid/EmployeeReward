package com.sts.service;

import com.sts.dto.StudentDto;
import com.sts.model.Student;

public interface StudentService {
	
	public int addStudent(StudentDto studentDto);

	public Student getStudentDetails(Long studentId);
}
