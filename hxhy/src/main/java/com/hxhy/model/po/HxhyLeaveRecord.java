package com.hxhy.model.po;

import java.util.Date;

import com.hxhy.model.common.BaseEntity;

public class HxhyLeaveRecord extends BaseEntity {

	private String user_id;
	private Long leave_id;
	private String monthy;
	private Date start_date;
	private Date end_date;
	private Integer longtime;
	private Integer manager_check;
	private Integer boss_check;
	private Date add_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(Long leave_id) {
		this.leave_id = leave_id;
	}
	public String getMonthy() {
		return monthy;
	}
	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getLongtime() {
		return longtime;
	}
	public void setLongtime(Integer longtime) {
		this.longtime = longtime;
	}
	public Integer getManager_check() {
		return manager_check;
	}
	public void setManager_check(Integer manager_check) {
		this.manager_check = manager_check;
	}
	public Integer getBoss_check() {
		return boss_check;
	}
	public void setBoss_check(Integer boss_check) {
		this.boss_check = boss_check;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
}
