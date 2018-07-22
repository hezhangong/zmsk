package com.zmsk.face.service.equipment;

import com.zmsk.face.pojo.FaceEquipmentInfo;

/****
 * 组织下设备显示信息操作Serivce接口声明
 * 
 * @author warrior
 *
 */
public interface EquipmentInfoService {

	/****
	 * 获取组织对应的认证记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	boolean addEquipmentInfo(String title, String logo, String telNumber, String address, int organizationId);

	/****
	 * 根据组织Id获取设备显示信息值
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	FaceEquipmentInfo queryEquipmentInfoByOrganizationId(int organizationId);

	/****
	 * 修改组织设备显示信息
	 * 
	 * @param id
	 *            主键Id
	 * @param title
	 *            标题
	 * @param logo
	 *            图标地址
	 * @param organizationId
	 *            组织Id
	 * @param telNumber
	 *            电话号码
	 * @param address
	 *            地址
	 * @return
	 */
	boolean updateEquipmentInfo(int id, String title, String logo, String telNumber, String address);

	/****
	 * 根据设备Id获取设备显示信息值
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	FaceEquipmentInfo queryEquipmentInfoByDeviceId(int deviceId);
}
