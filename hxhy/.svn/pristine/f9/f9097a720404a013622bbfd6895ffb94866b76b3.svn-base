package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyLeaveRecordMapper;
import com.hxhy.model.dto.AttendanceLeaveRecord;
import com.hxhy.model.po.HxhyLeaveRecord;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyLeaveRecordService extends BaseService<HxhyLeaveRecord, HxhyLeaveRecordMapper> {

	@Override
	protected String getTableName() {
		return null;
	}
	
	/**
	 * 通过第三方用户id查询用户请假信息
	 * @param userId
	 * @return
	 */
	public List<HxhyLeaveRecord> getByUserId(String userId, String monthy) {
		return mapper.getByUserId(userId, monthy);
	}
	
	/**
	 * 查询所有的请假记录
	 * @return
	 */
	public PageInfo<AttendanceLeaveRecord> listInfo(Integer page, Integer size, String monthy, String name, Integer type) {
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 20);
		}
		List<AttendanceLeaveRecord> list = mapper.listInfo(monthy, name, type);
		PageInfo<AttendanceLeaveRecord> pageInfo = new PageInfo<AttendanceLeaveRecord>(list);
		
		return pageInfo;
	}	
		
}
