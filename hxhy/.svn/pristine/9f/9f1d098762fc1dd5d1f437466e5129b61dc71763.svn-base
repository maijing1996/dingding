package com.hxhy.controller.manager.attendance;

import java.util.Date;
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
import com.hxhy.model.dto.WorkScheme;
import com.hxhy.model.dto.WorkSchemeEdil;
import com.hxhy.model.po.HxhyWorkScheme;
import com.hxhy.service.HxhyWorkSchemeService;
import com.hxhy.util.StringUtil;

@Controller
@ResponseBody
@RequestMapping("/manager/attendance/workscheme")
public class WorkSchemeController {

	@Autowired
	private HxhyWorkSchemeService hxhyWorkSchemeService;

	/**
	 * 获得所有的考勤信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listInfo(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		if (map != null) {
			PageInfo<WorkScheme> pageInfo = hxhyWorkSchemeService.listInfo(StringUtil.strToInt(map.get("page")),
					StringUtil.strToInt(map.get("size")), map.get("userId"),
					StringUtil.strToLong(map.get("departmentId")));

			if (pageInfo.getTotal() > 0) {
				return baseResponse.setSuccessList(200, pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages(),
						pageInfo.getPageNum(), pageInfo.getSize());
			} else {
				return baseResponse.setSuccess("没有数据");
			}
		} else {
			return baseResponse.setError(403, "参数有误");
		}
	}

	/**
	 * 删除考勤方案
	 * 
	 * @param map
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse delete(@RequestBody Map<String, String> map) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();

		hxhyWorkSchemeService.deleteById(StringUtil.strToLong(map.get("id")));
		return baseResponse.setSuccess("删除成功！", null);
	}

	/**
	 * 修改考勤方案
	 * @param hxhyWorkScheme
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse update(@RequestBody HxhyWorkScheme hxhyWorkScheme) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
//		hxhyWorkScheme.setAdd_date(new Date());
		boolean update = hxhyWorkSchemeService.update(hxhyWorkScheme);
		if(update) {
			return baseResponse.setSuccess("修改成功！", null);
		}else {
			return baseResponse.setError(500, "修改失败！");
		}
	}
	
	/**
	 * 保存考勤方案
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse save(@RequestBody HxhyWorkScheme hxhyWorkScheme) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		
		hxhyWorkScheme.setAdd_date(new Date());
		boolean save = hxhyWorkSchemeService.save(hxhyWorkScheme);
		if(save) {
			return baseResponse.setSuccess("添加成功！", null);
		}else {
			return baseResponse.setError(500, "添加失败！");
		}
	}
}
