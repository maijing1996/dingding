package com.hxhy.model.po;

import com.hxhy.model.common.BaseEntity;

public class HxhyDepartment extends BaseEntity {

	private Long department_id;//第三方提供的部门id
	private String name;//部门名称
	private Long parentid;//父级id
	private boolean create_dept_group;//是否同步创建一个关联此部门的企业群, 1表示是, 0表示不是
	private boolean auto_add_user;//当群已经创建后，是否有新人加入部门会自动加入该群, true表示是, false表示不是
	
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
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	public boolean isCreate_dept_group() {
		return create_dept_group;
	}
	public void setCreate_dept_group(boolean create_dept_group) {
		this.create_dept_group = create_dept_group;
	}
	public boolean isAuto_add_user() {
		return auto_add_user;
	}
	public void setAuto_add_user(boolean auto_add_user) {
		this.auto_add_user = auto_add_user;
	}
}
