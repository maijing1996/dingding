package com.hxhy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxhy.service.other.DingtalkService;

@Controller
@ResponseBody
@RequestMapping("/dingtalk")
public class DingtalkController {

	@Autowired
	private DingtalkService dingtalkService;
	
	@RequestMapping(value="/info", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public String listInfo() {
		//DepartmentResp departmentResp = dingtalkService.listDepartment();
		//System.out.println(JSONObject.toJSON(departmentResp).toString());
		return null;
	}
}
