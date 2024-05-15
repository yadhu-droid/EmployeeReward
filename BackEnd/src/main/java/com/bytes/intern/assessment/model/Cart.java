package com.bytes.intern.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart_table")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	
	@ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "merchendise_id", nullable = false)
    private Product product;
    
    private long merchCost;
    
    private boolean isActive = false;

	public Cart(long cartId, Employee employee, Product product, long merchCost, boolean isActive) {
		super();
		this.cartId = cartId;
		this.employee = employee;
		this.product = product;
		this.merchCost=merchCost;
		this.isActive=isActive;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getMerchCost() {
		return merchCost;
	}

	public void setMerchCost(long merchCost) {
		this.merchCost = merchCost;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
