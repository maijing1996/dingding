package com.hxhy.model.dto;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class AttendanceRecord {

	private Long id;
	private String monthy;
	private String check_type;//考勤类型（OnDuty：上班，OffDuty：下班）
	private String work_date;
	private String time_result;//时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡;leave:请假）
	private String location_result;
	private String user_check_time;
	private String source_type;//数据来源 （ATM:考勤机;BEACON:IBeacon;DING_ATM:钉钉考勤机;USER:用户打卡;BOSS:老板改签;APPROVE:审批系统;SYSTEM:考勤系统;AUTO_CHECK:自动打卡）
	private Integer timelong;
	private String name;
	private String nickname;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonthy() {
		return monthy;
	}
	public void setMonthy(String monthy) {
		this.monthy = monthy;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		if("OnDuty".equals(check_type)) {
			this.check_type = "上班";
		} else if("OffDuty".equals(check_type)) {
			this.check_type = "下班";
		}
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(Date work_date) {
		if(work_date != null) {
			this.work_date = DateUtil.format(work_date, DateUtil.FORMAT_YYYY_MM_dd);
		}else {
			this.work_date = null;
		}
	}
	public String getTime_result() {
		return time_result;
	}
	public void setTime_result(String time_result) {
		if("Normal".equals(time_result)) {
			this.time_result = "正常";
		} else if("Early".equals(time_result)) {
			this.time_result = "早退";
		} else if("Late".equals(time_result)) {
			this.time_result = "迟到";
		} else if("SeriousLate".equals(time_result)) {
			this.time_result = "严重迟到";
		} else if("Absenteeism".equals(time_result)) {
			this.time_result = "旷工迟到";
		} else if("NotSigned".equals(time_result)) {
			this.time_result = "未打卡";
		}
	}
	public String getLocation_result() {
		return location_result;
	}
	public void setLocation_result(String location_result) {
		if("Normal".equals(location_result)) {
			this.location_result = "范围内";
		} else if("Outside".equals(location_result)) {
			this.location_result = "范围外";
		} else if("NotSigned".equals(location_result)) {
			this.location_result = "未打卡";
		}
	}
	public String getUser_check_time() {
		return user_check_time;
	}
	public void setUser_check_time(Date user_check_time) {
		if(user_check_time != null) {
			this.user_check_time = DateUtil.format(user_check_time, DateUtil.FORMAT_YYYY_MM_dd_hh_mm_ss);
		}else {
			this.user_check_time = null;
		}
	}
	public String getSource_type() {
		return source_type;
	}
	public void setSource_type(String source_type) {
		if("ATM".equals(source_type)) {
			this.source_type = "考勤机";
		} else if("BEACON".equals(source_type)) {
			this.source_type = "IBeacon";
		} else if("DING_ATM".equals(source_type)) {
			this.source_type = "钉钉考勤机";
		} else if("USER".equals(source_type)) {
			this.source_type = "用户打卡";
		} else if("BOSS".equals(source_type)) {
			this.source_type = "老板改签";
		} else if("APPROVE".equals(source_type)) {
			this.source_type = "审批系统";
		} else if("SYSTEM".equals(source_type)) {
			this.source_type = "考勤系统";
		} else if("AUTO_CHECK".equals(source_type)) {
			this.source_type = "自动打卡";
		}
	}
	public Integer getTimelong() {
		return timelong;
	}
	public void setTimelong(Integer timelong) {
		this.timelong = timelong;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
