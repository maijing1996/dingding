package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.AttendanceStatistics;
import com.hxhy.model.po.HxhyAttendanceStatistics;
import com.hxhy.util.MyMapper;

public interface HxhyAttendanceStatisticsMapper extends MyMapper<HxhyAttendanceStatistics> {

	List<AttendanceStatistics> statistics(@Param("userId") String userId, @Param("monthy") String monthy, @Param("name") String name);

	List<HxhyAttendanceStatistics> getMonthy();
}
