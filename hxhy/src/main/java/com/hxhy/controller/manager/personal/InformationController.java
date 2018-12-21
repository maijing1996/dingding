package com.hxhy.controller.manager.personal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxhy.controller.BaseController;
import com.hxhy.exception.BusinessException;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.util.DateUtil;

@Controller
@ResponseBody
@RequestMapping("/manager/personal/information")
public class InformationController extends BaseController {
	
	/**
	 * 个人信息类
	 * 
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse info(HttpServletRequest request) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		HxhyManager hxhyManager = this.getCurrentUser(request);
		if(hxhyManager.getWork_date() != null) {
			hxhyManager.setWork_date_str(DateUtil.format(hxhyManager.getWork_date(), DateUtil.FORMAT_YYYY_MM_dd));
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", hxhyManager);
		
		return baseResponse.setSuccess(null, map);
	}
}
