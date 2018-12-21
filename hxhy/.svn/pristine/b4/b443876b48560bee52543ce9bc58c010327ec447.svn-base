package com.hxhy.model.dto;

public class Holiday {

	private Long id;
	private String type;
	private String is_use;
	private String years;
	private String monthy;
	private String date;
	private String description;
	private String name = "全体部门";
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIs_use() {
		return is_use;
	}
	public void setIs_use(Integer is_use) {
		if(is_use == null) {
			
		} else if(is_use == 1) {
			this.is_use = "是";
		} else if(is_use == 0) {
			this.is_use = "否";
		}
	}
	public String getType() {
		return type;
	}
	public void setType(Integer type) {
		if(type == null) {
			
		} else if(type == 1) {
			this.type = "个体员工";
		} else if(type == 0) {
			this.type = "全体员工";
		}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
