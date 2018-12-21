package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hxhy.mapper.HxhyRoleMapper;
import com.hxhy.model.po.HxhyRole;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyRoleService extends BaseService<HxhyRole, HxhyRoleMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 获得所有的角色信息
	 * @return
	 */
	public List<HxhyRole> listAllInfo() {
		return mapper.listAllInfo();
	}
}
