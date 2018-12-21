package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyAttendanceMapper;
import com.hxhy.model.dto.AttendanceRecord;
import com.hxhy.model.po.HxhyAttendance;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyAttendanceService extends BaseService<HxhyAttendance, HxhyAttendanceMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 查询所有的请假类型
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<AttendanceRecord> listInfo(Integer page, Integer size, String userId, Long departmentId, String nickname, String monthy) {
		
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 20);
		}
		
		List<AttendanceRecord> list = mapper.listInfo(userId, departmentId, nickname, monthy);
		PageInfo<AttendanceRecord> pageInfo = new PageInfo<AttendanceRecord>(list);
		return pageInfo;
	}
}
