package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hxhy.mapper.HxhyRevenueMapper;
import com.hxhy.model.dto.RevenueInfo;
import com.hxhy.model.po.HxhyRevenue;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyRevenueService extends BaseService<HxhyRevenue, HxhyRevenueMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 查询所有的税率信息
	 * @return
	 */
	public List<RevenueInfo> listInfo() {
		
		return mapper.listInfo();
	}

}
