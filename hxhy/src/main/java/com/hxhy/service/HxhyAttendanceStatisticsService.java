package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyAttendanceStatisticsMapper;
import com.hxhy.model.dto.AttendanceStatistics;
import com.hxhy.model.po.HxhyAttendanceStatistics;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyAttendanceStatisticsService extends BaseService<HxhyAttendanceStatistics, HxhyAttendanceStatisticsMapper> {

	@Override
	protected String getTableName() {
		return null;
	}
	
	/**
	 * 获得用户考勤统计信息
	 * 
	 * @param page
	 * @param size
	 * @param userId
	 * @param monthy
	 * @return
	 */
	public PageInfo<AttendanceStatistics> statistics(Integer page, Integer size, String monthy, String userId, String name) {
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 40);
		}
		
		List<AttendanceStatistics> list = mapper.statistics(userId, monthy, name);
		PageInfo<AttendanceStatistics> pageInfo = new PageInfo<AttendanceStatistics>(list);
		return pageInfo;
	}
	
	/**
	 * 获取统计表的月份
	 * @return
	 */
	public List<HxhyAttendanceStatistics> getMonthy() {
		
		return mapper.getMonthy();
	}

}
