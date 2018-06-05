package com.zmsk.face.service.equipment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.common.utils.DateUtils;
import com.zmsk.common.utils.StringDigestUtils;
import com.zmsk.face.mapper.FaceEquipmentMapper;
import com.zmsk.face.mapper.FaceSerialNumberMapper;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.pojo.FaceEquipmentExample;
import com.zmsk.face.pojo.FaceEquipmentExample.Criteria;
import com.zmsk.face.pojo.FaceSerialNumber;
import com.zmsk.face.service.equipment.equipmentService;
import com.zmsk.face.service.equipment.constants.EquipmentConstants;
import com.zmsk.face.service.equipment.constants.EquipmentStatus;

/****
 * 设备管理操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class EquipmentServiceImpl implements equipmentService {

	// 账号前缀
	private static final String NUMBER_PERFIEX = "zd";

	@Autowired
	private FaceEquipmentMapper equipmentMapper;

	@Autowired
	private FaceSerialNumberMapper serailNumberMapper;

	@Override
	public boolean createEquipment(int organizationId, int count, String password, int type, int renewalFee) {

		FaceEquipment equipment = null;

		for (int i = 0; i < count; i++) {

			equipment = new FaceEquipment();

			equipment.setOrganizationId(organizationId);

			FaceSerialNumber faceSerialNumber = new FaceSerialNumber();

			serailNumberMapper.insertSerialNumber(faceSerialNumber);

			int serialNumber = faceSerialNumber.getId();

			equipment.setEquipmentNumber(NUMBER_PERFIEX + serialNumber);

			String digestPassword = StringDigestUtils.md5(password);

			equipment.setEquipmentPwd(digestPassword);

			equipment.setEquipmentPlainPwd(password);

			equipment.setEquipmentType(type);

			equipment.setStatus(EquipmentStatus.UNABLE);

			equipment.setRenewalFee(renewalFee);

			equipment.setCreateTime(System.currentTimeMillis() / 1000);

			equipmentMapper.insert(equipment);
		}

		return true;
	}

	@Override
	public boolean activateEquipment(int deviceId) {

		FaceEquipment equipment = equipmentMapper.selectByPrimaryKey(deviceId);

		equipment.setStatus(EquipmentStatus.ENABLE);

		equipment.setActivationTime(System.currentTimeMillis() / 1000);

		// 默认结束时间为当前时间加20年
		long endTime = DateUtils.getCurrentPlusYearTime(10);

		equipment.setEndTime(endTime);

		return equipmentMapper.updateByPrimaryKey(equipment) > 0;
	}

	@Override
	public List<FaceEquipment> queryEquipmentByOrganizationId(int organizationId) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		return equipmentMapper.selectByExample(example);
	}

	@Override
	public FaceEquipment queryEquipmentById(int deviceId) {

		return equipmentMapper.selectByPrimaryKey(deviceId);
	}

	@Override
	public int updateEquipmentPassword(int equipmentId, String newPassword, String oldPassword) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(equipmentId);

		criteria.andEquipmentPlainPwdEqualTo(oldPassword);

		List<FaceEquipment> list = equipmentMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return EquipmentConstants.OLD_PASSWORD_ERROR;
		}

		FaceEquipment equipment = list.get(0);

		String newPasswordDigest = StringDigestUtils.md5(newPassword);

		equipment.setEquipmentPlainPwd(newPassword);

		equipment.setEquipmentPwd(newPasswordDigest);

		boolean success = equipmentMapper.updateByPrimaryKey(equipment) > 0;

		if (success) {
			return EquipmentConstants.SUCCESS;
		}

		return EquipmentConstants.FAIL;
	}

	@Override
	public boolean updateEquipmentRemarkById(int deviceId, String remark) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(deviceId);

		FaceEquipment record = new FaceEquipment();

		record.setRemark(remark);

		return equipmentMapper.updateByExampleSelective(record, example) > 0;
	}
}
