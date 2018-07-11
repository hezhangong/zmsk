package com.zmsk.face.service.library.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceEquipmentLibraryMapper;
import com.zmsk.face.mapper.custom.library.CustomerEquipmentLibraryMapper;
import com.zmsk.face.pojo.FaceEquipmentLibrary;
import com.zmsk.face.service.library.FaceLibraryEquipmentService;
import com.zmsk.face.service.library.constants.EquipmentLibraryOperationType;
import com.zmsk.face.service.library.constants.EquipmentLibrarySyncStatus;

/****
 * 人脸库，设备管理操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class FaceLibraryEquipmentServiceImpl implements FaceLibraryEquipmentService {

	@Autowired
	private FaceEquipmentLibraryMapper equipmentLibraryMapper;

	@Autowired
	private CustomerEquipmentLibraryMapper customerEquiomentLibraryMapper;

	@Override
	public boolean addLibraryEquipment(int libraryId, List<Integer> equipmentIds) {

		for (int equipmentId : equipmentIds) {

			addIncreaseLibrary(libraryId, equipmentId);
		}

		return true;
	}

	@Override
	public boolean updateLibraryEquipment(int libraryId, List<Integer> equipmentIds) {

		List<Integer> oldEquipmentIds = customerEquiomentLibraryMapper.queryEquipmentIdByLibraryId(libraryId);

		if (oldEquipmentIds == null || oldEquipmentIds.size() == 0) {
			return addLibraryEquipment(libraryId, oldEquipmentIds);
		}

		for (int equipmentId : equipmentIds) {

			if (oldEquipmentIds.contains(equipmentId)) {
				continue;
			}

			addIncreaseLibrary(libraryId, equipmentId);
		}

		oldEquipmentIds.removeAll(equipmentIds);

		for (int oldEquipmentId : oldEquipmentIds) {
			addDeleteLibrary(libraryId, oldEquipmentId);
		}

		return true;
	}

	@Override
	public List<Integer> queryEquipmentUnSyncFaceLibraryIds(int deviceId) {
		return customerEquiomentLibraryMapper.queryEquipmentUnSyncFaceLibraryIds(deviceId);
	}

	@Override
	public boolean flagsyncedFaceLibrary(int id) {

		FaceEquipmentLibrary equipmentLibrary = new FaceEquipmentLibrary();

		equipmentLibrary.setSyncStatus(EquipmentLibrarySyncStatus.SYNCED);

		equipmentLibrary.setId(id);

		return equipmentLibraryMapper.updateByPrimaryKeySelective(equipmentLibrary) > 0;
	}

	/****
	 * 新增类型人脸库和设备管理
	 * 
	 * @param libraryId
	 *            人脸库Id
	 * @param equipmentId
	 *            设备Id
	 */
	private void addIncreaseLibrary(int libraryId, int equipmentId) {

		FaceEquipmentLibrary equipmentLibrary = new FaceEquipmentLibrary();

		equipmentLibrary.setLibraryId(libraryId);

		equipmentLibrary.setEquipmentId(equipmentId);

		equipmentLibrary.setSyncStatus(EquipmentLibrarySyncStatus.UNSYNC);

		equipmentLibrary.setOperation(EquipmentLibraryOperationType.INCREASE_TYPE);

		equipmentLibrary.setCtime(System.currentTimeMillis() / 1000);

		equipmentLibraryMapper.insert(equipmentLibrary);
	}

	/****
	 * 删除类型人脸库和设备管理
	 * 
	 * @param libraryId
	 *            人脸库Id
	 * @param equipmentId
	 *            设备Id
	 */
	public void addDeleteLibrary(int libraryId, int equipmentId) {

		FaceEquipmentLibrary equipmentLibrary = new FaceEquipmentLibrary();

		equipmentLibrary.setLibraryId(libraryId);

		equipmentLibrary.setEquipmentId(equipmentId);

		equipmentLibrary.setSyncStatus(EquipmentLibrarySyncStatus.UNSYNC);

		equipmentLibrary.setOperation(EquipmentLibraryOperationType.DELETE_TYPE);

		equipmentLibrary.setCtime(System.currentTimeMillis() / 1000);

		equipmentLibraryMapper.insert(equipmentLibrary);
	}

}
