package com.hxhy.service.other;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxhy.model.dto.StatisticsAmount;
import com.hxhy.model.po.HxhyAttendance;
import com.hxhy.model.po.HxhyAttendanceStatistics;
import com.hxhy.model.po.HxhyDelayWorkScheme;
import com.hxhy.model.po.HxhyFinance;
import com.hxhy.model.po.HxhyLeaveRecord;
import com.hxhy.model.po.HxhyManager;
import com.hxhy.model.po.HxhyPayroll;
import com.hxhy.model.po.HxhyRevenue;
import com.hxhy.model.po.HxhyWorkTime;
import com.hxhy.service.HxhyAttendanceService;
import com.hxhy.service.HxhyAttendanceStatisticsService;
import com.hxhy.service.HxhyDelayWorkSchemeService;
import com.hxhy.service.HxhyFinanceService;
import com.hxhy.service.HxhyHolidayService;
import com.hxhy.service.HxhyLeaveRecordService;
import com.hxhy.service.HxhyManagerService;
import com.hxhy.service.HxhyPayrollService;
import com.hxhy.service.HxhyRevenueService;
import com.hxhy.service.HxhyWorkSchemeService;
import com.hxhy.service.HxhyWorkTimeService;
import com.hxhy.util.DateUtil;

@Service
public class HxhyService {

	private Logger logger = LoggerFactory.getLogger(HxhyService.class);
	
	@Autowired
	private HxhyAttendanceService hxhyAttendanceService;
	@Autowired
	private HxhyLeaveRecordService hxhyLeaveRecordService;
	@Autowired
	private HxhyHolidayService hxhyHolidayService;
	@Autowired
	private HxhyWorkSchemeService hxhyWorkSchemeService;
	@Autowired
	private HxhyManagerService hxhyManagerService;
	@Autowired
	private HxhyDelayWorkSchemeService hxhyDelayWorkSchemeService;
	@Autowired
	private HxhyWorkTimeService hxhyWorkTimeService;
	@Autowired
	private HxhyAttendanceStatisticsService hxhyAttendanceStatisticsService;
	@Autowired
	private HxhyPayrollService hxhyPayrollService;
	@Autowired
	private HxhyFinanceService hxhyFinanceService;
	@Autowired
	private HxhyRevenueService hxhyRevenueService;
	
