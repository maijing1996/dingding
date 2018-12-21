package com.hxhy.model.dto;

import java.util.Date;

public class AttendanceLeave {
	
	private Long id;
	private String title;
	private String description;
	private Integer amount;
	private Integer date;
	private Integer lower_limit;
	private Integer upper_limit;
	private Integer is_del;
	private String is_pay;
	private Date add_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Integer getLower_limit() {
		return lower_limit;
	}
	public void setLower_limit(Integer lower_limit) {
		this.lower_limit = lower_limit;
	}
	public Integer getUpper_limit() {
		return upper_limit;
	}
	public void setUpper_limit(Integer upper_limit) {
		this.upper_limit = upper_limit;
	}
	public Integer getIs_del() {
		return is_del;
	}
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	public String getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(Integer is_pay) {
		if(is_pay != null) {
			if(is_pay == 1) {
				this.is_pay = "是";
			}else {
				this.is_pay = "否";
			}
		}
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	
}
