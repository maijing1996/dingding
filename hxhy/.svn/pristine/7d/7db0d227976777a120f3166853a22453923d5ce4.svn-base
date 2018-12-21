package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hxhy.mapper.HxhyMenuMapper;
import com.hxhy.model.dto.MenuPackage;
import com.hxhy.model.po.HxhyMenu;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyMenuService extends BaseService<HxhyMenu, HxhyMenuMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	public List<HxhyMenu> getMenu() {
		return null;
	}

	/**
	 * 获得菜单列表
	 * @return
	 */
	public List<MenuPackage> menu() {
		return mapper.menu();
	}
}
