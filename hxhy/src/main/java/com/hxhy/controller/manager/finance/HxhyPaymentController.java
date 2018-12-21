package com.hxhy.controller.manager.finance;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.dto.Payroll;
import com.hxhy.service.HxhyPayrollService;
import com.hxhy.service.other.HxhyService;
import com.hxhy.util.DateUtil;
import com.hxhy.util.StringUtil;

//结算控制层
@Controller
@ResponseBody
@RequestMapping("/manager/finance/payment")
public class HxhyPaymentController {

	@Autowired
	private HxhyService hxhyService;
	@Autowired
	private HxhyPayrollService hxhyPayrollService;
	
	/**
	 * 获得员工结算信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listInfo(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		if(map != null) {
			String monthy = null;
			if(map.get("monthy") != null && "".equals(map.get("monthy").trim())) {
				monthy = map.get("monthy");
			} else {
				monthy = DateUtil.format(new Date(), DateUtil.FORMAT_YYYYMM);
			}
			PageInfo<Payroll> pageInfo = hxhyPayrollService.listInfo(StringUtil.strToInt(map.get("page")),
					StringUtil.strToInt(map.get("size")), monthy, map.get("name"));
			if(pageInfo.getTotal() > 0) {
				return baseResponse.setSuccessList(200, pageInfo.getList(), pageInfo.getTotal());
			} else {
				return baseResponse.setError(204, "没有数据");
			}
		} else {
			return baseResponse.setError(403, "参数有误");
		}
	}
	
	/**
	 * 手动结算，当前做成所有一起统计
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/settlement", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse settlement(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		if(map != null && map.get("monthy") != null) {
			String res = hxhyService.payrollCalculation(map.get("monthy"));
			if("0".equals(res)) {
				return baseResponse.setSuccess("统计成功");
			} else {
				return baseResponse.setError(403, "统计出错");
			}
		} else {
			return baseResponse.setError(403, "参数有误");
		}
	}
}
