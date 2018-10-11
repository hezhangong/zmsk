package com.zmsk.face.service.library.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zmsk.face.mapper.FaceEquipmentLibraryMapper;
import com.zmsk.face.mapper.FaceLibraryMapper;
import com.zmsk.face.mapper.custom.library.CustomerEquipmentLibraryMapper;
import com.zmsk.face.pojo.FaceEquipmentLibrary;
import com.zmsk.face.pojo.FaceEquipmentLibraryExample;
import com.zmsk.face.pojo.FaceEquipmentLibraryExample.Criteria;
import com.zmsk.face.pojo.FaceLibrary;
import com.zmsk.face.service.library.FaceLibraryEquipmentService;
import com.zmsk.face.service.library.constants.EquipmentLibraryOperationType;
import com.zmsk.face.service.library.constants.EquipmentLibrarySyncStatus;
import com.zmsk.face.service.library.constants.FaceLibraryErrorCode;

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
	private FaceLibraryMapper libraryMapper;

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
	public boolean updateLibraryEquipment(int libraryId, List<Integer> equipmentIds, List<Integer> oldEquipmentIds) {

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
	public boolean flagsyncedFaceLibrary(int id, int operation) {

		FaceEquipmentLibrary equipmentLibrary = new FaceEquipmentLibrary();

		equipmentLibrary.setSyncStatus(EquipmentLibrarySyncStatus.SYNCED);

		equipmentLibrary.setRemark("同步人脸库成功");

		equipmentLibrary.setId(id);
		
		equipmentLibrary.setOperation(operation);

		return equipmentLibraryMapper.updateByPrimaryKeySelective(equipmentLibrary) > 0;
	}

	@Override
	public boolean flagEnableFaceLibrary(int id, int errorCode) {

		FaceEquipmentLibrary equipmentLibrary = equipmentLibraryMapper.selectByPrimaryKey(id);

		int libraryId = equipmentLibrary.getLibraryId();

		if (errorCode == FaceLibraryErrorCode.DOWNLOAD_FACE_LIBRARY) {
			equipmentLibrary.setRemark("下载人脸库失败");
			equipmentLibraryMapper.updateByPrimaryKey(equipmentLibrary);
			return true;
		}

		if (errorCode == FaceLibraryErrorCode.REPEAT_ADD_FACE_LIBRARY) {
			equipmentLibrary.setRemark("重复添加人脸库");
			equipmentLibrary.setSyncStatus(EquipmentLibrarySyncStatus.SYNCED);
			equipmentLibraryMapper.updateByPrimaryKey(equipmentLibrary);
			return true;
		}

		// 无效人脸库特征
		equipmentLibraryMapper.deleteByPrimaryKey(id);

		FaceLibrary faceLibrary = libraryMapper.selectByPrimaryKey(libraryId);

		String remark = faceLibrary.getRemark();

		remark = remark + "(人脸图片不清晰)";

		String equipIds = faceLibrary.getEquipmentIds();

		List<Integer> equipIdList = JSON.parseArray("[" + equipIds + "]", Integer.class);

		Iterator<Integer> iterator = equipIdList.iterator();

		while (iterator.hasNext()) {
			int equipmentId = iterator.next();
			if (equipmentId == equipmentLibrary.getEquipmentId()) {
				iterator.remove();
				break;
			}
		}

		faceLibrary.setRemark(remark);

		faceLibrary.setEquipmentIds(StringUtils.join(equipIdList, ","));

		return libraryMapper.updateByPrimaryKey(faceLibrary) > 0;
	}

	@Override
	public List<FaceEquipmentLibrary> queryLibrarySynchroRecord(int libraryId) {

		FaceEquipmentLibraryExample example = new FaceEquipmentLibraryExample();

		Criteria criteria = example.createCriteria();

		criteria.andLibraryIdEqualTo(libraryId);
		
		example.setOrderByClause(" id desc");

		return equipmentLibraryMapper.selectByExample(example);
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
