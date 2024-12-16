package com.bytes.intern.assessment.dto;

import java.time.LocalDate;

public class ProductDto {
	private String merchName;
	private String merchCategory;
	private String merchDescription;
	private String merchManufacturer;
	private int merchQuantity;
	private long merchPoints;
	private String base64Image;
	private String merchRedemptionConditions;
	private String merchTermsAndConditions;
	private String merchStatus;
	private LocalDate merchExpiryDate;
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public String getMerchCategory() {
		return merchCategory;
	}
	public void setMerchCategory(String merchCategory) {
		this.merchCategory = merchCategory;
	}
	public String getMerchDescription() {
		return merchDescription;
	}
	public void setMerchDescription(String merchDescription) {
		this.merchDescription = merchDescription;
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
	
	public ProductDto(String merchName, String merchCategory, String merchDescription, String merchManufacturer,
			int merchQuantity, long merchPoints, String base64Image, String merchRedemptionConditions,
			String merchTermsAndConditions, String merchStatus, LocalDate merchExpiryDate) {
		super();
		this.merchName = merchName;
		this.merchCategory = merchCategory;
		this.merchDescription = merchDescription;
		this.merchManufacturer = merchManufacturer;
		this.merchQuantity = merchQuantity;
		this.merchPoints = merchPoints;
		this.base64Image = base64Image;
		this.merchRedemptionConditions = merchRedemptionConditions;
		this.merchTermsAndConditions = merchTermsAndConditions;
		this.merchStatus = merchStatus;
		this.merchExpiryDate = merchExpiryDate;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	public void setMerchQuantity(int merchQuantity) {
		this.merchQuantity = merchQuantity;
	}
	public long getMerchPoints() {
		return merchPoints;
	}
	public void setMerchPoints(long merchPoints) {
		this.merchPoints = merchPoints;
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
	public ProductDto() {
		super();
	}
	public String getMerchStatus() {
		return merchStatus;
	}
	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}
	@Override
	public String toString() {
		return "ProductDto [base64Image=" + base64Image + "]";
	}
	public LocalDate getMerchExpiryDate() {
		return merchExpiryDate;
	}
	public void setMerchExpiryDate(LocalDate merchExpiryDate) {
		this.merchExpiryDate = merchExpiryDate;
	}
	
}
