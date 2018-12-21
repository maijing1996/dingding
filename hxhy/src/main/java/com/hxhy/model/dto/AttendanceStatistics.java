package com.hxhy.model.dto;

public class AttendanceStatistics {

	private String name;
	private String monthy;
	private Integer all_date;
	private Integer work_date;
	private Integer leave_date;
	private Integer leave_time;
	private Integer late_date;
	private Integer late_time;
	private Integer early_date;
	private Integer early_time;
	private Integer not_signed;
	private Double deduct_money;//迟到早退扣除的金额
	private Double leave_money;//请假扣除的金额
	private String is_perfect;//是否全勤
	
	public Double getDeduct_money() {
		return deduct_money;
	}
	public void setDeduct_money(Double deduct_money) {
		this.deduct_money = deduct_money;
	}
	public Double getLeave_money() {
		return leave_money;
	}
	public void setLeave_money(Double leave_money) {
		this.leave_money = leave_money;
	}
	public String getIs_perfect() {
		return is_perfect;
	}
	public void setIs_perfect(Integer is_perfect) {
		if(is_perfect != null) {
			if(is_perfect == 0) {
				this.is_perfect = "否";
			}else if(is_perfect == 1) {
				this.is_perfect = "是";
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMonthy() {
		return monthy;
	}
	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}
	public Integer getAll_date() {
		return all_date;
	}
	public void setAll_date(Integer all_date) {
		this.all_date = all_date;
	}
	public Integer getWork_date() {
		return work_date;
	}
	public void setWork_date(Integer work_date) {
		this.work_date = work_date;
	}
	public Integer getLeave_date() {
		return leave_date;
	}
	public void setLeave_date(Integer leave_date) {
		this.leave_date = leave_date;
	}
	public Integer getLeave_time() {
		return leave_time;
	}
	public void setLeave_time(Integer leave_time) {
		this.leave_time = leave_time;
	}
	public Integer getLate_date() {
		return late_date;
	}
	public void setLate_date(Integer late_date) {
		this.late_date = late_date;
	}
	public Integer getLate_time() {
		return late_time;
	}
	public void setLate_time(Integer late_time) {
		this.late_time = late_time;
	}
	public Integer getEarly_date() {
		return early_date;
	}
	public void setEarly_date(Integer early_date) {
		this.early_date = early_date;
	}
	public Integer getEarly_time() {
		return early_time;
	}
	public void setEarly_time(Integer early_time) {
		this.early_time = early_time;
	}
	public Integer getNot_signed() {
		return not_signed;
	}
	public void setNot_signed(Integer not_signed) {
		this.not_signed = not_signed;
	}
}