package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.WorkTimeInfo;
import com.hxhy.model.po.HxhyWorkTime;
import com.hxhy.util.MyMapper;

public interface HxhyWorkTimeMapper extends MyMapper<HxhyWorkTime> {

	List<HxhyWorkTime> getByDepartmentId(@Param("departmentId") Long departmentId, @Param("id") Long id);

	List<WorkTimeInfo> listInfo();
}
