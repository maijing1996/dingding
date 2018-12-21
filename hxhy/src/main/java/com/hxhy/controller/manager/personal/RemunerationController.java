package com.hxhy.controller.manager.personal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxhy.controller.BaseController;
import com.hxhy.exception.BusinessException;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.model.po.HxhyPayroll;
import com.hxhy.service.HxhyPayrollService;

@Controller
@ResponseBody
@RequestMapping("/manager/personal/remuneration")
public class RemunerationController extends BaseController {

	@Autowired
	private HxhyPayrollService hxhyPayrollService;
	
	/**
	 * 获得个人的薪酬信息
	 * 
	 * @param request
	 * @param hxhyUserExtraMoney
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse info(HttpServletRequest request) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		HxhyManager hxhyManager = this.getCurrentUser(request);
		List<HxhyPayroll> list = hxhyPayrollService.getInfo(hxhyManager.getUser_id());
		return baseResponse.setSuccessList(200, list, list.size());
	}
}
