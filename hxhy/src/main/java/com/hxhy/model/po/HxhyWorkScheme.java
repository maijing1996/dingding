package com.hxhy.model.po;

import java.util.Date;

import com.hxhy.model.common.BaseEntity;

public class HxhyWorkScheme extends BaseEntity {

	private Integer type;
	private String user_id;
	private Long department_id;
	private String title;
	private String description;
	private String on_duty;
	private String off_duty;
	private Integer is_del;
	private Date add_date;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
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
	public String getOn_duty() {
		return on_duty;
	}
	public void setOn_duty(String on_duty) {
		this.on_duty = on_duty;
	}
	public String getOff_duty() {
		return off_duty;
	}
	public void setOff_duty(String off_duty) {
		this.off_duty = off_duty;
	}
	public Integer getIs_del() {
		return is_del;
	}
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
}
