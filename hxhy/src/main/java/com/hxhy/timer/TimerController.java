package com.hxhy.timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hxhy.model.po.HxhyAttendance;
import com.hxhy.model.po.HxhyDepartment;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.model.po.HxhyWorkTime;
import com.hxhy.model.to.AttendanceDetails;
import com.hxhy.model.to.DepartmentDetails;
import com.hxhy.model.to.UserDetails;
import com.hxhy.service.HxhyAttendanceService;
import com.hxhy.service.HxhyDepartmentService;
import com.hxhy.service.HxhyManagerService;
import com.hxhy.service.HxhyWorkTimeService;
import com.hxhy.service.other.DingtalkService;
import com.hxhy.service.other.HxhyService;
import com.hxhy.util.DateUtil;
import com.hxhy.util.MD5Util;

@Component
public class TimerController {

	private Logger logger = LoggerFactory.getLogger(TimerController.class);
	
	@Autowired
	private DingtalkService dingtalkService;
	@Autowired
	private HxhyAttendanceService hxhyAttendanceService;
	@Autowired
	private HxhyManagerService hxhyManagerService;
	@Autowired
	private HxhyWorkTimeService hxhyWorkTimeService;
	@Autowired
	private HxhyDepartmentService hxhyDepartmentService;
	@Autowired
	private HxhyService hxhyService;
	
