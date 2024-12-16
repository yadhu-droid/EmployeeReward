package com.bytes.intern.assessment.dto;

public class TransactionDebitDto {
	private long employeeId;
	private long merchendiseCost;
	private long merchendiseId;
	public TransactionDebitDto(long employeeId, long merchendiseCost, long merchendiseId) {
		super();
		this.employeeId = employeeId;
		this.merchendiseCost = merchendiseCost;
		this.merchendiseId = merchendiseId;
	}
	public TransactionDebitDto() {
		super();
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getMerchendiseCost() {
		return merchendiseCost;
	}
	public void setMerchendiseCost(long merchendiseCost) {
		this.merchendiseCost = merchendiseCost;
	}
	public long getMerchendiseId() {
		return merchendiseId;
	}
	public void setMerchendiseId(long merchendiseId) {
		this.merchendiseId = merchendiseId;
	}
	
	
}
