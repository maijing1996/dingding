package com.hxhy.model.po;

import java.util.Date;

import com.hxhy.model.common.BaseEntity;

public class HxhyRevenue extends BaseEntity {
	
	private Integer type;// 类型
	private Double threshold;// 起征点
	private Double ceiling;// 起征上限
	private Double tax_rate;// 税率
	private Date add_time;// 添加时间

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
}
