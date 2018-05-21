package com.zmsk.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/****
 * 字符串摘要获取工具类
 * 
 * @author warrior
 *
 */
public class StringDigestUtils {

	private static final Logger logger = LoggerFactory.getLogger(StringDigestUtils.class);

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String md5(String input) {
		MessageDigest mdd = null;
		try {
			mdd = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error("invalid MessageDigest");
		}
		mdd.update(input.getBytes());
		byte[] md = mdd.digest();
		char str[] = new char[md.length * 2];
		int k = 0;
		for (int i = 0; i < md.length; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
}
