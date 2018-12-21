package com.hxhy.model.to;

import java.util.List;

public class AttendanceResp {

	private Integer errcode;//	返回码
	private String errmsg;//	对返回码的文本描述内容
	private boolean hasMore;//	分页返回参数，表示是否还有更多数据
	private List<AttendanceDetails> recordresult;//	考勤信息列表
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public boolean isHasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	public List<AttendanceDetails> getRecordresult() {
		return recordresult;
	}
	public void setRecordresult(List<AttendanceDetails> recordresult) {
		this.recordresult = recordresult;
	}
}
