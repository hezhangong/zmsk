package com.zmsk.cache.exception;

/***
 * 自定义缓存操作Duplicate异常
 * 
 * @author warrior
 *
 */
public class DuplicateCacheDeclareException extends RuntimeException {

	private static final long serialVersionUID = -2004944416971523135L;

	public DuplicateCacheDeclareException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateCacheDeclareException(String message) {
		super(message);
	}

	public DuplicateCacheDeclareException(Throwable cause) {
		super(cause);
	}

}
