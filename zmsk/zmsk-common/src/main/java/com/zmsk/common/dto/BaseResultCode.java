package com.zmsk.common.dto;

public class BaseResultCode {

	public static final int SUCCESS = 200;

	public static final int NOT_CONTENT = 204;

	/** 无效的参数 **/
	public static final int INVALID_PARAM = 40000;

	/** 原始密码错误 **/
	public static final int INVALID_OLDPASSWORD = 40002;

	/** 无效的签名 **/
	public static final int INVALID_SIGN = 40003;

	/** 会员操作错误code **/
	public static final int USER_OPERATION_ERROR = 50001;

	/** 角色操作错误code **/
	public static final int ROLE_OPERATION_ERROR = 50002;

	/** 权限操作错误code **/
	public static final int PERMISSION_OPERATION_ERROR = 50003;

	/** 组织操作错误code **/
	public static final int ORGANIZATION_OPERATION_ERROR = 50004;

	/** 设备操作错误code **/
	public static final int EQUIPMENT_OPERATION_ERROR = 50005;

	/** 设备操作错误code **/
	public static final int EQUIPMENT_TAG_OPERATION_ERROR = 50006;

	/** 设备认证信息操作错误 **/
	public static final int AUTHENTICATION_INFO_OPERATION_ERROR = 50007;

	/** 人脸库操作失败 **/
	public static final int LIBRARY_OPERATION_ERROR = 50008;

	/** 组织操作失败 **/
	public static final int GROUP_OPERATION_ERROR = 50009;

	/** 同步人脸库操作失败 **/
	public static final int SYNC_FACE_LIBRARY_FAIL = 50010;

	/** 访客人脸库操作失败 **/
	public static final int VISITOR_LIBRARY_OPERATION_ERROR = 50011;
	
}
