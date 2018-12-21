package com.hxhy.model.po;

import java.util.Date;

import javax.persistence.Transient;

import com.hxhy.model.common.BaseEntity;

public class HxhyManager extends BaseEntity {

	private String account;//账号
	private String passwd;//密码
	private Long role_id;//角色id
	private String user_id;//第三方提供的用户id
	private Long word_id;//工作方案
	private Long department_id;//部门id
	private String name;//名称
	private Integer is_alter;//是否修改过用户账户
	private Integer is_work;//是否在职工作
	private Integer is_attendance;//是否参与考勤
	private Double basics_money;
	private Double money;
	private Integer is_official;
	private Integer is_settlement;
	private String birthday;
	private Integer sex;
	private String phone;
	private Date work_date;
	private Date add_date;
	
	@Transient
	private String dname;
	@Transient
	private String rname;
	@Transient
	private String work_date_str;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Long getWord_id() {
		return word_id;
	}
	public void setWord_id(Long word_id) {
		this.word_id = word_id;
	}
	public Long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIs_alter() {
		return is_alter;
	}
	public void setIs_alter(Integer is_alter) {
		this.is_alter = is_alter;
	}
	public Integer getIs_work() {
		return is_work;
	}
	public void setIs_work(Integer is_work) {
		this.is_work = is_work;
	}
	public Integer getIs_attendance() {
		return is_attendance;
	}
	public void setIs_attendance(Integer is_attendance) {
		this.is_attendance = is_attendance;
	}
	public Double getBasics_money() {
		return basics_money;
	}
	public void setBasics_money(Double basics_money) {
		this.basics_money = basics_money;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getIs_official() {
		return is_official;
	}
	public void setIs_official(Integer is_official) {
		this.is_official = is_official;
	}
	public Integer getIs_settlement() {
		return is_settlement;
	}
	public void setIs_settlement(Integer is_settlement) {
		this.is_settlement = is_settlement;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getWork_date() {
		return work_date;
	}
	public void setWork_date(Date work_date) {
		this.work_date = work_date;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getWork_date_str() {
		return work_date_str;
	}
	public void setWork_date_str(String work_date_str) {
		this.work_date_str = work_date_str;
	}
}
