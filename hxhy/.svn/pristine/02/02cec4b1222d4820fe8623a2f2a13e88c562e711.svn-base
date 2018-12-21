package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyOperationLogMapper;
import com.hxhy.model.dto.OperationLog;
import com.hxhy.model.po.HxhyOperationLog;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyOperationLogService extends BaseService<HxhyOperationLog, HxhyOperationLogMapper> {

	@Override
	protected String getTableName() {
		return "hxhy_operation_log";
	}

	
	/**
	 * 获得日志信息
	 * @param page
	 * @param size
	 * @param state
	 * @return
	 */
	public PageInfo<OperationLog> listInfo(Integer page, Integer size, Integer state, Integer type){
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 20);
		}
		
		List<OperationLog> list = mapper.listInfo(state, type);
		PageInfo<OperationLog> pageInfo = new PageInfo<OperationLog>(list);
		return pageInfo;
	}
}
