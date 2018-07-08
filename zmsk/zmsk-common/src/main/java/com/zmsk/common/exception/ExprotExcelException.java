package com.zmsk.common.exception;

import com.zmsk.common.exception.constants.ExceptionCodeConstants;

/****
 * Excel 导出自定义异常
 * 
 * @author warrior
 *
 */
public class ExprotExcelException extends ZmskException {

	private static final long serialVersionUID = 7980783131402949293L;

	public ExprotExcelException(String message) {
		super(message);
	}

	public ExprotExcelException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public int getCode() {
		return ExceptionCodeConstants.EXPORT_EXCEL_CODE;
	}

}
