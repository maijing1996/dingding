package com.hxhy.model.dto;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class SupplementAttendance {
	
	private Long id;
	private String monthy;
	private String work_date;
	private String user_check_time;//打卡时间
	private String name;//部门
	private String nickname;//员工姓名
	private Integer is_audit;//管理员审核
	
	public Integer getIs_audit() {
		return is_audit;
	}
	public void setIs_audit(Integer is_audit) {
		this.is_audit = is_audit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonthy() {
		return monthy;
	}
	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	public String getUser_check_time() {
		return user_check_time;
	}
	public void setUser_check_time(Date user_check_time) {
		if(user_check_time != null) {
			this.user_check_time = DateUtil.format(user_check_time, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
