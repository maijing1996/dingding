package com.hxhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxhy.mapper.HxhyManagerMapper;
import com.hxhy.model.dto.Employee;
import com.hxhy.model.dto.ManagerInfo;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.service.common.BaseService;

@Service
public class HxhyManagerService extends BaseService<HxhyManager, HxhyManagerMapper> {

	@Override
	protected String getTableName() {
		return null;
	}

	/**
	 * 管理员登入
	 * 
	 * @param account
	 * @param passwd
	 * @return
	 */
	public HxhyManager login(String account, String passwd) {
		return mapper.login(account, passwd);
	}

	/**
	 * 查询所有的管理员
	 * 
	 * @return
	 */
	public List<ManagerInfo> listInfo() {
		return mapper.listInfo();
	}

	/**
	 * 获得所有在职的职工信息
	 * @return
	 */
	public List<HxhyManager> listEmployee() {
		return mapper.listEmployee();
	}
	
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public PageInfo<Employee> listEmployeeInfo(Integer page, Integer size, String name, Integer roleId, Integer isWork, Integer departmentId) {
		
		if(page != null && size != null) {
			PageHelper.startPage(page, size);
		} else {
			PageHelper.startPage(1, 20);
		}
		
		List<Employee> list = mapper.listEmployeeInfo(name, roleId, isWork, departmentId);
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);
		return pageInfo;
	}
}
