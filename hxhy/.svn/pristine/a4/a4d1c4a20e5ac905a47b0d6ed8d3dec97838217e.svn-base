package com.hxhy.model.dto;

import java.util.Date;

import javax.persistence.Transient;

import com.hxhy.util.DateUtil;

public class OperationLog {

	private Long id;
	private Long user_id;// 用户id
	private String info;// 操作内容
	private String ip;// 操作ip
	private String url;// 操作url
	private String type;// 日志类型 0:操作日志 1:登录日志
	private String state;// 日志状态 0:失败 1:成功
	private String add_date;// 操作时间

	@Transient
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(Integer type) {
		if(type != null) {
			if(type == 0) {
				this.type = "操作日志";
			}else {
				this.type = "登录日志";
			}
		}
	}

	public String getState() {
		return state;
	}

	public void setState(Integer state) {
		if(state != null) {
			if(state == 0) {
				this.state = "失败";
			}else {
				this.state = "成功";
			}
		}
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		if(add_date != null) {
			this.add_date = DateUtil.format(add_date, DateUtil.FORMAT_YYYY_MM_dd);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
