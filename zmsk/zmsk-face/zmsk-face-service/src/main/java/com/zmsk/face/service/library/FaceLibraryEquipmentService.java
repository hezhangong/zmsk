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

	/****
	 * 修改人脸库和设备关联记录信息
	 * 
	 * @param libraryId
	 *            人脸库Id
	 * @param equipmentIds
	 *            设备Id
	 * @param oldEquipmentIds
	 *            原始设备Id列表
	 * @return
	 */
	boolean updateLibraryEquipment(int libraryId, List<Integer> equipmentIds, List<Integer> oldEquipmentIds);

	/****
	 * 获取设备未同步的人脸库Id列表
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	List<Integer> queryEquipmentUnSyncFaceLibraryIds(int deviceId);

	/***
	 * 标记同步状态
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	boolean flagsyncedFaceLibrary(int id);

	/****
	 * 标记未同步状态
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	boolean flagEnableFaceLibrary(int id);

}
