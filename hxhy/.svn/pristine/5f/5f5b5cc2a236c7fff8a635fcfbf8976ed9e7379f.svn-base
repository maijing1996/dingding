package com.hxhy.controller.manager.attendance;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxhy.exception.BusinessException;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.dto.SupplementAttendance;
import com.hxhy.model.po.HxhySupplementAttendance;
import com.hxhy.service.HxhySupplementAttendanceService;

@Controller
@ResponseBody
@RequestMapping("/manager/attendance/supplement")
public class HxhySupplementAttendanceController {
	
	@Autowired
	private HxhySupplementAttendanceService hxhySupplementAttendanceService;
	
	/**
	 * 查询员工补卡记录
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse getSupplement(HttpServletRequest request, @RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		SupplementAttendance supplementAttendance = hxhySupplementAttendanceService.getSupplement(map.get("name"), map.get("workDate"));
		
		return baseResponse.setSuccessList(200, supplementAttendance, 0, 0, 0, 0);
	}

	/**
	 * 补卡记录审核
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse update(@RequestBody HxhySupplementAttendance hxhySupplementAttendance) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		hxhySupplementAttendanceService.update(hxhySupplementAttendance);
		return baseResponse.setSuccess("操作成功！", null);
	}
}
