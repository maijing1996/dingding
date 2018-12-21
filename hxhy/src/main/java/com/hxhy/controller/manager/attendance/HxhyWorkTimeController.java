package com.hxhy.controller.manager.attendance;

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
import com.hxhy.model.dto.WorkTimeInfo;
import com.hxhy.model.po.HxhyWorkTime;
import com.hxhy.service.HxhyWorkTimeService;
import com.hxhy.util.StringUtil;

@Controller
@ResponseBody
@RequestMapping("/manager/attendance/work")
public class HxhyWorkTimeController {
	
	@Autowired
	private HxhyWorkTimeService hxhyWorkTimeService;
	
	/**
	 * 查询所有的考勤时间
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;chatset=utf-8")
	public BaseResponse listInfo(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		PageInfo<WorkTimeInfo> pageInfo = hxhyWorkTimeService.listInfo(StringUtil.strToInt(map.get("page")), StringUtil.strToInt(map.get("size")));
		
		return baseResponse.setSuccessList(200, pageInfo.getList(), pageInfo.getTotal(), 0, 0, 30);
	}
	
	/**
	 * 删除考勤时间
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;chatset=utf-8")
	public BaseResponse delete(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		hxhyWorkTimeService.deleteById(StringUtil.strToLong(map.get("id")));
		
		return baseResponse.setSuccess("删除成功！", null);
	}
	
	/**
	 * 修改考勤时间
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;chatset=utf-8")
	public BaseResponse update(@RequestBody HxhyWorkTime hxhyWorkTime) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		boolean update = hxhyWorkTimeService.update(hxhyWorkTime);
		if(update) {
			return baseResponse.setSuccess("修改成功！", null);
		}else {
			return baseResponse.setSuccess("修改失败！", null);
		}
	}
	
	/**
	 * 添加考勤时间
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;chatset=utf-8")
	public BaseResponse save(@RequestBody HxhyWorkTime hxhyWorkTime) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		boolean save = hxhyWorkTimeService.save(hxhyWorkTime);
		if(save) {
			return baseResponse.setSuccess("添加成功！", null);
		}else {
			return baseResponse.setSuccess("添加失败！", null);
		}
	}
}
