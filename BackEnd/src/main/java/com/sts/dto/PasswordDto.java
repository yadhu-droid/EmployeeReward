package com.bytes.intern.assessment.dto;

public class PasswordDto {
	private long employeeId;
	private String newPassword;
	private String oldPassword;
	public PasswordDto(String newPassword, long employeeId, String oldPassword) {
		super();
		this.newPassword = newPassword;
		this.employeeId = employeeId;
		this.setOldPassword(oldPassword);
	}
	public PasswordDto() {
		super();
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
