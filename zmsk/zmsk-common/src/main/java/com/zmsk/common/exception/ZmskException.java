package com.zmsk.common.exception;

/****
 * 自定义业务处理的自定义异常, 包含错误code和http的错误code
 * 
 * @author warrior
 *
 */
public abstract class ZmskException extends RuntimeException {

	private static final long serialVersionUID = 6851274131509494649L;

	public ZmskException(String message) {
		super(message);
	}

	public ZmskException(Throwable cause) {
		super(cause);
	}

	public ZmskException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZmskException(String message, Throwable cause, boolean enableSupperssion, boolean writableStackTrace) {
		super(message, cause, enableSupperssion, writableStackTrace);
	}

	/**
	 * 返回错误code, 错误代码 5位数字, 前三位和http status保持一致, 后两位为自定义的错误码
	 *
	 * @return
	 */
	public abstract int getCode();

	/**
	 * 获取http的错误code
	 *
	 * @return
	 */
	public final int getHttpCode() {

		int code = getCode();

		if (code > 9999) {
			return code / 100;
		}

		if (code > 1000) {
			return code / 10;
		}
		return code;
	}
}
