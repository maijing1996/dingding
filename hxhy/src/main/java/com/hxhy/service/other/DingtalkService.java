package com.hxhy.service.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hxhy.conf.MySiteSetting;
import com.hxhy.model.to.AccessTokenResp;
import com.hxhy.model.to.AttendanceDetails;
import com.hxhy.model.to.AttendanceResp;
import com.hxhy.model.to.DepartmentDetails;
import com.hxhy.model.to.DepartmentResp;
import com.hxhy.model.to.UserDetails;
import com.hxhy.model.to.UserResp;
import com.hxhy.util.FinalParamsUtil;
import com.hxhy.util.HttpUtil;

@Service
public class DingtalkService {

	private Logger logger = LoggerFactory.getLogger(DingtalkService.class);
	
	@Autowired
	private MySiteSetting mySiteSetting;
	
	/**
	 * 获取钉钉的accessToken
	 * @return
	 */
	public String getAccessToken() {
		
		String url = FinalParamsUtil.DING_TALK_ACCESS_TOKEN_URL + "?corpid=" + mySiteSetting.getCorpid() + "&corpsecret=" + mySiteSetting.getCorpsecret();
		AccessTokenResp accessTokenResp = HttpUtil.httpRequest(url, "GET", null, AccessTokenResp.class);
		if(accessTokenResp.getErrcode() == 0) {
			return accessTokenResp.getAccess_token();
		} else {
			logger.info("获取钉钉的 accessToken 失败，失败码："+accessTokenResp.getErrcode()+"，失败原因："+accessTokenResp.getErrmsg());
			return accessTokenResp.getErrmsg();
		}
	}
	
	/**
	 * 获得部门列表信息
	 * @param token
	 * @return
	 */
	public List<DepartmentDetails> listDepartment(String token) {
		
		String url = FinalParamsUtil.DEPARTMENT_URL + "?access_token=" + token;
		DepartmentResp departmentResp = HttpUtil.httpRequest(url, "GET", null, DepartmentResp.class);
		if(departmentResp.getErrcode() == 0) {
			return departmentResp.getDepartment();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得特定部门的用户信息
	 * @param token
	 * @param departmentId
	 * @return
	 */
	public List<UserDetails> departmentUser(String token, Long departmentId) {
		
		String url = FinalParamsUtil.SIMPLELIST + "?access_token=" + token + "&department_id=" + departmentId;
		UserResp userResp = HttpUtil.httpRequest(url, "GET", null, UserResp.class);
		if(userResp.getErrcode() == 0 && userResp.getUserlist() != null && !userResp.getUserlist().isEmpty()) {
			return userResp.getUserlist();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得考勤信息
	 * 
	 * @param token
	 * @param dateFrom
	 * @param dateTo
	 * @param list
	 * @param limit
	 * @return
	 */
	public List<AttendanceDetails> getAttendance(String token, String dateFrom, String dateTo, List<String> list, Long limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workDateFrom", dateFrom);
		map.put("workDateTo", dateTo);
		map.put("userIdList", list);
		map.put("offset", 0);
		map.put("limit", limit);
		
		List<AttendanceDetails> list2 = new ArrayList<AttendanceDetails>();
		boolean hasMore = true;
		int i=1;
		while(hasMore) {
			String url = FinalParamsUtil.ATTENDANCE + "?access_token=" + token;
			AttendanceResp attendanceResp = HttpUtil.httpRequestInputJson(url, JSONObject.toJSON(map).toString(), AttendanceResp.class);
			if(attendanceResp.getErrcode() == 0) {
				
				map.put("offset", i);
				i++;
				hasMore = attendanceResp.isHasMore();
				list2.addAll(attendanceResp.getRecordresult());
			} else {
				logger.error(JSONObject.toJSONString(attendanceResp.getErrmsg()));
				hasMore = false;
			}
		}
		
		return list2;
	}
	
	/*public void leave(String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", "025058684339723414");
		map.put("from_date", DateUtil.parseDate("2018-11-02 00:00:00"));
		map.put("to_date", DateUtil.parseDate("2018-11-03 00:00:00"));
		map.put("method", "POST");
		String url = FinalParamsUtil.LEAVE +"?userid=025058684339723414&from_date=2018-11-02 00:00:00&to_date=2018-11-03 00:00:00";
		HttpUtil.httpRequestInputJson(FinalParamsUtil.LEAVE, XmlUtil.toXML(map, HashMap.class), null);
	}*/
}
