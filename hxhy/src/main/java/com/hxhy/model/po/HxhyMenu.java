package com.hxhy.model.po;

import com.hxhy.model.common.BaseEntity;

public class HxhyMenu extends BaseEntity {

	private Long parent_id;//上级ID
	private String title;//名称
	private String controller;//控制器
	private String operation;//操作方法
	private String parameter;//其他参数
	private String ico;//图标
	private Integer sequence;//排序
	private Integer type;//0:系统菜单 1:自定义菜单
	private Integer is_turn;//点击顶级菜单是否跳转
	private Integer is_show;//是否显示
	
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIs_turn() {
		return is_turn;
	}
	public void setIs_turn(Integer is_turn) {
		this.is_turn = is_turn;
	}
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
}
