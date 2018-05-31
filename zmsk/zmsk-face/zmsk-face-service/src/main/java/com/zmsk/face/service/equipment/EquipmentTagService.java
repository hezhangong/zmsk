package com.zmsk.face.service.equipment;

import java.util.List;

import com.zmsk.face.pojo.FaceEquipmentTag;

/****
 * 设备标签操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface EquipmentTagService {

	/****
	 * 创建设备标签
	 * 
	 * @param organizationId
	 *            组织id
	 * @param description
	 *            描述
	 * @return
	 */
	boolean createEquipmentTag(int organizationId, String description);

	/****
	 * 修改设备标签
	 * 
	 * @param id
	 *            主键
	 * @param description
	 *            描述
	 * @return
	 */
	boolean updateEquipmentTag(int id, String description);

	/****
	 * 删除设备标签
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	boolean deleteEquipmentTag(int id);

	/****
	 * 获取对应组织下的设备标签列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<FaceEquipmentTag> queryEquipmentTagByOrganizationId(int organizationId);
	
}
