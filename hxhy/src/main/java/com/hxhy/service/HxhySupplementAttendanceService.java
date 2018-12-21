package com.hxhy.service;

import org.springframework.stereotype.Service;

import com.hxhy.mapper.HxhySupplementAttendanceMapper;
import com.hxhy.model.dto.SupplementAttendance;
import com.hxhy.model.po.HxhySupplementAttendance;
import com.hxhy.service.common.BaseService;

@Service
public class HxhySupplementAttendanceService extends BaseService<HxhySupplementAttendance, HxhySupplementAttendanceMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 获取补卡记录的信息
	 * @param name
	 * @param workDate
	 * @return
	 */
	public SupplementAttendance getSupplement(String name, String workDate) {
		
		return mapper.getSupplement(name, workDate);
	}

}
