package com.zmsk.face.service.library;

import java.util.List;

/****
 * 人脸库，设备管理操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface FaceLibraryEquipmentService {

	/****
	 * 新增人脸和设备关联记录信息
	 * 
	 * @param libraryId
	 *            人脸库Id
	 * @param equipmentIds
	 *            设备Id
	 * @return
	 */
	boolean addLibraryEquipment(int libraryId, List<Integer> equipmentIds);

}
