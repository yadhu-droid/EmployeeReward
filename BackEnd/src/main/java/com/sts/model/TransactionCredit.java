package com.bytes.intern.assessment.model;

import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="transaction_credit_table")
public class TransactionCredit {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column
    private BigInteger numberofPoints;
    
    @Column
    private String award;
    
    @Column
    private String remarks;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDateTime;

	public TransactionCredit(Long transactionId, Employee employee, BigInteger numberofPoints, String award, String remarks,
			Date transactionDateTime) {
		super();
		this.transactionId = transactionId;
		this.employee = employee;
		this.numberofPoints = numberofPoints;
		this.award = award;
		this.remarks = remarks;
		this.transactionDateTime = transactionDateTime;
	}


	public TransactionCredit() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
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


	public Date getTransactionDateTime() {
		return transactionDateTime;
	}


	public void setTransactionDateTime(Date transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
}
