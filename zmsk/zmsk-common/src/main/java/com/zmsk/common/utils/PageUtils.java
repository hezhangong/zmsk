package com.zmsk.common.utils;

public class PageUtils {

	public static int getPageStart(int pageSize, int pageNum) {
		return (pageNum - 1 <= 0) ? 0 : ((pageNum - 1) * pageSize);
	}
}
