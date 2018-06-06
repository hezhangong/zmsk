package com.zmsk.face.service.authentication;

import com.zmsk.common.pagehelper.PageInfo;
import com.zmsk.face.pojo.FaceAuthenticationInfo;

/****
 * 认证信息操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface AuthenticationInfoService {

	/****
	 * 添加认证信息记录
	 * 
	 * @param name
	 *            姓名
	 * @param idNumber
	 *            身份证号
	 * @param nation
	 *            民族
	 * @param address
	 *            地址
	 * @param avatar
	 *            头像地址
	 * @param sex
	 *            性别
	 * @param group
	 *            分组
	 * @param type
	 *            类型
	 * @param result
	 *            结果
	 * @param deviceNumber
	 *            设备登入账号
	 * @return
	 */
	boolean addAuthenticationInfo(String name, String idNumber, String nation, String address, String avatar, int sex, String group, int type, int result, String deviceNumber);

	/****
	 * 查询认证信息记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            第几页
	 * @return
	 */
	PageInfo<FaceAuthenticationInfo> queryAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum);
}
