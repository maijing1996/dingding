package com.hxhy.model.dto;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class RevenueInfo {
	
	private Long id;
	private Integer type;// 类型
	private String type_name;
	private Double threshold;// 起征点
	private Double ceiling;// 起征上限
	private Double tax_rate;// 税率
	private String add_time;// 添加时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		if(type == 1) {
			this.type_name = "个人所得税";
		}else if(type == 2) {
			this.type_name = "公司税收";
		}else {
			this.type_name = null;
		}
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public Double getThreshold() {
		return threshold;
	}
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}
	public Double getCeiling() {
		return ceiling;
	}
	public void setCeiling(Double ceiling) {
		this.ceiling = ceiling;
	}
	public Double getTax_rate() {
		return tax_rate;
	}
	public void setTax_rate(Double tax_rate) {
		this.tax_rate = tax_rate;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		if(add_time != null) {
			this.add_time = DateUtil.format(add_time, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}
	}
	
}
