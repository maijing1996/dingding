package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.Holiday;
import com.hxhy.model.dto.StatisticsAmount;
import com.hxhy.model.po.HxhyHoliday;
import com.hxhy.util.MyMapper;

public interface HxhyHolidayMapper extends MyMapper<HxhyHoliday> {

	List<Holiday> listInfo(@Param("isUse") Integer isUse, @Param("departmentId") Long departmentId, @Param("type") Integer type);
	
	List<StatisticsAmount> getStatisics(@Param("monthy") String monthy);

	List<Holiday> listTypeInfo();
}