	/**
	 * 每日获得打卡信息
	 */
	@Scheduled(cron=" 0 15 3 * * ?") //测试时间"0 0/1 * * * ?  正式时间：    0 15 3 * * ?"
    public void executeGetAttendance() {
		try {
			//获得token
			String token = dingtalkService.getAccessToken();
			
			HxhyManager hxhyManager = new HxhyManager();
			hxhyManager.setIs_work(1);
			hxhyManager.setIs_attendance(1);
			List<HxhyManager> list = hxhyManagerService.getAll(hxhyManager, null);
			List<String> list2 = new ArrayList<String>();
			for(HxhyManager manager : list) {
				list2.add(manager.getUser_id());
			}
			
			if(list2.size() > 0) {
				String yesterday = DateUtil.format(DateUtil.getDistanceByDay(new Date(), -1), DateUtil.FORMAT_YYYY_MM_dd);
				String dateFrom = yesterday + " 00:00:00";
				String dateTo = yesterday + " 23:59:59";
				List<AttendanceDetails> list3 = dingtalkService.getAttendance(token, dateFrom, dateTo, list2, 50L);
				if(list3.size() > 0) {
					Long departmentId = null;
					List<HxhyAttendance> list4 = new ArrayList<HxhyAttendance>();
					List<HxhyWorkTime> workTimeList = new ArrayList<HxhyWorkTime>();
					
					for(AttendanceDetails attendanceDetails : list3) {
						boolean boo = true;
						for(HxhyAttendance hxhyAttendance : list4) {
							if(hxhyAttendance.getUser_id().equals(attendanceDetails.getUserId())
									&& hxhyAttendance.getCheck_type().equals(attendanceDetails.getCheckType())
									&& hxhyAttendance.getUser_check_time().getTime() == attendanceDetails.getUserCheckTime().getTime()) {
								boo = false;
							}
						}
						
						if(boo) {
							HxhyAttendance hxhyAttendance = new HxhyAttendance();
							hxhyAttendance.setBase_check_time(attendanceDetails.getBaseCheckTime());
							hxhyAttendance.setCheck_type(attendanceDetails.getCheckType());
							hxhyAttendance.setLocation_result(attendanceDetails.getLocationResult());
							hxhyAttendance.setMonthy(DateUtil.format(new Date(), DateUtil.FORMAT_YYYYMM));
							hxhyAttendance.setSource_type(attendanceDetails.getSourceType());
							hxhyAttendance.setTime_result(attendanceDetails.getTimeResult());
							hxhyAttendance.setUser_check_time(attendanceDetails.getUserCheckTime());
							hxhyAttendance.setSource_type(attendanceDetails.getSourceType());
							hxhyAttendance.setUser_id(attendanceDetails.getUserId());
							hxhyAttendance.setIs_supplement(0);
							hxhyAttendance.setWork_date(DateUtil.format(attendanceDetails.getWorkDate(), DateUtil.FORMAT_YYYY_MM_dd));
							
							for(HxhyManager manager : list) {
								if(manager.getUser_id().equals(attendanceDetails.getUserId())) {
									hxhyAttendance.setDepartment_id(manager.getDepartment_id());
									
									//获得上班时间详情，迟到早退等会使用到，并封装
									if(departmentId == null || departmentId != manager.getDepartment_id()) {
										departmentId = manager.getDepartment_id();
										workTimeList = hxhyWorkTimeService.getByDepartmentId(departmentId);
									}
									break;
								}
							}
							
							if("OnDuty".equals(attendanceDetails.getCheckType())) {
								//上班
								Long time = (attendanceDetails.getUserCheckTime().getTime() - attendanceDetails.getBaseCheckTime().getTime());
								if(time > 0) {
									Long val = 0L;
									Long objective = DateUtil.parseDate(DateUtil.format(attendanceDetails.getUserCheckTime(), "HH:mm:ss"), "HH:mm:ss").getTime();
									for(HxhyWorkTime hxhyWorkTime : workTimeList) {
										Long origin = DateUtil.parseDate(hxhyWorkTime.getOn_duty(), "HH:mm").getTime();
										Long destination = DateUtil.parseDate(hxhyWorkTime.getOff_duty(), "HH:mm").getTime();
										if(origin <= objective && objective <= destination) {
											val = (val + (objective - origin))/60000;
											break;
										} else if(objective > destination) {
											val = val + (destination - origin);
										} else if(objective <= origin) {
											break;
										}
									}
									hxhyAttendance.setTimelong(val.intValue());
								} else {
									hxhyAttendance.setTimelong(0);
								}
							} else if("OffDuty".equals(attendanceDetails.getCheckType())) {
								//下班
								Long time = (attendanceDetails.getBaseCheckTime().getTime() - attendanceDetails.getUserCheckTime().getTime());
								if(time > 0) {
									Long val = 0L;
									Long objective = DateUtil.parseDate(DateUtil.format(attendanceDetails.getUserCheckTime(), "HH:mm:ss"), "HH:mm:ss").getTime();
									for(HxhyWorkTime hxhyWorkTime : workTimeList) {
										Long origin = DateUtil.parseDate(hxhyWorkTime.getOn_duty(), "HH:mm").getTime();
										Long destination = DateUtil.parseDate(hxhyWorkTime.getOff_duty(), "HH:mm").getTime();
										if(destination <= objective) {
											
										} else if(origin <= objective && objective < destination) {
											val = val + (destination - objective);
										} else if(objective < origin) {
											val = val + (destination - origin);
										}
									}
									
									Long val2 = val/60000;
									hxhyAttendance.setTimelong(val2.intValue());
								} else {
									hxhyAttendance.setTimelong(0);
								}
							} else {
								hxhyAttendance.setTimelong(0);
							}
							list4.add(hxhyAttendance);
						}
					}
					
					hxhyAttendanceService.insertList(list4);
				}
				
				logger.info("已成功获取了，" + dateFrom +" -- "+ dateTo + " 的考勤信息！");
			}
		} catch (Exception e) {
			logger.error(JSONObject.toJSONString(e));
		}
    }
	
