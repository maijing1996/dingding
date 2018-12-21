package com.hxhy.model.dto;

public class StatisticsAmount {

	private Integer state;//类型，1休假，2调班
	private Integer amount;//天数
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
