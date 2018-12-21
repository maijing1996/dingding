package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyPayrollMapper;
import com.hxhy.model.dto.Payroll;
import com.hxhy.model.po.HxhyPayroll;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyPayrollService extends BaseService<HxhyPayroll, HxhyPayrollMapper> {

	@Override
	protected String getTableName() {
		return "hxhy_payroll";
	}

	/**
	 * 获得员工结算信息
	 * 
	 * @param page
	 * @param size
	 * @param monthy
	 * @return
	 */
	public PageInfo<Payroll> listInfo(Integer page, Integer size, String monthy, String name) {
		if(page != null && size != null) {
			PageHelper.startPage(page,	size);
		} else {
			PageHelper.startPage(1, 30);
		}
		
		List<Payroll> list = mapper.listInfo(monthy, name);
		PageInfo<Payroll> pageInfo = new PageInfo<Payroll>(list);
		return pageInfo;
	}
	
	/**
	 * 获得个人所有的薪酬信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<HxhyPayroll> getInfo(String userId) {
		return mapper.getInfo(userId);
	}
}
