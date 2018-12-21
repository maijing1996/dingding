package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyHolidayMapper;
import com.hxhy.model.dto.Holiday;
import com.hxhy.model.dto.StatisticsAmount;
import com.hxhy.model.po.HxhyHoliday;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyHolidayService extends BaseService<HxhyHoliday, HxhyHolidayMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 获得所有节假日信息
	 * 
	 * @param page
	 * @param size
	 * @param isUse
	 * @param departmentId
	 * @return
	 */
	public PageInfo<Holiday> listInfo(Integer page, Integer size, Integer isUse, Long departmentId, Integer type) {
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 40);
		}
		
		List<Holiday> list = mapper.listInfo(isUse, departmentId, type);
		PageInfo<Holiday> pageInfo = new PageInfo<Holiday>(list);
		return pageInfo;
	}
	
	/**
	 * 获得指定月份的休假及调班的天数
	 * @param monthy
	 * @return
	 */
	public List<StatisticsAmount> getStatisics(String monthy) {
		return mapper.getStatisics(monthy);
	}

	/**
	 * 获取放假的人
	 * @return
	 */
	public List<Holiday> listTypeInfo() {
		
		return mapper.listTypeInfo();
	}
}
