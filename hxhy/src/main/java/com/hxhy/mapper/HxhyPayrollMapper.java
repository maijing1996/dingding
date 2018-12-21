package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.Payroll;
import com.hxhy.model.po.HxhyPayroll;
import com.hxhy.util.MyMapper;

public interface HxhyPayrollMapper extends MyMapper<HxhyPayroll> {

	List<Payroll> listInfo(@Param("monthy") String monthy, @Param("name") String name);
	
	List<HxhyPayroll> getInfo(@Param("userId") String userId);
}
