package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyWorkSchemeMapper;
import com.hxhy.model.dto.WorkScheme;
import com.hxhy.model.po.HxhyWorkScheme;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyWorkSchemeService extends BaseService<HxhyWorkScheme, HxhyWorkSchemeMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 获得工作方案
	 * 
	 * @param page
	 * @param size
	 * @param userId
	 * @param departmentId
	 * @return
	 */
	public PageInfo<WorkScheme> listInfo(Integer page, Integer size, String userId, Long departmentId) {
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 40);
		}
		
		List<WorkScheme> list = mapper.listInfo(userId, departmentId);
		PageInfo<WorkScheme> pageInfo = new PageInfo<WorkScheme>(list);
		return pageInfo;
	}
	
	/**
	 * 获得用户绑定的方案类型
	 * @param userId
	 * @return
	 */
	public int getTypeByUserId(String userId) {
		return  mapper.getTypeByUserId(userId);
	}
	
	/**
	 * 获得特定员工的可以选择的工作方案
	 * 
	 * @param userId
	 * @return
	 */
	public List<HxhyWorkScheme> listUserScheme(String userId, Long departmentId) {
		return mapper.listUserScheme(userId, departmentId);
	}
}
