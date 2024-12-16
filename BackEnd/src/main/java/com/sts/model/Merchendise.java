package com.bytes.intern.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="merchendise_table")
public class Merchendise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long merch_id;
	
	private String merch_name;
	private String merch_description;
	private String merch_brand;
	private long merch_cost;
	
	public Merchendise(long merch_id, String merch_name, String merch_description, String merch_brand,
			long merch_cost) {
		super();
		this.merch_id = merch_id;
		this.merch_name = merch_name;
		this.merch_description = merch_description;
		this.merch_brand = merch_brand;
		this.merch_cost = merch_cost;
	}
	
	public long getMerch_id() {
		return merch_id;
	}
	public void setMerch_id(long merch_id) {
		this.merch_id = merch_id;
	}
	public String getMerch_name() {
		return merch_name;
	}
	public void setMerch_name(String merch_name) {
		this.merch_name = merch_name;
	}
	public String getMerch_description() {
		return merch_description;
	}
	public void setMerch_description(String merch_description) {
		this.merch_description = merch_description;
	}
	public String getMerch_brand() {
		return merch_brand;
	}
	public void setMerch_brand(String merch_brand) {
		this.merch_brand = merch_brand;
	}
	public long getMerch_cost() {
		return merch_cost;
	}
	public void setMerch_cost(long merch_cost) {
		this.merch_cost = merch_cost;
	}
	
}
