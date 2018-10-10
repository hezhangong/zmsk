package com.zmsk.face.service.equipment;

/****
 * 设备绑定操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface EquipmentListService {

	/****
	 * 创建设备绑定信息
	 * 
	 * @param deviceNumber
	 *            设备号
	 * @param deviceIP
	 *            设备IP
	 * @return
	 */
	boolean createEquipmentList(String deviceNumber, String deviceIP);
}
