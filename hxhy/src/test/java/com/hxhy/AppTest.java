package com.hxhy;

import java.util.Date;

import com.hxhy.util.DateUtil;

public class AppTest {

	public static void main(String[] args) {

		Date date = DateUtil.parseDate("201811", DateUtil.FORMAT_YYYYMM);
		System.out.println(date);
		
		Date date2 = DateUtil.parseDate("2018-11-02 09:33:06");
		Long origin = DateUtil.parseDate("08:30", "HH:mm").getTime();
		Long origin2 = DateUtil.parseDate("09:30", "HH:mm").getTime();
		Long objective = DateUtil.parseDate(DateUtil.format(date2, "HH:mm:ss"), "HH:mm:ss").getTime();
		System.out.println(origin);
		System.out.println(origin2);
		System.out.println((origin2-origin)/60000);
		System.out.println(objective);
		System.out.println((objective-origin)/60000);
		
		 
//		String date3 = DateUtil.format("201911", DateUtil.FORMAT_YYYYMM);
//		System.out.println(date3);
	}

}
