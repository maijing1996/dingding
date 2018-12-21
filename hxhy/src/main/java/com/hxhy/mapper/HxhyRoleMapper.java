package com.hxhy.mapper;

import java.util.List;

import com.hxhy.model.po.HxhyRole;
import com.hxhy.util.MyMapper;

public interface HxhyRoleMapper extends MyMapper<HxhyRole> {

	List<HxhyRole> listAllInfo();
}
