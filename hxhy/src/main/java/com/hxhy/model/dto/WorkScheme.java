package com.hxhy.model.dto;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class WorkScheme {

	private Long id;
	private String title;
	private String description;
	private String name="全体员工";
	private String depname="全部部门";
	private String on_duty;
	private String off_duty;
	private Integer is_del;
	private String add_date;
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		if(add_date != null) {
			this.add_date = DateUtil.format(add_date, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepname() {
		return depname;
	}
	public void setDepname(String depname) {
		this.depname = depname;
	}
}
