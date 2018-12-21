package com.hxhy.model.po;

import java.util.Date;

import com.hxhy.model.common.BaseEntity;

public class HxhySupplementAttendance extends BaseEntity {

	private String user_id;
	private Long attendance_id;
	private Integer is_audit;
	private Date add_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(Long attendance_id) {
		this.attendance_id = attendance_id;
	}
	public Integer getIs_audit() {
		return is_audit;
	}
	public void setIs_audit(Integer is_audit) {
		this.is_audit = is_audit;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
}
