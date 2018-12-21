package com.hxhy.controller.manager.finance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxhy.exception.BusinessException;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.po.HxhyFinance;
import com.hxhy.service.HxhyFinanceService;

@Controller
@ResponseBody
@RequestMapping("/manager/finance/setting")
public class FinanceSettingController {

	@Autowired
	private HxhyFinanceService hxhyFinanceService;

	/**
	 * 获得财务设置信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listInfo() {
		BaseResponse baseResponse = new BaseResponse();
		HxhyFinance hxhyFinance = hxhyFinanceService.getById(1L);
		return baseResponse.setSuccess(null, hxhyFinance);
	}

	/**
	 * 修改财务设置信息
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse update(@RequestBody HxhyFinance hxhyFinance) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(hxhyFinance.getId() != null) {
			hxhyFinanceService.update(hxhyFinance);
			return baseResponse.setSuccess("修改成功！");
		} else {
			return baseResponse.setError(204, "参数异常");
		}
	}
}
