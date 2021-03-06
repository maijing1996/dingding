package com.hxhy.model.dto;

import java.util.Date;

public class ManagerInfo {

	private Long id;
	private String account;//账号
	private String passwd;//密码
	private Long role_id;//角色id
	private Long department_id;//部门id
	private String user_id;//第三方提供的用户id
	private String name;//名称
	private Integer is_alter;//是否修改过用户账户
	private Integer is_work;//是否在职工作
	private Integer is_attendance;//是否参与考勤
	private Date add_date;
	private String title;//名称
	private String description;//描述
	private String power;//权限
	private Integer is_del;//是否可删除
	private Integer is_change;//是否可以修改
	private String dname;
	private Double basics_money;
	private Double money;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
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
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public Integer getIs_del() {
		return is_del;
	}
	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	public Integer getIs_change() {
		return is_change;
	}
	public void setIs_change(Integer is_change) {
		this.is_change = is_change;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
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
}
