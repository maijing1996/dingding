package com.hxhy;

import java.util.Calendar;
import java.util.Date;

import com.hxhy.util.DateUtil;

public class Test {

	public static void main(String[] args) throws Exception {

		
		Date date = DateUtil.parseDate("2018-10-08", DateUtil.FORMAT_YYYY_MM_dd);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		cal.set(Calendar.DATE, 1);//把日期设置为当月第一天 
		
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		int week = cal.get(Calendar.DAY_OF_WEEK);//得到某月1号星期几
		cal.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天 
		
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
	    int maxDate = cal.get(Calendar.DATE);
		System.out.println(maxDate);
		
		//周日是1
		//第一个周六日期
		System.out.println(week);
		int saturday = (7 - week) + 1;
		if(saturday < 0) {
			saturday = saturday + 7;
		}
		System.out.println(saturday+"....");
		
		int sunday = (1 - week) + 1;
		if(sunday < 0) {
			sunday = sunday + 7;
		}
		System.out.println(sunday);
		
		int n = 1, m = 1;
		for(int i=1; i < 7; i++) {
			if(saturday+7*i <= maxDate) {
				n++;
			}
			if(sunday+7*i <= maxDate) {
				m++;
			}
		}
		
		System.out.println("n:"+n);
		System.out.println("m:"+m);
		System.out.println(maxDate-n-m);
	}
}
