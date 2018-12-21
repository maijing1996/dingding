package com.hxhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hxhy.model.dto.ManagerInfo;
import com.hxhy.model.dto.Employee;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.util.MyMapper;

public interface HxhyManagerMapper extends MyMapper<HxhyManager> {

	HxhyManager login(@Param("account") String account, @Param("passwd") String passwd);

	List<ManagerInfo> listInfo();

	List<HxhyManager> listEmployee();
	
	List<Employee> listEmployeeInfo(@Param("name") String name, @Param("roleId") Integer roleId,
			@Param("isWork") Integer isWork, @Param("departmentId") Integer departmentId);
}
