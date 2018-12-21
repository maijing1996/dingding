package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyLeaveMapper;
import com.hxhy.model.dto.AttendanceLeave;
import com.hxhy.model.dto.AttendanceRecord;
import com.hxhy.model.po.HxhyLeave;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyLeaveService extends BaseService<HxhyLeave, HxhyLeaveMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 查询所有的请假记录
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<AttendanceLeave> listInfo(Integer page, Integer size) {
		
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 20);
		}
		
		List<AttendanceLeave> list = mapper.listInfo();
		PageInfo<AttendanceLeave> pageInfo = new PageInfo<AttendanceLeave>(list);
		
		return pageInfo;
	}

}
