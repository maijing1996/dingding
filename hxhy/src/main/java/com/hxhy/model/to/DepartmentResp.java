package com.hxhy.model.to;

import java.util.List;

public class DepartmentResp {

	private Integer errcode;//	返回码
	private String errmsg;//	对返回码的文本描述内容
	private List<DepartmentDetails> department;//	部门列表数据。以部门的order字段从小到大排列
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public List<DepartmentDetails> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentDetails> department) {
		this.department = department;
	}
}
