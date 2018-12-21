package com.hxhy.controller.manager.finance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hxhy.exception.BusinessException;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.dto.ExtraMoney;
import com.hxhy.model.po.HxhyUserExtraMoney;
import com.hxhy.service.HxhyUserExtraMoneyService;
import com.hxhy.util.DateUtil;
import com.hxhy.util.RegexUtil;
import com.hxhy.util.StringUtil;

//额外费用控制类
@Controller
@ResponseBody
@RequestMapping("/manager/finance/extra")
public class HxhyExtraMoneyController {

	@Autowired
	private HxhyUserExtraMoneyService hxhyUserExtraMoneyService;
	
	/**
	 * 获得额外费用信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listInfo(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		if(map != null) {
			String monthy = null;
			if(map.get("monthy") != null && !"".equals(map.get("monthy").trim())) {
				if(!"0".equals(map.get("monthy"))) {
					monthy = map.get("monthy");
				}
			} else {
				monthy = DateUtil.format(new Date(), DateUtil.FORMAT_YYYYMM);
			}
			
			PageInfo<ExtraMoney> pageInfo = hxhyUserExtraMoneyService.listInfo(StringUtil.strToInt(map.get("page")),
					StringUtil.strToInt(map.get("size")), StringUtil.strToInt(map.get("state")), map.get("name"),
					StringUtil.strToInt(map.get("extra_type")), monthy, StringUtil.strToInt(map.get("type")));
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
	 * 添加信息
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse insert(@RequestBody HxhyUserExtraMoney hxhyUserExtraMoney) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(hxhyUserExtraMoney.getId() == null) {
			hxhyUserExtraMoney.setAdd_date(new Date());
			hxhyUserExtraMoneyService.save(hxhyUserExtraMoney);
			return baseResponse.setSuccess("添加信息成功");
		} else {
			return baseResponse.setError(403, "参数异常");
		}
	}
	
	/**
	 * 更新信息
	 * 
	 * @param hxhyRevenue
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse update(@RequestBody HxhyUserExtraMoney hxhyUserExtraMoney) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(hxhyUserExtraMoney.getId() != null) {
			hxhyUserExtraMoneyService.update(hxhyUserExtraMoney);
			return baseResponse.setSuccess("修改信息成功");
		} else {
			return baseResponse.setError(403, "参数异常");
		}
	}
	
	/**
	 * 删除单个信息
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse delete(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(map != null && StringUtil.strToLong(map.get("id")) !=null) {
			hxhyUserExtraMoneyService.deleteById(StringUtil.strToLong(map.get("id")));
			return baseResponse.setSuccess("删除成功！");
		} else {
			return baseResponse.setError(403, "参数有误");
		}
	}
	
	/**
	 * 批量删除信息
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/deletes", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse deletes(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(map != null && RegexUtil.matches(RegexUtil.IDS_REGEX, map.get("ids"))) {
			hxhyUserExtraMoneyService.deleteByIds(map.get("ids"));
			return baseResponse.setSuccess("删除成功！");
		} else {
			return baseResponse.setError(403, "参数有误");
		}
	}
	
	/**
	 * 获得月份信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/monthy", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listAllStatistics() {
		BaseResponse baseResponse = new BaseResponse();
		List<String> list = hxhyUserExtraMoneyService.getMonthy();
		return baseResponse.setSuccess(null, list);
	}
}
