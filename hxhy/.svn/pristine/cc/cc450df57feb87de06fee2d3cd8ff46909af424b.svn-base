package com.hxhy.model.dto;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class AttendanceLeaveRecord {
	
	private Long id;
	private String name;//姓名
	private String type;//请假类型
	private String monthy;//月份
	private String start_date;//开始时间
	private String end_date;//结束时间
	private Integer longtime;//请假时长（分钟）
	private String manager_check;//主管审核
	private String boss_check;//boss审核
	private String add_date;//申请日期
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMonthy() {
		return monthy;
	}
	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		if(start_date != null) {
			this.start_date = DateUtil.format(start_date, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		if(end_date != null) {
			this.end_date = DateUtil.format(end_date, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}
	}
	public Integer getLongtime() {
		return longtime;
	}
	public void setLongtime(Integer longtime) {
		this.longtime = longtime;
	}
	public String getManager_check() {
		return manager_check;
	}
	public void setManager_check(Integer manager_check) {
		if(manager_check != null) {
			if(manager_check == 0) {
				this.manager_check = "审核中";
			}else if(manager_check == 1) {
				this.manager_check = "通过";
			}else {
				this.manager_check = "未通过";
			}
		}
	}
	public String getBoss_check() {
		return boss_check;
	}
	public void setBoss_check(Integer boss_check) {
		if(boss_check != null) {
			if(boss_check == 0) {
				this.boss_check = "审核中";
			}else if(boss_check == 1) {
				this.boss_check = "通过";
			}else {
				this.boss_check = "未通过";
			}
		}
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		if(add_date != null) {
			this.add_date = DateUtil.format(add_date, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}
	}
	
}
