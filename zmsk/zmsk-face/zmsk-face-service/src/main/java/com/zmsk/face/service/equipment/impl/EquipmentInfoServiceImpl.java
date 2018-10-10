package com.zmsk.face.service.equipment.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceEquipmentInfoMapper;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.pojo.FaceEquipmentInfo;
import com.zmsk.face.pojo.FaceEquipmentInfoExample;
import com.zmsk.face.pojo.FaceEquipmentInfoExample.Criteria;
/****
 * 组织下设备显示信息操作Serivce接口实现
 * 
 * @author warrior
 *
 */
import com.zmsk.face.service.equipment.EquipmentInfoService;
import com.zmsk.face.service.equipment.EquipmentService;
import com.zmsk.face.service.equipment.constants.EquipmentInfoSyncConstant;

@Service
@Transactional
public class EquipmentInfoServiceImpl implements EquipmentInfoService {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentInfoServiceImpl.class);

	@Autowired
	private FaceEquipmentInfoMapper equipmentInfoMapper;

	@Autowired
	private EquipmentService equipmentService;

	@Override
	public boolean addEquipmentInfo(String title, String logo, String telNumber, String address, int organizationId) {

		FaceEquipmentInfo equipmentInfo = new FaceEquipmentInfo();

		equipmentInfo.setTitle(title);

		equipmentInfo.setLogo(logo);

		equipmentInfo.setTelNumber(telNumber);

		equipmentInfo.setAddress(address);

		equipmentInfo.setOrganizationId(organizationId);

		equipmentInfo.setIsSync(EquipmentInfoSyncConstant.need_sync);

		equipmentInfo.setCtime(System.currentTimeMillis() / 1000);

		return equipmentInfoMapper.insert(equipmentInfo) > 0;
	}

	@Override
	public FaceEquipmentInfo queryEquipmentInfoByOrganizationId(int organizationId) {

		FaceEquipmentInfoExample example = new FaceEquipmentInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		List<FaceEquipmentInfo> list = equipmentInfoMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

	@Override
	public boolean updateEquipmentInfo(int id, String title, String logo, String telNumber, String address) {

		FaceEquipmentInfo faceEquipmentInfo = equipmentInfoMapper.selectByPrimaryKey(id);

		boolean needSync = false;

		if (faceEquipmentInfo == null) {
			logger.info("can not fund equipment info when update id is {}", id);
			return false;
		}

		if (!StringUtils.isEmpty(title) && !faceEquipmentInfo.getTitle().equals(title)) {
			needSync = true;
			faceEquipmentInfo.setTitle(title);
		}

		if (!StringUtils.isEmpty(logo) && !faceEquipmentInfo.getLogo().equals(logo)) {
			needSync = true;
			faceEquipmentInfo.setLogo(logo);
		}

		if (!StringUtils.isEmpty(telNumber) && !faceEquipmentInfo.getTelNumber().equals(telNumber)) {
			needSync = true;
			faceEquipmentInfo.setTelNumber(telNumber);
		}

		if (!StringUtils.isEmpty(address) && !faceEquipmentInfo.getAddress().equals(address)) {
			needSync = true;
			faceEquipmentInfo.setAddress(address);
		}

		if (needSync) {
			faceEquipmentInfo.setIsSync(EquipmentInfoSyncConstant.need_sync);
		}

		return equipmentInfoMapper.updateByPrimaryKey(faceEquipmentInfo) > 0;
	}

	@Override
	public FaceEquipmentInfo queryEquipmentInfoByDeviceId(int deviceId) {

		FaceEquipment equipment = equipmentService.queryEquipmentById(deviceId);

		if (equipment == null) {
			return null;
		}

		int organizationId = equipment.getOrganizationId();

		FaceEquipmentInfoExample example = new FaceEquipmentInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		criteria.andIsSyncEqualTo(EquipmentInfoSyncConstant.need_sync);

		List<FaceEquipmentInfo> list = equipmentInfoMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

}