	/**
	 * 考勤统计，没人每月一条，可以统计多次，以最后统计为准
	 * 
	 * @param monthy  YYYYMM
	 * @return
	 */
	public String attendanceStatistics(String monthy) {
		
		try {
			//获得考勤统计信息，如果存在则覆盖，不存在则不处理
			HxhyAttendanceStatistics hxhyAttendanceStatistics = new HxhyAttendanceStatistics();
			hxhyAttendanceStatistics.setMonthy(monthy);
			List<HxhyAttendanceStatistics> statistiscList = hxhyAttendanceStatisticsService.getAll(hxhyAttendanceStatistics, null);
			
			//获得用户考勤信息
			HxhyAttendance hxhyAttendance = new HxhyAttendance();
			hxhyAttendance.setMonthy(monthy);
			List<HxhyAttendance> attendanceList = hxhyAttendanceService.getAll(hxhyAttendance, "user_id ASC, work_date ASC, check_type DESC");
			
			if(attendanceList.size() > 0) {
				
				//逻辑涉及的基础参数
				Long leaveStart=0L, leaveEnd=0L, departmentId=null;
				String userId = null, verDate = null;
				Double deductMoney = 0.0, leaveMoney = 0.0, workMoney = 0.0, basicsMony = 0.0, money=0.0, bonus=0.0;
				boolean isEnd = false, isSys = false, isStart = false, isAdd = true, isNotLeave = true, isNotCalculate=true;
				int earlyDate=0, earlyTime=0, notSigned=0, leaveTime=0, leaveDate=0, lateDate=0, lateTime=0,
						workDate=0, theoryDate=0, allWorkDate=0, type=0, isPerfect=1, holiday = 0, workday = 0,
						amount=0, delayTime=0, intermission=0;
				//开始核对统计，新增加入列表，修改直接更新，因为没有批量更新的操作
				List<HxhyAttendanceStatistics> saveStatisticsList = new ArrayList<HxhyAttendanceStatistics>();
				HxhyAttendanceStatistics hxhyAttendanceStatistics2 = new HxhyAttendanceStatistics();
				List<HxhyLeaveRecord> leaveList = new ArrayList<HxhyLeaveRecord>();
				List<HxhyWorkTime> workTimeList = new ArrayList<HxhyWorkTime>();
				
				//获得当月节假日信息
				List<StatisticsAmount> list = hxhyHolidayService.getStatisics(monthy);
				if(list != null && !list.isEmpty()) {
					for(StatisticsAmount statisticsAmount : list) {
						if(statisticsAmount.getState() == 1) {
							holiday++;
						}
						if(statisticsAmount.getState() == 2) {
							workday++;
						}
					}
				}
				
				//获得延迟上班方案信息
				List<HxhyDelayWorkScheme> delayList = hxhyDelayWorkSchemeService.getAll(null, "type ASC, work_date ASC");
				
				//获得所有的用户信息
				HxhyManager hxhyManager = new HxhyManager();
				hxhyManager.setIs_attendance(1);
				hxhyManager.setIs_work(1);
				List<HxhyManager> userList = hxhyManagerService.getAll(hxhyManager, null);
				
				for(HxhyAttendance hxhyAttendance2 : attendanceList) {
					
					//获得用户的基础信息
					if(userId == null) {
						userId = hxhyAttendance2.getUser_id();
						hxhyAttendanceStatistics2.setUser_id(userId);
						verDate = hxhyAttendance2.getWork_date();
						leaveList = hxhyLeaveRecordService.getByUserId(userId, monthy);
						type = hxhyWorkSchemeService.getTypeByUserId(userId);
						theoryDate = getTheoryDate(type, monthy);
						allWorkDate = theoryDate + workday - holiday;
						for(HxhyManager hxhyManager2 : userList) {
							if(hxhyManager2.getUser_id().equals(userId)) {
								money=hxhyManager2.getBasics_money();
								bonus=hxhyManager2.getMoney();
								basicsMony = (money + bonus) / theoryDate;
								basicsMony = new BigDecimal(basicsMony).setScale(4, RoundingMode.HALF_UP).doubleValue();
							}
						}
					}
					//判断是不是同一个人
					//时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；Absenteeism:旷工迟到； NotSigned:未打卡,leave：请假）
					if(userId.equals(hxhyAttendance2.getUser_id())) {
						//统计处理
						//判断是否是同一天
						if(!hxhyAttendance2.getWork_date().equals(verDate)) {
							//不是同一天
							//判断上一天的处理是否未结束
							if(!isEnd) {
								//未结束
								//判断是否是下班卡
								if("OffDuty".equals(hxhyAttendance2.getCheck_type())) {
									//下班卡
									//判断是否是系统打卡
									if("SYSTEM".equals(hxhyAttendance2.getSource_type())) {
										//判断同一天是否有上班操作
										if(isStart) {
											//是否是系统打卡
											//判断是否是系统打卡，非系统打卡进行下面操作
											if(!isSys) {
												notSigned++;
												isPerfect = 0;
												amount++;
												workDate++;
											} else {
												isPerfect = 0;
											}
										}
									} else {
										//判断同一天是否有上班操作
										if(!isStart) {
											//没有上班打卡则加一次缺卡记录
											notSigned++;
											isPerfect = 0;
											amount++;
											workDate++;
										} else {
											//判断是否是系统打卡,当时系统打卡的时候进行下方操作
											if(isSys) {
												workDate++;
												notSigned++;
												isPerfect = 0;
												amount++;
											}
										}
										
										//继续后续操作
										if("Early".equals(hxhyAttendance2.getTime_result())) {
											
											int longTime = hxhyAttendance2.getTimelong() - intermission;
											if(longTime <= 3) {
												//
												isPerfect = 0;
											} else {
												amount++;
												isPerfect = 0;
												if(amount <= 5) {
													if(longTime <= 60) {
														earlyDate++;
													} else if(longTime > 60) {
														earlyDate++;
														deductMoney = deductMoney + (double)((longTime/60)*basicsMony)/9;
													}
												} else {
													earlyDate++;
													deductMoney = deductMoney + calculateMoney(longTime, basicsMony);
												}
											}
										} else if("NotSigned".equals(hxhyAttendance2.getTime_result())) {
											
											notSigned++;
											isPerfect = 0;
										} else if("Normal".equals(hxhyAttendance2.getTime_result())) {
											
											if(delayList != null && !delayList.isEmpty()) {
												for(HxhyDelayWorkScheme hxhyDelayWorkScheme : delayList) {
													if(hxhyDelayWorkScheme.getType() == 1) {
														Long destination = DateUtil.parseDate(hxhyDelayWorkScheme.getWork_date(), "HH:mm").getTime();
														Long objective = DateUtil.parseDate(DateUtil.format(hxhyAttendance2.getUser_check_time(), "HH:mm"), "HH:mm").getTime();
														if(destination <= objective) {
															delayTime = hxhyDelayWorkScheme.getDelay_time();
															break;
														} else {
															delayTime = 0;
														}
													}
												}
											}
										}
										
										//工作日加一天，并重置参数
										if(isAdd) {
											workDate++;
											isAdd = false;
										}
									}
									isEnd = true;
									continue;
								} else {
									//未打卡及新的一天的情况
									//判断上一天是否有上班操作
									if(isStart) {
										//判断是否是系统打卡，下面的操作是非系统打卡
										if(!isSys) {
											//判断是否已经计算过上班天数
											if(isAdd) {
												notSigned++;
												workDate++;
												isPerfect = 0;
												amount++;
											}
										}
									}
								}
								
							}
							isEnd = false; isSys = false; isStart = false; isAdd = true; isNotLeave = true; isNotCalculate=true;
							intermission = 0;
						}
						
						//请假校验
						if(isNotLeave){
							if(leaveList != null && !leaveList.isEmpty()) {
								//当天是否已经计算过
								if(isNotCalculate) {
									for(HxhyLeaveRecord hxhyLeaveRecord : leaveList) {
										String startDateStr = DateUtil.format(hxhyLeaveRecord.getStart_date(), DateUtil.FORMAT_YYYY_MM_dd);
										if(startDateStr.equals(hxhyAttendance2.getWork_date())) {
												
											isNotLeave = false;
											leaveStart = DateUtil.parseDate(DateUtil.format(hxhyLeaveRecord.getStart_date(), "HH:mm:ss"), "HH:mm:ss").getTime();
											leaveEnd = DateUtil.parseDate(DateUtil.format(hxhyLeaveRecord.getEnd_date(), "HH:mm:ss"), "HH:mm:ss").getTime();
											leaveTime = leaveTime + hxhyLeaveRecord.getLongtime();
											isPerfect = 0;
											leaveDate++;
											isNotCalculate = false;
											break;
										}
									}
								}
							}
						}
						
						//是否补卡操作
						if(hxhyAttendance2.getIs_supplement() == 1) {
							isPerfect = 0;
						}
						
						//判断当天是否有请假
						if(!isNotLeave) {
							//获得上班时间详情，迟到早退等会使用到，并封装
							if(departmentId == null || departmentId != hxhyAttendance2.getDepartment_id()) {
								departmentId = hxhyAttendance2.getDepartment_id();
								workTimeList = hxhyWorkTimeService.getByDepartmentId(departmentId);
							}
							//请假处理
							long userTime = DateUtil.parseDate(DateUtil.format(hxhyAttendance2.getUser_check_time(), "HH:mm:ss"), "HH:mm:ss").getTime();
							if("OnDuty".equals(hxhyAttendance2.getCheck_type())) {
								if(leaveStart <= userTime) {
									intermission = intermission + calculateLeaveOnDuty(workTimeList, userTime, leaveStart);
								}
							} else if("OffDuty".equals(hxhyAttendance2.getCheck_type())) {
								if(userTime <= leaveEnd) {
									intermission = intermission + calculateLeaveOffDuty(workTimeList, userTime, leaveEnd);
								}
							}
						}
						
						//判断是上班还是下班
						if("OnDuty".equals(hxhyAttendance2.getCheck_type())) {
							
							//日期重置
							verDate = hxhyAttendance2.getWork_date();
							//修改状态
							isStart = true;
							//判断是否是系统打卡
							if("SYSTEM".equals(hxhyAttendance2.getSource_type())) {
								isSys = true;
							} else {
								if("Late".equals(hxhyAttendance2.getTime_result()) || "SeriousLate".equals(hxhyAttendance2.getTime_result())
										|| "Absenteeism".equals(hxhyAttendance2.getTime_result())) {
									
									int longTime = hxhyAttendance2.getTimelong() - delayTime;
									longTime = longTime - intermission;
									if(longTime <= 3) {
										//
										isPerfect = 0;
									} else {
										amount++;
										isPerfect = 0;
										if(amount <= 5) {
											if(longTime <= 60) {
												lateDate++;
											} else if(longTime > 60) {
												lateDate++;
												deductMoney = deductMoney + ((double)(longTime/60)*basicsMony)/9;
											}
										} else {
											lateDate++;
											deductMoney = deductMoney + calculateMoney(longTime, basicsMony);
										}
									}
								}
							}
						} else if("OffDuty".equals(hxhyAttendance2.getCheck_type())) {
							
							//判断是否是系统打卡
							if("SYSTEM".equals(hxhyAttendance2.getSource_type())) {
								//判断同一天是否有上班操作
								if(isStart) {
									//是否是系统打卡
									//判断是否是系统打卡，非系统打卡进行下面操作
									if(!isSys) {
										notSigned++;
										isPerfect = 0;
										amount++;
										workDate++;
									} else {
										isPerfect = 0;
									}
								}
							} else {
								//判断同一天是否有上班操作
								if(!isStart) {
									//没有上班打卡则加一次缺卡记录
									notSigned++;
									isPerfect = 0;
									amount++;
									workDate++;
								} else {
									//判断是否是系统打卡,当时系统打卡的时候进行下方操作
									if(isSys) {
										workDate++;
										notSigned++;
										isPerfect = 0;
										amount++;
									}
								}
								
								//继续后续操作
								if("Early".equals(hxhyAttendance2.getTime_result())) {
									
									int longTime = hxhyAttendance2.getTimelong() - intermission;
									if(longTime <= 3) {
										//
										isPerfect = 0;
									} else {
										amount++;
										isPerfect = 0;
										if(amount <= 5) {
											if(longTime <= 60) {
												earlyDate++;
											} else if(longTime > 60) {
												earlyDate++;
												deductMoney = deductMoney + (double)((longTime/60)*basicsMony)/9;
											}
										} else {
											earlyDate++;
											deductMoney = deductMoney + calculateMoney(longTime, basicsMony);
										}
									}
								} else if("NotSigned".equals(hxhyAttendance2.getTime_result())) {
									
									notSigned++;
									isPerfect = 0;
								} else if("Normal".equals(hxhyAttendance2.getTime_result())) {
									
									if(delayList != null && !delayList.isEmpty()) {
										for(HxhyDelayWorkScheme hxhyDelayWorkScheme : delayList) {
											if(hxhyDelayWorkScheme.getType() == 1) {
												Long destination = DateUtil.parseDate(hxhyDelayWorkScheme.getWork_date(), "HH:mm").getTime();
												Long objective = DateUtil.parseDate(DateUtil.format(hxhyAttendance2.getUser_check_time(), "HH:mm"), "HH:mm").getTime();
												if(destination <= objective) {
													delayTime = hxhyDelayWorkScheme.getDelay_time();
													break;
												} else {
													delayTime = 0;
												}
											}
										}
									}
								}
								
								//工作日加一天，并重置参数
								if(isAdd) {
									workDate++;
									isAdd = false;
								}
							}
							isEnd = true;
						}
					} else {
						//判断上一天的处理是否未结束
						if(!isEnd) {
							//未结束
							//判断上一天是否有上班操作
							if(isStart) {
								//判断是否是系统打卡，下面的操作是非系统打卡
								if(!isSys) {
									//判断是否已经计算过上班天数
									if(isAdd) {
										notSigned++;
										workDate++;
										isPerfect = 0;
										amount++;
									}
								}
							}
						}
						
						//全勤奖、请假、早退和迟到最总薪酬计算
						deductMoney = new BigDecimal(deductMoney).setScale(2, RoundingMode.HALF_UP).doubleValue();
						leaveMoney = (basicsMony*leaveTime)/(60*9);
						leaveMoney = new BigDecimal(leaveMoney).setScale(2, RoundingMode.HALF_UP).doubleValue();
						if(isPerfect == 1) {
							workMoney = 200.0;
						}
						//参数封装
						hxhyAttendanceStatistics2.setMonthy(monthy);
						hxhyAttendanceStatistics2.setEarly_date(earlyDate);
						hxhyAttendanceStatistics2.setEarly_time(earlyTime);
						hxhyAttendanceStatistics2.setLate_date(lateDate);
						hxhyAttendanceStatistics2.setLate_time(lateTime);
						hxhyAttendanceStatistics2.setLeave_date(leaveDate);
						hxhyAttendanceStatistics2.setLeave_time(leaveTime);
						hxhyAttendanceStatistics2.setNot_signed(notSigned);
						hxhyAttendanceStatistics2.setWork_date(workDate);
						hxhyAttendanceStatistics2.setAll_date(allWorkDate);
						hxhyAttendanceStatistics2.setTheory_date(theoryDate);
						hxhyAttendanceStatistics2.setDeduct_money(deductMoney);
						hxhyAttendanceStatistics2.setLeave_money(leaveMoney);
						hxhyAttendanceStatistics2.setWork_money(workMoney);
						hxhyAttendanceStatistics2.setIs_perfect(isPerfect);
						hxhyAttendanceStatistics2.setBasics_money(money);
						hxhyAttendanceStatistics2.setMoney(bonus);
						hxhyAttendanceStatistics2.setAdd_date(new Date());
						
						//保存或更新处理
						if(statistiscList.size() > 0) {
							boolean isSave = true;
							for(HxhyAttendanceStatistics hxhyAttendanceStatistics3 : statistiscList) {
								if(hxhyAttendanceStatistics3.getUser_id().equals(hxhyAttendanceStatistics2.getUser_id())) {
									
									isSave = false;
									hxhyAttendanceStatistics2.setId(hxhyAttendanceStatistics3.getId());
									hxhyAttendanceStatisticsService.update(hxhyAttendanceStatistics2);
								}
							}
							
							if(isSave) {
								saveStatisticsList.add(hxhyAttendanceStatistics2);
							}
						} else {
							saveStatisticsList.add(hxhyAttendanceStatistics2);
						}
						
						//重置
						//long
						leaveStart=0L; leaveEnd=0L;
						//double
						deductMoney = 0.0; leaveMoney = 0.0; workMoney = 0.0;
						//bolean
						isEnd = false; isSys = false; isStart = false; isAdd = true; isNotLeave = true; isNotCalculate=true;
						//int
						earlyDate=0; earlyTime=0; notSigned=0; leaveTime=0; leaveDate=0; lateDate=0; lateTime=0; workDate=0;
						isPerfect=1; amount=0; intermission=0;
						//obj
						hxhyAttendanceStatistics2 = new HxhyAttendanceStatistics();
						userId = hxhyAttendance2.getUser_id();
						hxhyAttendanceStatistics2.setUser_id(userId);
						verDate = hxhyAttendance2.getWork_date();
						leaveList = hxhyLeaveRecordService.getByUserId(userId, monthy);
						int type2 = hxhyWorkSchemeService.getTypeByUserId(userId);
						if(type2 != type) {
							type = type2;
							theoryDate = getTheoryDate(type, monthy);
							allWorkDate = theoryDate + workday - holiday;
						}
						for(HxhyManager hxhyManager2 : userList) {
							if(hxhyManager2.getUser_id().equals(userId)) {
								money=hxhyManager2.getBasics_money();
								bonus=hxhyManager2.getMoney();
								basicsMony = (money + bonus) / theoryDate;
								basicsMony = new BigDecimal(basicsMony).setScale(4, RoundingMode.HALF_UP).doubleValue();
							}
						}
						
						//统计处理
						//请假校验
						if(isNotLeave){
							if(leaveList != null && !leaveList.isEmpty()) {
								//当天是否已经计算过
								if(isNotCalculate) {
									for(HxhyLeaveRecord hxhyLeaveRecord : leaveList) {
										String startDateStr = DateUtil.format(hxhyLeaveRecord.getStart_date(), DateUtil.FORMAT_YYYY_MM_dd);
										if(startDateStr.equals(hxhyAttendance2.getWork_date())) {
												
											isNotLeave = false;
											leaveStart = DateUtil.parseDate(DateUtil.format(hxhyLeaveRecord.getStart_date(), "HH:mm:ss"), "HH:mm:ss").getTime();
											leaveEnd = DateUtil.parseDate(DateUtil.format(hxhyLeaveRecord.getEnd_date(), "HH:mm:ss"), "HH:mm:ss").getTime();
											leaveTime = leaveTime + hxhyLeaveRecord.getLongtime();
											isPerfect = 0;
											leaveDate++;
											isNotCalculate = false;
											break;
										}
									}
								}
							}
						}
						
						//是否补卡操作
						if(hxhyAttendance2.getIs_supplement() == 1) {
							isPerfect = 0;
						}
						
						//判断当天是否有请假
						if(!isNotLeave) {
							//获得上班时间详情，迟到早退等会使用到，并封装
							if(departmentId == null || departmentId != hxhyAttendance2.getDepartment_id()) {
								departmentId = hxhyAttendance2.getDepartment_id();
								workTimeList = hxhyWorkTimeService.getByDepartmentId(departmentId);
							}
							//请假处理
							long userTime = DateUtil.parseDate(DateUtil.format(hxhyAttendance2.getUser_check_time(), "HH:mm:ss"), "HH:mm:ss").getTime();
							if("OnDuty".equals(hxhyAttendance2.getCheck_type())) {
								if(leaveStart <= userTime) {
									intermission = intermission + calculateLeaveOnDuty(workTimeList, userTime, leaveStart);
								}
							} else if("OffDuty".equals(hxhyAttendance2.getCheck_type())) {
								if(userTime <= leaveEnd) {
									intermission = intermission + calculateLeaveOffDuty(workTimeList, userTime, leaveEnd);
								}
							}
						}
						
						//判断是上班还是下班
						if("OnDuty".equals(hxhyAttendance2.getCheck_type())) {
							
							//日期重置
							verDate = hxhyAttendance2.getWork_date();
							//修改状态
							isStart = true;
							//判断是否是系统打卡
							if("SYSTEM".equals(hxhyAttendance2.getSource_type())) {
								isSys = true;
							} else {
								if("Late".equals(hxhyAttendance2.getTime_result()) || "SeriousLate".equals(hxhyAttendance2.getTime_result())
										|| "Absenteeism".equals(hxhyAttendance2.getTime_result())) {
									
									int longTime = hxhyAttendance2.getTimelong() - delayTime;
									longTime = longTime - intermission;
									if(longTime <= 3) {
										//
										isPerfect = 0;
									} else {
										amount++;
										isPerfect = 0;
										if(amount <= 5) {
											if(longTime <= 60) {
												lateDate++;
											} else if(longTime > 60) {
												lateDate++;
												deductMoney = deductMoney + ((double)(longTime/60)*basicsMony)/9;
											}
										} else {
											lateDate++;
											deductMoney = deductMoney + calculateMoney(longTime, basicsMony);
										}
									}
								}
							}
						} else if("OffDuty".equals(hxhyAttendance2.getCheck_type())) {
							
							//判断是否是系统打卡
							if("SYSTEM".equals(hxhyAttendance2.getSource_type())) {
								//判断同一天是否有上班操作
								if(isStart) {
									//是否是系统打卡
									//判断是否是系统打卡，非系统打卡进行下面操作
									if(!isSys) {
										notSigned++;
										isPerfect = 0;
										amount++;
										workDate++;
									} else {
										isPerfect = 0;
									}
								}
							} else {
								//判断同一天是否有上班操作
								if(!isStart) {
									//没有上班打卡则加一次缺卡记录
									notSigned++;
									isPerfect = 0;
									amount++;
									workDate++;
								} else {
									//判断是否是系统打卡,当时系统打卡的时候进行下方操作
									if(isSys) {
										workDate++;
										notSigned++;
										isPerfect = 0;
										amount++;
									}
								}
								
								//继续后续操作
								if("Early".equals(hxhyAttendance2.getTime_result())) {
									
									int longTime = hxhyAttendance2.getTimelong() - intermission;
									if(longTime <= 3) {
										//
										isPerfect = 0;
									} else {
										amount++;
										isPerfect = 0;
										if(amount <= 5) {
											if(longTime <= 60) {
												earlyDate++;
											} else if(longTime > 60) {
												earlyDate++;
												deductMoney = deductMoney + (double)((longTime/60)*basicsMony)/9;
											}
										} else {
											earlyDate++;
											deductMoney = deductMoney + calculateMoney(longTime, basicsMony);
										}
									}
								} else if("NotSigned".equals(hxhyAttendance2.getTime_result())) {
									
									notSigned++;
									isPerfect = 0;
								} else if("Normal".equals(hxhyAttendance2.getTime_result())) {
									
									if(delayList != null && !delayList.isEmpty()) {
										for(HxhyDelayWorkScheme hxhyDelayWorkScheme : delayList) {
											if(hxhyDelayWorkScheme.getType() == 1) {
												Long destination = DateUtil.parseDate(hxhyDelayWorkScheme.getWork_date(), "HH:mm").getTime();
												Long objective = DateUtil.parseDate(DateUtil.format(hxhyAttendance2.getUser_check_time(), "HH:mm"), "HH:mm").getTime();
												if(destination <= objective) {
													delayTime = hxhyDelayWorkScheme.getDelay_time();
													break;
												} else {
													delayTime = 0;
												}
											}
										}
									}
								}
								
								//工作日加一天，并重置参数
								if(isAdd) {
									workDate++;
									isAdd = false;
								}
							}
							isEnd = true;
						}
					}
				}
				
				//是否需要保存操作
				if(saveStatisticsList.size() > 0) {
					hxhyAttendanceStatisticsService.insertList(saveStatisticsList);
				}
				return "0";
			} else {
				return "没有考勤信息";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "系统出错";
		}
	}
	
	/**
	 * 职工薪酬计算
	 * @param monthy
	 */
	public String payrollCalculation(String monthy) {
		try {
			
			//获得考勤统计信息，如果存在则覆盖，不存在则不处理
			HxhyAttendanceStatistics hxhyAttendanceStatistics = new HxhyAttendanceStatistics();
			hxhyAttendanceStatistics.setMonthy(monthy);
			List<HxhyAttendanceStatistics> statistiscList = hxhyAttendanceStatisticsService.getAll(hxhyAttendanceStatistics, null);
			
			//获得所有的用户信息
			HxhyManager hxhyManager2 = new HxhyManager();
			hxhyManager2.setIs_work(1);
			List<HxhyManager> userList = hxhyManagerService.getAll(hxhyManager2, null);
			
			//获得原来的统计信息，二次统计的时候不会创新信的信息
			HxhyPayroll hxhyPayroll2 = new HxhyPayroll();
			hxhyPayroll2.setMonthy(monthy);
			List<HxhyPayroll> originalList = hxhyPayrollService.getAll(hxhyPayroll2, null);
			
			//需要保存的参数
			List<HxhyPayroll> saveList = new ArrayList<HxhyPayroll>();
			
			//统计计算
			if(!userList.isEmpty()) {
				for(HxhyManager hxhyManager : userList) {
					Long id = null;
					if(!originalList.isEmpty()) {
						for(HxhyPayroll hxhyPayroll : originalList) {
							if(hxhyPayroll.getUser_id().equals(hxhyManager.getUser_id())) {
								id = hxhyPayroll.getId();
								break;
							}
						}
					}
					
					boolean isSave = true;
					if(statistiscList != null && !statistiscList.isEmpty()) {
						for(HxhyAttendanceStatistics hxhyAttendanceStatistics2 : statistiscList) {
							if(hxhyAttendanceStatistics2.getUser_id().equals(hxhyManager.getUser_id())) {
								
								double basicsMoney = hxhyAttendanceStatistics2.getBasics_money() + hxhyAttendanceStatistics2.getMoney();
								double workMoney = hxhyAttendanceStatistics2.getWork_money();
								double leaveMoney = hxhyAttendanceStatistics2.getLeave_money();
								double deductMoney = hxhyAttendanceStatistics2.getDeduct_money();
								int workDate = hxhyAttendanceStatistics2.getWork_date();
								int allDate = hxhyAttendanceStatistics2.getAll_date();
								
								Double total = basicsMoney*workDate/allDate;
								total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
								
								Double subtotal = total - 0 + 0;
								
								//税收计算
								double[] valArr = calculateInsurance(hxhyAttendanceStatistics2.getUser_id());
								double taxMoney = calculateRevenue(subtotal);
								double realMoney = subtotal - taxMoney - valArr[18] - valArr[19];
								
								double companyPayment = total + valArr[12];
								
								HxhyPayroll hxhyPayroll = new HxhyPayroll();
								hxhyPayroll.setAdd_date(new Date());
								hxhyPayroll.setBasics_money(hxhyAttendanceStatistics2.getBasics_money());
								hxhyPayroll.setMoney(hxhyAttendanceStatistics2.getMoney());
								hxhyPayroll.setAll_date(allDate);
								hxhyPayroll.setBack_pay(0.0);
								hxhyPayroll.setBonus(workMoney);
								hxhyPayroll.setCom_reserved_fund(valArr[10]);
								hxhyPayroll.setCom_social_insurance(valArr[11]);
								hxhyPayroll.setCompany_payment(companyPayment);
								hxhyPayroll.setIndividual_income_tax(taxMoney);
								hxhyPayroll.setPer_reserved_fund(valArr[18]);
								hxhyPayroll.setPer_social_insurance(valArr[19]);
								hxhyPayroll.setLate_money(deductMoney);
								hxhyPayroll.setLeave_money(leaveMoney);
								hxhyPayroll.setMeal_fee(0.0);
								hxhyPayroll.setOvertime_pay(0.0);
								hxhyPayroll.setUser_id(hxhyAttendanceStatistics2.getUser_id());
								hxhyPayroll.setWork_date(workDate);
								hxhyPayroll.setTotal(total);
								hxhyPayroll.setReal_money(realMoney);
								hxhyPayroll.setRemark(null);
								hxhyPayroll.setMonthy(monthy);
								hxhyPayroll.setSubtotal(subtotal);
								
								if(id != null) {
									hxhyPayroll.setId(id);
									hxhyPayrollService.update(hxhyPayroll);
								} else {
									saveList.add(hxhyPayroll);
								}
								
								isSave = false;
							}
						}
					}
					
					if(isSave) {
						double basicsMoney = hxhyManager.getBasics_money() + hxhyManager.getMoney();
						
						Double total = basicsMoney;
						total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
						
						Double subtotal = total - 0 + 0;
						
						//税收计算
						double[] valArr = calculateInsurance(hxhyManager.getUser_id());
						double taxMoney = calculateRevenue(subtotal);
						double realMoney = subtotal - taxMoney - valArr[18] - valArr[19];
						
						double companyPayment = total + valArr[12];
						
						HxhyPayroll hxhyPayroll = new HxhyPayroll();
						hxhyPayroll.setAdd_date(new Date());
						hxhyPayroll.setBasics_money(hxhyManager.getBasics_money());
						hxhyPayroll.setMoney(hxhyManager.getMoney());
						hxhyPayroll.setAll_date(22);
						hxhyPayroll.setBack_pay(0.0);
						hxhyPayroll.setBonus(0.0);
						hxhyPayroll.setCom_social_insurance(valArr[11]);
						hxhyPayroll.setCom_reserved_fund(valArr[10]);
						hxhyPayroll.setCompany_payment(companyPayment);
						hxhyPayroll.setIndividual_income_tax(taxMoney);
						hxhyPayroll.setPer_reserved_fund(valArr[18]);
						hxhyPayroll.setPer_social_insurance(valArr[19]);
						hxhyPayroll.setLate_money(0.0);
						hxhyPayroll.setLeave_money(0.0);
						hxhyPayroll.setMeal_fee(0.0);
						hxhyPayroll.setOvertime_pay(0.0);
						hxhyPayroll.setUser_id(hxhyManager.getUser_id());
						hxhyPayroll.setWork_date(22);
						hxhyPayroll.setTotal(total);
						hxhyPayroll.setReal_money(realMoney);
						hxhyPayroll.setRemark(null);
						hxhyPayroll.setMonthy(monthy);
						hxhyPayroll.setSubtotal(subtotal);
						
						if(id != null) {
							hxhyPayrollService.update(hxhyPayroll);
						} else {
							saveList.add(hxhyPayroll);
						}
					}
				}
				
				hxhyPayrollService.insertList(saveList);
				return "0";
			} else {
				return "没有员工信息";
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(JSONObject.toJSONString(e));
			return "系统出错";
		}
	}
	
	/**
	 * 通过类型获得理论上的上班时间
	 * @param type
	 * @return
	 */
	private int getTheoryDate(int type, String monthy) {
		if(type == 1) {
			Date date = DateUtil.parseDate(monthy, DateUtil.FORMAT_YYYYMM);
			return DateUtil.getDays(date);
		}
		return 0;
	}
	
	/**
	 * 计算单次早退或退出应扣除的钱数
	 * @param timeLong
	 * @param basicsMony
	 * @return
	 */
	private double calculateMoney(int timeLong, Double basicsMony) {
		
		double res = 0;
		if(timeLong <= 3) {
			//
		} else if(timeLong <= 15) {
			res = 50;
		} else if(timeLong <= 60) {
			
			double val = (double)(2*basicsMony/9);
			if(val < 50) {
				res = 50;
			}
			res = val;
		} else if(timeLong <= 120) {
			double val = (double)(4*basicsMony/9);
			if(val < 50) {
				res = 50;
			}
			res = val;
		} else if(timeLong <= 240) {
			double val = (double)(6*basicsMony/9);
			if(val < 50) {
				res = 50;
			}
			res = val;
		} else if(timeLong > 240) {
			res = basicsMony;
		}
		
		return res;
	}
	
	/**
	 * 计算请假当天打卡需要补充的时间
	 * 上班打卡情况
	 * 
	 * @param workTimeList
	 * @param userTime
	 * @param verifyTime
	 * @return
	 */
	private int calculateLeaveOnDuty(List<HxhyWorkTime> workTimeList, Long userTime, Long leaveStart) {
		
		int intermission = 0;
		if(workTimeList != null && !workTimeList.isEmpty()) {
			for(HxhyWorkTime hxhyWorkTime : workTimeList) {
				long offDuty = DateUtil.parseDate(hxhyWorkTime.getOff_duty(), "HH:mm").getTime();
				long onDuty = DateUtil.parseDate(hxhyWorkTime.getOn_duty(), "HH:mm").getTime();
				
				if(userTime < onDuty) {
					//不计算时间
				} else if(leaveStart <= onDuty && offDuty <= userTime) {
					//内包含
					intermission = intermission + (int)(offDuty - onDuty);
				} else if(leaveStart <= onDuty && userTime < offDuty) {
					//左包含
					intermission = intermission + (int)(userTime - onDuty);
				} else if(onDuty < leaveStart && offDuty <= userTime) {
					//右包含
					intermission = intermission + (int)(offDuty - leaveStart);
				} else if(onDuty < leaveStart && userTime < offDuty) {
					//外包含
					intermission = intermission + (int)(userTime - leaveStart);
				}
			}
			intermission = intermission/60000;
		}
		
		return intermission;
	}
	
	/**
	 * 计算请假当天打卡需要补充的时间
	 * 下班打卡情况
	 * 
	 * @param workTimeList
	 * @param userTime
	 * @param leaveStart
	 * @return
	 */
	private int calculateLeaveOffDuty(List<HxhyWorkTime> workTimeList, Long userTime, Long leaveEnd) {
		
		int intermission = 0;
		if(workTimeList != null && !workTimeList.isEmpty()) {
			for(HxhyWorkTime hxhyWorkTime : workTimeList) {
				long offDuty = DateUtil.parseDate(hxhyWorkTime.getOff_duty(), "HH:mm").getTime();
				long onDuty = DateUtil.parseDate(hxhyWorkTime.getOn_duty(), "HH:mm").getTime();
				
				if(userTime > offDuty) {
					//不统计学时
				} else if(userTime <= onDuty && offDuty <= leaveEnd) {
					//内包含
					intermission = intermission + (int)(offDuty - onDuty);
				} else if(userTime <= onDuty && leaveEnd < offDuty) {
					//左包含
					intermission = intermission + (int)(leaveEnd - onDuty);
				} else if(onDuty < userTime && offDuty <= leaveEnd) {
					//右包含
					intermission = intermission + (int)(offDuty - userTime);
				} else if(onDuty < userTime && leaveEnd < offDuty) {
					//外包含
					intermission = intermission + (int)(leaveEnd - userTime);
				}
			}
			intermission = intermission/60000;
		}
		return intermission;
	}
	
	/**
	 * 五险一金计算
	 * 
	 * @param userId
	 * @return
	 */
	public double[] calculateInsurance(String userId) {
		
		double[] res = new double[21];
		//税率信息获取
		HxhyFinance hxhyFinance = hxhyFinanceService.getById(1L);
		
		Double provident_fund = hxhyFinance.getProvident_fund();
		Double social_security = hxhyFinance.getSocial_security();
		
		Double com_pension = social_security*hxhyFinance.getCom_pension()/100;
		Double com_medical = social_security*hxhyFinance.getCom_medical()/100;
		Double com_unemployment = social_security*hxhyFinance.getCom_unemployment()/100;
		Double com_work_injury = social_security*hxhyFinance.getCom_work_injury()/100;
		Double com_bear = social_security*hxhyFinance.getCom_bear()/100;
		Double per_pension = social_security*hxhyFinance.getPer_pension()/100;
		Double per_medical = social_security*hxhyFinance.getPer_medical()/100;
		Double per_unemployment = social_security*hxhyFinance.getPer_unemployment()/100;
		
		Double com_provident_fund=0.0;
		Double com_provident_fund_supplement=0.0;
		Double per_provident_fund=0.0;
		Double per_provident_fund_supplement=0.0;
		
		if(hxhyFinance.getType() == 0) {
			com_provident_fund = provident_fund*hxhyFinance.getCom_provident_fund()/100;
			per_provident_fund = provident_fund*hxhyFinance.getPer_provident_fund()/100;
			com_provident_fund_supplement = provident_fund*hxhyFinance.getCom_provident_fund_supplement()/100;
			per_provident_fund_supplement = provident_fund*hxhyFinance.getPer_provident_fund_supplement()/100;
		} else if(hxhyFinance.getType() == 1) {
			com_provident_fund = provident_fund*hxhyFinance.getCom_provident_fund()/100;
			per_provident_fund = provident_fund*hxhyFinance.getPer_provident_fund()/100;
		} else if(hxhyFinance.getType() == 2) {
			com_provident_fund_supplement = provident_fund*hxhyFinance.getCom_provident_fund_supplement()/100;
			per_provident_fund_supplement = provident_fund*hxhyFinance.getPer_provident_fund_supplement()/100;
		}
		
		Double comSocial = com_pension+com_medical+com_unemployment+com_work_injury+com_bear;
		Double comProvidentFund = com_provident_fund+com_provident_fund_supplement;
		Double comTotal = comSocial + comProvidentFund;
		
		Double perSocial = per_pension+per_medical+per_unemployment;
		Double perProvidentFund = per_provident_fund+per_provident_fund_supplement;
		Double perTotal = perSocial + perProvidentFund;
		
		//四舍五入处理及封装
		res[0] = round(provident_fund);
		res[1] = round(social_security);
		res[2] = round(hxhyFinance.getType()+0.0);
		res[3] = round(com_pension);
		res[4] = round(com_medical);
		res[5] = round(com_unemployment);
		res[6] = round(com_work_injury);
		res[7] = round(com_bear);
		res[8] = round(com_provident_fund);
		res[9] = round(com_provident_fund_supplement);
		res[10] = round(comSocial);
		res[11] = round(comProvidentFund);
		res[12] = round(comTotal);
		res[13] = round(per_pension);
		res[14] = round(per_medical);
		res[15] = round(per_unemployment);
		res[16] = round(per_provident_fund);
		res[17] = round(per_provident_fund_supplement);
		res[18] = round(perSocial);
		res[19] = round(perProvidentFund);
		res[20] = round(perTotal);

		return res;
	}
	
	/**
	 * 个人所得税计算
	 * @param money
	 * @return
	 */
	public Double calculateRevenue(Double money) {
		
		if(money != null) {
			
			//获得个税的列表
			HxhyRevenue hxhyRevenue = new HxhyRevenue();
			hxhyRevenue.setType(1);
			List<HxhyRevenue> lsit = hxhyRevenueService.getAll(hxhyRevenue, "id ASC");
			
			//计算
			double res = 0.0;
			for(HxhyRevenue hxhyRevenue2 : lsit) {
				if(hxhyRevenue2.getCeiling() <= money) {
					res = res + (hxhyRevenue2.getCeiling() - hxhyRevenue2.getThreshold())*hxhyRevenue2.getTax_rate()/100;
				} else if(hxhyRevenue2.getThreshold() < money && money <= hxhyRevenue2.getCeiling()) {
					res = res + (money - hxhyRevenue2.getThreshold())*hxhyRevenue2.getTax_rate()/100;
					break;
				} else if(money < hxhyRevenue2.getCeiling()) {
					break;
				}
			}
			
			res = round(res);
			return res;
		} else {
			return null;
		}
	}
	
	/**
	 * 四舍五入处理
	 * @param val
	 * @return
	 */
	private Double round(Double val) {
		if(val != null) {
			return new BigDecimal(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
		} else {
			return 0.0;
		}
	}
}
