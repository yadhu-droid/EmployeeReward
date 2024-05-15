package com.bytes.intern.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.intern.assessment.dto.EmployeeDto;
import com.bytes.intern.assessment.dto.LoginDto;
import com.bytes.intern.assessment.dto.PasswordDto;
import com.bytes.intern.assessment.dto.TransactionDto;
import com.bytes.intern.assessment.model.Employee;
import com.bytes.intern.assessment.model.ResponseHandler;
import com.bytes.intern.assessment.model.TransactionCredit;
import com.bytes.intern.assessment.model.TransactionDebit;
import com.bytes.intern.assessment.service.LoginService;
import com.bytes.intern.assessment.service.TransactionService;
import com.bytes.intern.assessment.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/add") 
	public int addEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.addEmployee(employeeDto);
	}
	
	@PostMapping("/login") 
	public Employee validateEmployee(@RequestBody LoginDto loginDto) {
		return loginService.validateEmployee(loginDto);
	}
	
	@PostMapping("/addpoints")
	public ResponseEntity<Object> addTransactionCredit(@RequestBody List<TransactionDto> transactionDtoList) { //its a list because of bulk allocations
		transactionService.addTransactionCredit(transactionDtoList);
		 return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, null);
	}
	
	@PostMapping("/changepassword")
	public int ChangePassword(@RequestBody PasswordDto passwordDto){
		return employeeService.changePassword(passwordDto);
	}
	
	@GetMapping("/getemployees")
	public List<Employee> getEmployeeList(){
		return employeeService.getEmployeeList();
	}
	
	@GetMapping("/getperformance/{employeeId}")
	public List<TransactionCredit> getPerformance(@PathVariable long employeeId){
		return transactionService.getPerformance(employeeId);
	}
	
	@GetMapping("/getdetails/{employeeId}")
	public Employee getEmployeeDetails(@PathVariable long employeeId) {
	    return employeeService.getEmployeeDetails(employeeId);
	}
	
	@GetMapping("/getbestemployees")
	public List<Employee> getBestEmployeesList(){
		return employeeService.getBestEmployeeList();
	}
	
	@GetMapping("/getmyorders/{employeeId}")
	public List<TransactionDebit> getMyOrders(@PathVariable long employeeId){
		return transactionService.getMyOrders(employeeId);
	}
	
	@GetMapping("/getawardcount")
	public long getAwardCount(){
		return employeeService.getAwardCount();
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable long employeeId) { 
		employeeService.deleteEmployee(employeeId);
		 return ResponseHandler.generateResponse("Successfully deleted Employee!", HttpStatus.OK, null);
	}
	
}
