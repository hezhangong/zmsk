package com.zmsk.face.service.equipment;

import java.util.List;

import com.zmsk.face.dto.equipment.EquipmentRemarkDTO;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.equipment.dto.DeviceLoginResultDTO;

/***
 * 设备管理操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface EquipmentService {

	/****
	 * 创建设备
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param factoryId
	 *            工厂Id
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
	boolean createEquipment(int organizationId, int factoryId, int count, String password, int type, int renewalFee);

	/****
	 * 激活设备
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	boolean activateEquipment(int deviceId);
	
	/****
	 * 停用设备
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	boolean inactivateEquipment(int deviceId);

	/****
	 * 删除设备
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	boolean deleteEquipment(int deviceId);
	
	/****
	 * 根据设备物理Id获取设备信息
	 * @param macId
	 *            设备物理Id
	 * @param equipmentNumber 
	 *            登入账号
	 * @return
	 */
	List<FaceEquipment> checkEquipment(String macId, String equipmentNumber);

	/****
	 * 获取设备列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param factoryId
	 *            工厂Id
	 * @return
	 */
	List<FaceEquipment> queryEquipmentList(Integer organizationId, Integer factoryId);

	/****
	 * 根据设备Id获取设备信息
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	FaceEquipment queryEquipmentById(int deviceId);
	
	/****
	 * 根据组织Id获取组织备注信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<EquipmentRemarkDTO> queryEquipmentRemarkByorganizationId(int organizationId);

	/***
	 * 根据账号获取设备信息
	 * 
	 * @param deviceNumber
	 *            设备登入账号
	 * @return
	 */
	FaceEquipment queryEquipmentByNumber(String deviceNumber);

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

	/****
	 * 设备登入
	 * 
	 * @param deviceNumber
	 *            登入账号
	 * @param devicePassword
	 *            登入密码
	 * @param macId
	 *            机器物理地址
	 * @return null 不是同一个设备登入
	 */
	DeviceLoginResultDTO deviceLogin(String deviceNumber, String devicePassword, String macId);
}
