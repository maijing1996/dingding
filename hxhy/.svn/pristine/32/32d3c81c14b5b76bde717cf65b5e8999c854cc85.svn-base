package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.OperationLog;
import com.hxhy.model.po.HxhyOperationLog;
import com.hxhy.util.MyMapper;

public interface HxhyOperationLogMapper extends MyMapper<HxhyOperationLog> {

	List<OperationLog> listInfo(@Param("state") Integer state, @Param("type") Integer type);
}
