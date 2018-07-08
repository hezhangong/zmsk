package com.zmsk.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/****
 * 日期操作工具类
 * 
 * @author warrior
 *
 */
public class DateUtils {

	/***
	 * 获取当前日期时间戳 yyyy-MM-dd
	 * 
	 * @return 秒值
	 */
	public static long getCurrentDateTime() {
		LocalDate localDate = new LocalDate();
		return localDate.toDate().getTime() / 1000;
	}

	/****
	 * 获取指定日期时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long getDateTime(Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.toDate().getTime() / 1000;
	}

	/****
	 * 获取前几天的时间戳 yyyy-MM-dd
	 * 
	 * @param days
	 *            天数
	 * @return
	 */
	public static long getBeforeDaysDateTime(int days) {
		LocalDate localDate = new LocalDate();
		return localDate.minusDays(days).toDate().getTime() / 1000;
	}

	/****
	 * 获取当前日期的后几天天时间戳 yyyy-MM-dd
	 * 
	 * @param days
	 *            天数
	 * @return 秒值
	 */
	public static long getCurrentNextDaysDateTime(int days) {
		LocalDate localDate = new LocalDate();
		return localDate.plusDays(1).toDate().getTime() / 1000;
	}

	/***
	 * 获取指定日期后几天天时间戳
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static long getNextDaysDateTime(Date date, int days) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusDays(days).toDate().getTime() / 1000;
	}

	/***
	 * 获取当前日期前的一个星期时间戳 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static long getbeforeWeekDateTime() {
		LocalDate localDate = new LocalDate();
		return localDate.minusWeeks(1).toDate().getTime() / 1000;
	}

	/****
	 * 获取当前日期前的一个月的时间戳
	 * 
	 * @return
	 */
	public static long getCurrentLastMonthDateTime() {
		LocalDate localDate = new LocalDate();
		return localDate.minusMonths(1).toDate().getTime() / 1000;
	}

	/****
	 * 获取当前日期前的一个星期日期
	 * 
	 * @return
	 */
	public static Date getbeforeWeekDate() {
		LocalDate localDate = new LocalDate();
		return localDate.minusWeeks(1).toDate();
	}

	/****
	 * 获取当前日期前的一个月日期
	 * 
	 * @return
	 */
	public static Date getCurrentLastMonth() {
		LocalDate localDate = new LocalDate();
		return localDate.minusMonths(1).toDate();
	}

	/****
	 * 指定新增一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date plusDay(Date date) {

		DateTime dateTime = new DateTime(date);

		dateTime = dateTime.plusDays(1);

		return dateTime.toDate();
	}

	/***
	 * 指定日期减少一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date minusDay(Date date) {

		DateTime dateTime = new DateTime(date);

		dateTime = dateTime.minusDays(1);

		return dateTime.toDate();
	}

	/****
	 * 计算两个日期之间的间隔时间
	 * 
	 * @param beforeDate
	 *            开始日期
	 * @param afterDate
	 *            结束日期
	 * @return
	 */
	public static int interval(Date beforeDate, Date afterDate) {
		DateTime before = new DateTime(beforeDate);
		DateTime after = new DateTime(afterDate);
		Period p = new Period(before, after, PeriodType.days());
		return p.getDays();
	}

	/***
	 * 将日期格式转换为yyyy-MM-dd字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDate2Str(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/****
	 * 讲字符串格式转换成日期格式
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date convertStr2Date(String dateStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dateTime = dateTimeFormatter.parseDateTime(dateStr);
		return dateTime.toDate();
	}

	/****
	 * 获取当前日期的新增指定年时间戳
	 * 
	 * @param years
	 *            新增年数
	 * @return
	 */
	public static long getCurrentPlusYearTime(int years) {
		LocalDate localDate = new LocalDate();
		localDate = localDate.plusYears(years);
		return localDate.toDate().getTime() / 1000;
	}

	/***
	 * 获取当前时间字符串 yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentDateStr() {
		DateTime dateTime = new DateTime();
		return dateTime.toString("yyyy-MM-dd");
	}

	public static void main(String[] args) {
		Date date = new Date(1530029699000L);
		System.out.println(convertDate2Str(date));
	}

}
