package com.bytes.intern.assessment.dto;

import java.math.BigInteger;

import jakarta.persistence.Column;

public class TransactionDto {
    private long employeeId;
    
    @Column(precision=40, scale=0)
    private BigInteger numberofPoints;
    
    private String award;
    private String remarks;
    
	public TransactionDto(long employeeId, BigInteger numberofPoints, String award, String remarks) {
		super();
		this.employeeId = employeeId;
		this.numberofPoints = numberofPoints;
		this.award = award;
		this.remarks = remarks;
	}
	
	public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public BigInteger getNumberofPoints() {
		return numberofPoints;
	}
	public void setNumberofPoints(BigInteger numberofPoints) {
		this.numberofPoints = numberofPoints;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
}
