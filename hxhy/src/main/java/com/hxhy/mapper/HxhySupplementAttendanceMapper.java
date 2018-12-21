package com.hxhy.mapper;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.SupplementAttendance;
import com.hxhy.model.po.HxhySupplementAttendance;
import com.hxhy.util.MyMapper;

public interface HxhySupplementAttendanceMapper extends MyMapper<HxhySupplementAttendance> {

	SupplementAttendance getSupplement(@Param("name") String name, @Param("workDate") String workDate);

}
