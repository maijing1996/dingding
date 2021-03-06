package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.WorkScheme;
import com.hxhy.model.po.HxhyWorkScheme;
import com.hxhy.util.MyMapper;

public interface HxhyWorkSchemeMapper extends MyMapper<HxhyWorkScheme> {

	List<WorkScheme> listInfo(@Param("userId") String userId, @Param("departmentId") Long departmentId);
	
	int getTypeByUserId(@Param("userId") String userId);
	
	List<HxhyWorkScheme> listUserScheme(@Param("userId") String userId, @Param("departmentId") Long departmentId);
}