	/**
	 * 每周更新部门人员信息
	 */
	@Scheduled(cron=" 0 0 5 ? * 7") //测试时间"0 0/1 * * * ?  正式时间： 0 0 5 ? * 7"
    public void executeGetUser() {
		try {
			//获得token
			String token = dingtalkService.getAccessToken();
			
			//获得系统内的部门信息
			List<HxhyDepartment> listDep = hxhyDepartmentService.getAll(null, null);
			//获得部门信息
			List<DepartmentDetails> listDepartment = dingtalkService.listDepartment(token);
			List<Long> listIds = new ArrayList<Long>();
			if(listDepartment != null && listDepartment.size() > 0) {
				//封装保存部门信息
				List<HxhyDepartment> listSaveDep = new ArrayList<HxhyDepartment>();
				for(DepartmentDetails departmentDetails : listDepartment) {
					boolean boo = true;
					if(listDep.size() > 0) {
						for(int i=0; i < listDep.size(); i++) {
							HxhyDepartment hxhyDepartment2 = listDep.get(i);
							if(departmentDetails.getId() == hxhyDepartment2.getDepartment_id()) {
								HxhyDepartment hxhyDepartment = new HxhyDepartment();
								hxhyDepartment.setId(hxhyDepartment2.getId());
								hxhyDepartment.setName(departmentDetails.getName());
								hxhyDepartment.setDepartment_id(departmentDetails.getId());
								hxhyDepartment.setParentid(departmentDetails.getParentid());
								hxhyDepartment.setAuto_add_user(departmentDetails.isAutoAddUser());
								hxhyDepartment.setCreate_dept_group(departmentDetails.isCreateDeptGroup());
								
								boo = false;
								listDep.remove(i);
								hxhyDepartmentService.update(hxhyDepartment);
							}
						}
					}
					
					if(boo) {
						HxhyDepartment hxhyDepartment = new HxhyDepartment();
						hxhyDepartment.setName(departmentDetails.getName());
						hxhyDepartment.setDepartment_id(departmentDetails.getId());
						hxhyDepartment.setParentid(departmentDetails.getParentid());
						hxhyDepartment.setAuto_add_user(departmentDetails.isAutoAddUser());
						hxhyDepartment.setCreate_dept_group(departmentDetails.isCreateDeptGroup());
						
						listSaveDep.add(hxhyDepartment);
					}
					
					listIds.add(departmentDetails.getId());
				}
				
				if(listDep.size() > 0) {
					hxhyDepartmentService.deleteByIds(listDep);
				}
				
				if(listSaveDep.size() > 0) {
					hxhyDepartmentService.insertList(listSaveDep);
				}
			}
			
			//获得系统所有的用户信息
			List<HxhyManager> listUs = hxhyManagerService.getAll(null, null);
			//获得所有的用户信息
			if(listIds.size() > 0) {
				//封装保存用户信息
				List<HxhyManager> listSaveUser = new ArrayList<HxhyManager>();
				for(Long id : listIds) {
					//获得部门用户信息
					List<UserDetails> listUser = dingtalkService.departmentUser(token, id);
					if(listUser != null && listUser.size() > 0) {
						//封装保存用户信息
						for(int j=0; j < listUser.size(); j++) {
							UserDetails userDetails = listUser.get(j);
							boolean boo = true;
							if(listUs.size() > 0) {
								for(int i=0; i < listUs.size(); i++) {
									HxhyManager hxhyManager = listUs.get(i);
									if(userDetails.getUserid().equals(hxhyManager.getUser_id())) {
										boo = false;
										listUs.remove(i);
										break;
									}
								}
							}
								
							if(boo) {
								HxhyManager hxhyManager = new HxhyManager();
								hxhyManager.setAccount("user"+new Date().getTime()+j);
								hxhyManager.setAdd_date(new Date());
								hxhyManager.setDepartment_id(id);
								hxhyManager.setName(userDetails.getName());
								hxhyManager.setUser_id(userDetails.getUserid());
								hxhyManager.setRole_id(3L);//数据库有默认值
								hxhyManager.setIs_alter(0);//数据库有默认值
								hxhyManager.setIs_work(1);
								hxhyManager.setIs_official(0);
								hxhyManager.setBasics_money(3000.0);
								hxhyManager.setMoney(5000.0);
								hxhyManager.setIs_attendance(1);
								String passwd = MD5Util.MD5("123456");
								hxhyManager.setPasswd(passwd);
								
								listSaveUser.add(hxhyManager);
							}
						}
					}
				}
				
				if(listUs.size() > 0) {
					hxhyManagerService.deleteByIds(listUs);
				}
				
				if(listSaveUser.size() > 0) {
					hxhyManagerService.insertList(listSaveUser);
				}
			}
		} catch (Exception e) {
			logger.error(JSONObject.toJSONString(e));
		}
    }
    
	/**
	 * 考勤月度统计
	 */
	//@Scheduled(cron="0 0/1 * * * ?") //测试时间"0 0/1 * * * ?  and  0 15 3 * * ?  正式时间： 0 0 6 1 * ?"
    public void executeAttendanceMonthly() {
		try {
			String res = hxhyService.attendanceStatistics("201811");
			logger.info(res);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * 薪酬结算
	 */
    //@Scheduled(cron="0 0/1 * * * ?") //测试时间"0 0/1 * * * ?  正式时间： 0 0 6 ? * 7"
    public void executePayroll() {
		try {
			String res = hxhyService.payrollCalculation("201811");
			logger.info(res);
		} catch (Exception e) {
			
		}
	}
}
