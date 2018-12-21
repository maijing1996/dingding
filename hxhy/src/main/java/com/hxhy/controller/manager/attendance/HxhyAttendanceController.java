package com.hxhy.controller.manager.attendance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hxhy.model.common.BaseResponse;
import com.hxhy.model.dto.AttendanceRecord;
import com.hxhy.model.dto.AttendanceStatistics;
import com.hxhy.model.po.HxhyAttendanceStatistics;
import com.hxhy.service.HxhyAttendanceService;
import com.hxhy.service.HxhyAttendanceStatisticsService;
import com.hxhy.util.DateUtil;
import com.hxhy.util.StringUtil;

@Controller
@ResponseBody
@RequestMapping("/manager/attendance/record")
public class HxhyAttendanceController {

	@Autowired
	private HxhyAttendanceService hxhyAttendanceService;
	@Autowired
	private HxhyAttendanceStatisticsService hxhyAttendanceStatisticsService;

	/**
	 * 获得所有的考勤信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listInfo(@RequestBody Map<String, String> map) {

		BaseResponse baseResponse = new BaseResponse();
		String monthy = null;
		if (map != null) {
			if(map.get("monthy") != null) {
				if(StringUtil.strToInt(map.get("monthy")) == 1) {
					monthy = "";
				}else {
					monthy = map.get("monthy");
				}
			}else {
				monthy = DateUtil.getYearMonthy();
			}
			PageInfo<AttendanceRecord> pageInfo = hxhyAttendanceService.listInfo(StringUtil.strToInt(map.get("page")),
					StringUtil.strToInt(map.get("limit")), map.get("userId"),
					StringUtil.strToLong(map.get("departmentId")), map.get("nickname"), monthy);

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
	 * 获得统计信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/statistics", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse statistics(@RequestBody Map<String, String> map) {

		BaseResponse baseResponse = new BaseResponse();
		if (map != null && map.get("monthy") != null && !"".equals(map.get("monthy").trim())) {
			String monthy = null;
			if(map.get("monthy") != null) {
				if(StringUtil.strToInt(map.get("monthy")) == 1) {
					monthy = "";
				}else {
					monthy = map.get("monthy");
				}
			}else {
				monthy = DateUtil.getYearMonthy();
			}
			PageInfo<AttendanceStatistics> pageInfo = hxhyAttendanceStatisticsService.statistics(
					StringUtil.strToInt(map.get("page")), StringUtil.strToInt(map.get("size")), monthy,
					map.get("userId"), map.get("name"));

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
	 * 获得统计表的月份
	 * 
	 * @return
	 */
	@RequestMapping(value = "/statistics/monthy", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResponse listAllStatistics(@RequestBody Map<String, String> map) {
		BaseResponse baseResponse = new BaseResponse();
		List<HxhyAttendanceStatistics> list = hxhyAttendanceStatisticsService.getMonthy();
		
		return baseResponse.setSuccess(null, list);
	}

}
