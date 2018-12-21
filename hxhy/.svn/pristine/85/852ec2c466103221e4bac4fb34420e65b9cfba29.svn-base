package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hxhy.model.dto.AttendanceLeaveRecord;
import com.hxhy.model.po.HxhyLeaveRecord;
import com.hxhy.util.MyMapper;

public interface HxhyLeaveRecordMapper extends MyMapper<HxhyLeaveRecord> {

	List<HxhyLeaveRecord> getByUserId(@Param("userId") String userId, @Param("monthy") String monthy);
	
	List<AttendanceLeaveRecord> listInfo(@Param("monthy") String monthy, @Param("name") String name, @Param("type") Integer type);
}
