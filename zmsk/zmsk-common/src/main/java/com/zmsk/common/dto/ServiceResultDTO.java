package com.zmsk.common.dto;

import java.io.Serializable;

/****
 * rest 数据统一返回对象封装
 * 
 * @author warrior
 *
 */
public class ServiceResultDTO implements Serializable {

	private static final long serialVersionUID = 4503003768092442290L;

	/** 状态 **/
	private int code;

	/** 消息 **/
	private String msg;

	/** 数据 **/
	private Object data;

	public ServiceResultDTO() {
	}

	public ServiceResultDTO(int code, String msg) {
		this(code, msg, null);
	}

	public ServiceResultDTO(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static ServiceResultDTO success() {
		return success(null);
	}

	public static ServiceResultDTO success(Object data) {
		return new ServiceResultDTO(BaseResultCode.SUCCESS, "success", data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
