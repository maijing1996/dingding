package com.hxhy.model.po;

import java.util.Date;

import com.hxhy.model.common.BaseEntity;

public class HxhyHoliday extends BaseEntity {

	private Long department_id;//部门id
	private Integer is_use;
	private Integer type;
	private Integer state;
	private String years;
	private String monthy;//月份， 201801
	private String date;//日期，2018-11-11
	private String description;//描述
	private Date add_date;
	
	public Long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}
	public Integer getIs_use() {
		return is_use;
	}
	public void setIs_use(Integer is_use) {
		this.is_use = is_use;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonthy() {
		return monthy;
	}
	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
}
