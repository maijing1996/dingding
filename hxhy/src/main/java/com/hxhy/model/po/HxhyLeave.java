package com.hxhy.model.po;

import java.util.Date;

import com.hxhy.model.common.BaseEntity;

public class HxhyLeave extends BaseEntity {

	private String title;
	private String description;
	private Integer amount;
	private Integer date;
	private Integer lower_limit;
	private Integer upper_limit;
	private Integer is_del;
	private Integer is_pay;
	private Date add_date;
	
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
	public Integer getIs_pay() {
		return is_pay;
	}
	public void setIs_pay(Integer is_pay) {
		this.is_pay = is_pay;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
}
