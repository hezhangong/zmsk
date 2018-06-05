package com.zmsk.face.service.equipment;

import java.util.List;

import com.zmsk.face.pojo.FaceEquipment;

/***
 * 设备管理操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface equipmentService {

	/****
	 * 创建设备
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param count
	 *            数量
	 * @param password
	 *            初始密码
	 * @param type
	 *            类型
	 * @param renewalFee
	 *            续费金额
	 * @return
	 */
	boolean createEquipment(int organizationId, int count, String password, int type, int renewalFee);

	/****
	 * 激活设备
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	boolean activateEquipment(int deviceId);

	/****
	 * 获取设备列表
	 * 
	 * @param organizationId
	 * @return
	 */
	List<FaceEquipment> queryEquipmentByOrganizationId(int organizationId);

	/****
	 * 根据设备Id获取设备信息
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	FaceEquipment queryEquipmentById(int deviceId);

	/***
	 * 修改设备登入密码
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @param newPassword
	 *            新密码
	 * @param oldPassword
	 *            旧密码
	 * @return 0 修改失败，1：修改成功，2：原始密码不正确
	 */
	int updateEquipmentPassword(int equipmentId, String newPassword, String oldPassword);

	/****
	 * 修改设备的备注信息
	 * 
	 * @param deviceId
	 *            设备Id
	 * @param remark
	 *            备注信息
	 * @return
	 */
	boolean updateEquipmentRemarkById(int deviceId, String remark);
}
