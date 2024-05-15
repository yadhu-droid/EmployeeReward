package com.bytes.intern.assessment.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="product_table")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long merchId;
	
	private String merchName;
	
	@Column(length = 255)
	private String merchDescription;
	
	private String merchCategory;
	private String merchManufacturer;
	private int merchQuantity;
	private byte[] merchImage;
	
	@Column(length = 255)
	private String merchRedemptionConditions;
	
	@Column(length = 255)
	private String merchTermsAndConditions;
	
	private String merchStatus;
	private long merchCost;
	
	@Column
	private LocalDate merchExpiryDate;
	
	@Column
	private boolean ismerchDeleted;
	
	public long getMerchId() {
		return merchId;
	}
	public void setMerchId(long merchId) {
		this.merchId = merchId;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public String getMerchDescription() {
		return merchDescription;
	}
	public void setMerchDescription(String merchDescription) {
		this.merchDescription = merchDescription;
	}
	public String getMerchCategory() {
		return merchCategory;
	}
	public void setMerchCategory(String merchCategory) {
		this.merchCategory = merchCategory;
	}
	public String getMerchManufacturer() {
		return merchManufacturer;
	}
	public void setMerchManufacturer(String merchManufacturer) {
		this.merchManufacturer = merchManufacturer;
	}
	public int getMerchQuantity() {
		return merchQuantity;
	}
	public void setMerchQuantity(int merchQuantity) {
		this.merchQuantity = merchQuantity;
	}
	public byte[] getMerchImagePath() {
		return merchImage;
	}
	public void setMerchImage(byte[] merchImage) {
		this.merchImage = merchImage;
	}
	public String getMerchRedemptionConditions() {
		return merchRedemptionConditions;
	}
	public void setMerchRedemptionConditions(String merchRedemptionConditions) {
		this.merchRedemptionConditions = merchRedemptionConditions;
	}
	public String getMerchTermsAndConditions() {
		return merchTermsAndConditions;
	}
	public void setMerchTermsAndConditions(String merchTermsAndConditions) {
		this.merchTermsAndConditions = merchTermsAndConditions;
	}
	public String getMerchStatus() {
		return merchStatus;
	}
	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}
	public long getMerchCost() {
		return merchCost;
	}
	public void setMerchCost(long merchCost) {
		this.merchCost = merchCost;
	}
	
	public LocalDate getMerchExpiryDate() {
		return merchExpiryDate;
	}
	public void setMerchExpiryDate(LocalDate merchExpiryDate) {
		this.merchExpiryDate = merchExpiryDate;
	}
	public boolean isIsmerchDeleted() {
		return ismerchDeleted;
	}
	public void setIsmerchDeleted(boolean ismerchDeleted) {
		this.ismerchDeleted = ismerchDeleted;
	}
	public byte[] getMerchImage() {
		return merchImage;
	}
	public Product(long merchId, String merchName, String merchDescription, String merchCategory,
			String merchManufacturer, int merchQuantity, byte[] merchImage, String merchRedemptionConditions,
			String merchTermsAndConditions, String merchStatus, long merchCost, LocalDate merchExpiryDate,
			boolean ismerchDeleted) {
		super();
		this.merchId = merchId;
		this.merchName = merchName;
		this.merchDescription = merchDescription;
		this.merchCategory = merchCategory;
		this.merchManufacturer = merchManufacturer;
		this.merchQuantity = merchQuantity;
		this.merchImage = merchImage;
		this.merchRedemptionConditions = merchRedemptionConditions;
		this.merchTermsAndConditions = merchTermsAndConditions;
		this.merchStatus = merchStatus;
		this.merchCost = merchCost;
		this.merchExpiryDate = merchExpiryDate;
		this.ismerchDeleted = ismerchDeleted;
	}
	public Product() {
		super();
	}
	public boolean isMerchDeleted() {
		return ismerchDeleted;
	}
	public void setMerchDeleted(boolean ismerchDeleted) {
		this.ismerchDeleted = ismerchDeleted;
	}
	
	
}
