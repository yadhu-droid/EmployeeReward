package com.sts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.dto.StudentDto;
import com.sts.dto.LoginDto;
import com.sts.model.Student;
import com.sts.service.StudentService;
import com.sts.service.LoginService;

@RestController
@RequestMapping(value="/employee")
@CrossOrigin(origins = "*")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/add") 
	public int addEmployee(@RequestBody StudentDto studentDto) {
		return studentService.addStudent(studentDto);
	}
	
	@PostMapping("/login") 
	public Student validateEmployee(@RequestBody LoginDto loginDto) {
		return loginService.validateStudent(loginDto);
	}
	
	@GetMapping("/getdetails/{employeeId}")
	public Student getStudentDetails(@PathVariable long employeeId) {
	    return studentService.getStudentDetails(employeeId);
	}
	
}
