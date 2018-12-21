package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyUserExtraMoneyMapper;
import com.hxhy.model.dto.ExtraMoney;
import com.hxhy.model.po.HxhyUserExtraMoney;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyUserExtraMoneyService extends BaseService<HxhyUserExtraMoney, HxhyUserExtraMoneyMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 获得额外费用信息
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @param userId
	 * @param monthy
	 * @return
	 */
	public PageInfo<ExtraMoney> listInfo(Integer page, Integer size, Integer state, String name,
			Integer extraType, String monthy, Integer type) {
		if(page != null && size != null) {
			PageHelper.startPage(1, 30);
		} else {
			PageHelper.startPage(1, 30);
		}
		
		List<ExtraMoney> list = mapper.listInfo(state, name, monthy, extraType, type);
		PageInfo<ExtraMoney> pageInfo = new PageInfo<ExtraMoney>(list);
		return pageInfo;
	}
	
	/**
	 * 获得月份信息
	 * @return
	 */
	public List<String> getMonthy() {
		return mapper.getMonthy();
	}
}
