package com.hxhy.model.po;

import java.util.Date;

import javax.persistence.Transient;

import com.hxhy.model.common.BaseEntity;

public class HxhyOperationLog extends BaseEntity {
	
	private Long user_id;//用户id
	private String info;//操作内容
	private String ip;//操作ip
	private String url;//操作url
	private Integer type;//日志类型 0:操作日志 1:登录日志
	private Integer state;//日志状态 0:失败 1:成功
	private Date add_date;//操作时间
	
	@Transient
	private String name;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
