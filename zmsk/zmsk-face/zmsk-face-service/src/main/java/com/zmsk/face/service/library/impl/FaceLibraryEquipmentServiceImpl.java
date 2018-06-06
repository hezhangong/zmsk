package com.zmsk.face.service.library.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceEquipmentLibraryMapper;
import com.zmsk.face.pojo.FaceEquipmentLibrary;
import com.zmsk.face.service.library.FaceLibraryEquipmentService;
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

	@Override
	public boolean addLibraryEquipment(int libraryId, List<Integer> equipmentIds) {

		FaceEquipmentLibrary equipmentLibrary = null;

		for (int equipmentId : equipmentIds) {

			equipmentLibrary = new FaceEquipmentLibrary();

			equipmentLibrary.setLibraryId(libraryId);

			equipmentLibrary.setEquipmentId(equipmentId);

			equipmentLibrary.setSyncStatus(EquipmentLibrarySyncStatus.UNSYNC);

			equipmentLibraryMapper.insert(equipmentLibrary);
		}

		return true;
	}

}
