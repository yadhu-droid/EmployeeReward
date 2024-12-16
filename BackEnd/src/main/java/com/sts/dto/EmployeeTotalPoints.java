package com.bytes.intern.assessment.dto;

public class EmployeeTotalPoints {
	private String employeeFirstName;
	private String employeeLastName;
	private long employeeTotalPoints;
	
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
	
	public EmployeeTotalPoints(String employeeFirstName, String employeeLastName, long employeeTotalPoints) {
		super();
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeeTotalPoints = employeeTotalPoints;
	}
	public long getEmployeeTotalPoints() {
		return employeeTotalPoints;
	}
	public void setEmployeeTotalPoints(long employeeTotalPoints) {
		this.employeeTotalPoints = employeeTotalPoints;
	}
	public EmployeeTotalPoints() {
		super();
	}
	
}
