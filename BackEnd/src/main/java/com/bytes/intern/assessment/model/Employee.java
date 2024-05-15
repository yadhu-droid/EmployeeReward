package com.bytes.intern.assessment.model;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_table")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	
	private String employeeFirstName;
	
	private String employeeLastName;
	private int employeeAge;
	private String employeeGender;
	private long employeePhone;
	private String employeeEmail;
	private String employeePosition;
	private String employeePassword;
	private String employeeRole;
	private String status;
	
	@Column
	private BigInteger employeeTotalPoints; //Big Integer to accomodate very large number of values like 300000000000000000
	
	private long employeeTotalDebitPoints;
	
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public Employee() {
		super();
	}
	
	public Employee(long employeeId, String employeeFirstName, String employeeLastName, int employeeAge,
			String employeeGender, long employeePhone, String employeeEmail, String employeePosition,
		    String employeePassword, String employeeRole, BigInteger employeeTotalPoints,
			long employeeTotalDebitPoints,String status) {
		super();
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeeAge = employeeAge;
		this.employeeGender = employeeGender;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.employeePosition = employeePosition;
		this.employeePassword = employeePassword;
		this.employeeRole = employeeRole;
		this.employeeTotalPoints = employeeTotalPoints;
		this.employeeTotalDebitPoints = employeeTotalDebitPoints;
		this.setEmployeeStatus(status);
	}
	public long getEmployeeTotalDebitPoints() {
		return employeeTotalDebitPoints;
	}
	public void setEmployeeTotalDebitPoints(long employeeTotalDebitPoints) {
		this.employeeTotalDebitPoints = employeeTotalDebitPoints;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public int getEmployeeAge() {
		return employeeAge;
	}
	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}
	public String getEmployeeGender() {
		return employeeGender;
	}
	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}
	public long getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeePhone(long employeePhone) {
		this.employeePhone = employeePhone;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeePosition() {
		return employeePosition;
	}
	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public BigInteger getEmployeeTotalPoints() {
		return employeeTotalPoints;
	}
	public void setEmployeeTotalPoints(BigInteger employeeTotalPoints) {
		this.employeeTotalPoints = employeeTotalPoints;
	}
	public String getEmployeeStatus() {
		return status;
	}
	public void setEmployeeStatus(String status) {
		this.status = status;
	}
	
}
