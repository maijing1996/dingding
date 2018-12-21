package com.hxhy.controller.manager.confine;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxhy.conf.MySiteSetting;
import com.hxhy.exception.BusinessException;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.dto.ManagerInfo;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.service.HxhyManagerService;

@Controller
@ResponseBody
@RequestMapping("/manager/confine/user")
public class HxhyManagerController {

	@Autowired
	private HxhyManagerService hxhyManagerService;
	@Autowired
	public MySiteSetting mySiteSetting;
	
	/**
	 * 查找所有的管理员
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listInfo(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();

		List<ManagerInfo> list = hxhyManagerService.listInfo();
		if(list != null && !list.isEmpty()) {
			return baseResponse.setSuccess("操作成功！", list);
		} else {
			return baseResponse.setSuccess("没有数据");
		}
	}
	
	/**
	 * 获得所有在职的职工的id 和 name
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listEmployee() {
		BaseResponse baseResponse = new BaseResponse();

		List<HxhyManager> list = hxhyManagerService.listEmployee();
		if(list != null && !list.isEmpty()) {
			return baseResponse.setSuccess("操作成功！", list);
		} else {
			return baseResponse.setSuccess("没有数据");
		}
	}

	/**
	 * 添加和修改管理员
	 * 
	 * @param
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces ="application/json;charset=utf-8")
	public BaseResponse update(@RequestBody HxhyManager hxhyManager) throws BusinessException {
		BaseResponse baseResponse = new BaseResponse();
		if(hxhyManager.getId() != null && hxhyManager.getRole_id() != null) {
			HxhyManager hxhyManager2 = new HxhyManager();
			hxhyManager2.setId(hxhyManager.getId());
			hxhyManager2.setRole_id(hxhyManager.getRole_id());
			
			hxhyManagerService.update(hxhyManager2);
			return baseResponse.setSuccess("操作成功");
		} else {
			return baseResponse.setError(403, "参数有误");
		}
	}
}
