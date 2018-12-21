package com.hxhy.model.to;

import java.util.Date;

public class AttendanceDetails {
	
	private Long id;//	唯一标示ID
	private Long groupId;//	考勤组ID
	private Long planId;//	排班ID
	private Long recordId;//	打卡记录ID
	private Date workDate;//	工作日
	private String userId;//	用户ID
	private String checkType;//	考勤类型（OnDuty：上班，OffDuty：下班）
	private String timeResult;//	时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡）
	private String locationResult;//	位置结果（Normal:范围内；Outside:范围外；NotSigned:未打卡）
	private Long approveId;//	关联的审批id
	private String procInstId;//	关联的审批实例id
	private Date baseCheckTime;//	计算迟到和早退，基准时间
	private Date userCheckTime;//	实际打卡时间
	private String sourceType;//	数据来源 （ATM:考勤机;BEACON:IBeacon;DING_ATM:钉钉考勤机;USER:用户打卡;BOSS:老板改签;APPROVE:审批系统;SYSTEM:考勤系统;AUTO_CHECK:自动打卡）
	private String corpId;//
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getTimeResult() {
		return timeResult;
	}
	public void setTimeResult(String timeResult) {
		this.timeResult = timeResult;
	}
	public String getLocationResult() {
		return locationResult;
	}
	public void setLocationResult(String locationResult) {
		this.locationResult = locationResult;
	}
	public Long getApproveId() {
		return approveId;
	}
	public void setApproveId(Long approveId) {
		this.approveId = approveId;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	public Date getBaseCheckTime() {
		return baseCheckTime;
	}
	public void setBaseCheckTime(Date baseCheckTime) {
		this.baseCheckTime = baseCheckTime;
	}
	public Date getUserCheckTime() {
		return userCheckTime;
	}
	public void setUserCheckTime(Date userCheckTime) {
		this.userCheckTime = userCheckTime;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
}
