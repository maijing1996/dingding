package com.hxhy.model.dto;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class ExtraMoney {

	private Long id;
	private String state;// 作用的对象，1公司，2个人
	private String extra_type;// 额外费用类型
	private String type;// 资金流向：1支付，2收入
	private String title;
	private String description;
	private Double money;
	private String use_date;
	private String monthy;
	private String name;
	private String add_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(Integer state) {
		if (state != null) {
			if (state == 1) {
				this.state = "公司";
			} else {
				this.state = "个人";
			}
		}
	}

	public String getExtra_type() {
		return extra_type;
	}

	public void setExtra_type(Integer extra_type) {
		if (extra_type != null) {
			if (extra_type == 1) {
				this.extra_type = "补发工资";
			} else if (extra_type == 2) {
				this.extra_type = "餐费";
			} else if (extra_type == 3) {
				this.extra_type = "奖金";
			} else if (extra_type == 4) {
				this.extra_type = "加班费";
			} else if (extra_type == 5) {
				this.extra_type = "费用报销";
			} else if (extra_type == 6) {
				this.extra_type = "公司支付";
			} else {
				this.extra_type = "公司收入";
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(Integer type) {
		if (type != null) {
			if (type == 1) {
				this.type = "支付";
			} else {
				this.type = "收入";
			}
		}
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getUse_date() {
		return use_date;
	}

	public void setUse_date(Date use_date) {
		if (use_date != null) {
			this.use_date = DateUtil.format(use_date, DateUtil.FORMAT_YYYY_MM_dd);
		}
	}

	public String getMonthy() {
		return monthy;
	}

	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(Date add_date) {
		if (add_date != null) {
			this.add_date = DateUtil.format(add_date);
		}
	}
}
