package com.hxhy.mapper;

import java.util.List;

import com.hxhy.model.po.HxhyDepartment;
import com.hxhy.util.MyMapper;

public interface HxhyDepartmentMapper extends MyMapper<HxhyDepartment> {

	List<HxhyDepartment> listDepartmentInfo();

}
