package com.zmsk.common.exception;

import com.zmsk.common.exception.constants.ExceptionCodeConstants;

/****
 * 认证失败异常
 * 
 * @author warrior
 *
 */
public class UnauthorizedAccessException extends ZmskException {

	private static final long serialVersionUID = -5779268220838382145L;

	public UnauthorizedAccessException(String message) {
		super(message);
	}

	public UnauthorizedAccessException(Throwable cause) {
		super(cause);
	}

	public UnauthorizedAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getCode() {
		return ExceptionCodeConstants.UNAUTH_CODE;
	}

}
