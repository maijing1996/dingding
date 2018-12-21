package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyWorkTimeMapper;
import com.hxhy.model.dto.WorkTimeInfo;
import com.hxhy.model.po.HxhyWorkTime;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyWorkTimeService extends BaseService<HxhyWorkTime, HxhyWorkTimeMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 通过部门id获得工作时间信息
	 * 如果该部门没有特有的工作时间安排，就使用默认的时间安排
	 * 
	 * @param departmentId
	 * @return
	 */
	public List<HxhyWorkTime> getByDepartmentId(Long departmentId) {
		List<HxhyWorkTime> list = mapper.getByDepartmentId(departmentId, null);
		if(list == null || list.isEmpty()) {
			list = mapper.getByDepartmentId(null, 1L);
		}
		return list;
	}

	/**
	 * 获得所有的工作时间
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<WorkTimeInfo> listInfo(Integer page, Integer size) {
		
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 20);
		}
		
		List<WorkTimeInfo> list = mapper.listInfo();
		PageInfo<WorkTimeInfo> pageInfo = new PageInfo<WorkTimeInfo>(list);
		return pageInfo;
	}
}
