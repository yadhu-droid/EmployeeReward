package com.bytes.intern.assessment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="achievement_table")
public class Achievement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long achievement_id;
	
	private String achievement_name;
	private String achievement_desc;
	public Achievement(long achievement_id, String achievement_name, String achievement_desc) {
		super();
		this.achievement_id = achievement_id;
		this.achievement_name = achievement_name;
		this.achievement_desc = achievement_desc;
	}
	
	public long getAchievement_id() {
		return achievement_id;
	}
	public void setAchievement_id(long achievement_id) {
		this.achievement_id = achievement_id;
	}
	public String getAchievement_name() {
		return achievement_name;
	}
	public void setAchievement_name(String achievement_name) {
		this.achievement_name = achievement_name;
	}
	public String getAchievement_desc() {
		return achievement_desc;
	}
	public void setAchievement_desc(String achievement_desc) {
		this.achievement_desc = achievement_desc;
	}
	
}
