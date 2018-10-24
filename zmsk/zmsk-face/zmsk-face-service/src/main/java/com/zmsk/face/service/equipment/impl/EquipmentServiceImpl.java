package com.zmsk.face.service.equipment.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.common.exception.UnauthorizedAccessException;
import com.zmsk.common.utils.DateUtils;
import com.zmsk.common.utils.StringDigestUtils;
import com.zmsk.face.dto.equipment.EquipmentRemarkDTO;
import com.zmsk.face.mapper.FaceEquipmentMapper;
import com.zmsk.face.mapper.FaceSerialNumberMapper;
import com.zmsk.face.mapper.custom.equipment.CustomerEquipmentMapper;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.pojo.FaceEquipmentExample;
import com.zmsk.face.pojo.FaceEquipmentExample.Criteria;
import com.zmsk.face.pojo.FaceSerialNumber;
import com.zmsk.face.service.equipment.EquipmentService;
import com.zmsk.face.service.equipment.constants.EquipmentConstants;
import com.zmsk.face.service.equipment.constants.EquipmentStatus;
import com.zmsk.face.service.equipment.dto.DeviceLoginResultDTO;

/****
 * 设备管理操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

	// 账号前缀
	private static final String NUMBER_PERFIEX = "zd9";

	@Autowired
	private FaceEquipmentMapper equipmentMapper;

	@Autowired
	private FaceSerialNumberMapper serailNumberMapper;

	@Autowired
	private CustomerEquipmentMapper customerEquipmetMapper;

	@Override
	public boolean createEquipment(int organizationId, int factoryId, int count, String password, int type, int renewalFee) {

		FaceEquipment equipment = null;

		for (int i = 0; i < count; i++) {

			equipment = new FaceEquipment();

			equipment.setOrganizationId(organizationId);
			
			equipment.setFactoryId(factoryId);

			FaceSerialNumber faceSerialNumber = new FaceSerialNumber();

			serailNumberMapper.insertSerialNumber(faceSerialNumber);

			int serialNumber = faceSerialNumber.getId();

			equipment.setEquipmentNumber(NUMBER_PERFIEX + type + serialNumber);

			String digestPassword = StringDigestUtils.md5(password);

			equipment.setEquipmentPwd(digestPassword);

			equipment.setEquipmentPlainPwd(password);

			equipment.setEquipmentType(type);

			equipment.setStatus(EquipmentStatus.UNABLE);

			equipment.setRenewalFee(renewalFee);
			
			equipment.setRemark(String.valueOf(type) + String.valueOf(serialNumber));

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
	public boolean inactivateEquipment(int deviceId) {
		
		FaceEquipment equipment = equipmentMapper.selectByPrimaryKey(deviceId);
		
		equipment.setStatus(EquipmentStatus.DISABLE);
		
		equipment.setEndTime(System.currentTimeMillis() / 1000);
		
		equipment.setMacId(null);
		
		return equipmentMapper.updateByPrimaryKey(equipment) > 0;
	}
	
	@Override
	public boolean deleteEquipment(int deviceId) {
		return equipmentMapper.deleteByPrimaryKey(deviceId) > 0;
	}

	@Override
	public boolean checkEquipment(String macId, String equipmentNumber, String version) {
		
		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andMacIdEqualTo(macId);
		
		criteria.andEquipmentNumberEqualTo(equipmentNumber);

		List<FaceEquipment> list = equipmentMapper.selectByExample(example);
		
		if (list!=null && list.size()>0) {
			for (FaceEquipment equipment : list) {
				
				equipment.setVersion(version);
				
				equipment.setLastLoginTime(System.currentTimeMillis() / 1000);
				
				equipmentMapper.updateByPrimaryKey(equipment);
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<FaceEquipment> queryEquipmentList(Integer organizationId, Integer factoryId) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		if (organizationId != null && organizationId != 1) {//总部可以查询所有记录
			criteria.andOrganizationIdEqualTo(organizationId);
		}
		
		if (factoryId != null) {
			criteria.andFactoryIdEqualTo(factoryId);
		}

		return equipmentMapper.selectByExample(example);
	}

	@Override
	public FaceEquipment queryEquipmentById(int deviceId) {

		return equipmentMapper.selectByPrimaryKey(deviceId);
	}

	@Override
	public List<EquipmentRemarkDTO> queryEquipmentRemarkByorganizationId(int organizationId) {
		return customerEquipmetMapper.queryEquipmentRemarkByorganizationId(organizationId);
	}

	@Override
	public FaceEquipment queryEquipmentByNumber(String deviceNumber) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andEquipmentNumberEqualTo(deviceNumber);

		List<FaceEquipment> list = equipmentMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

	@Override
	public int updateEquipmentPassword(int equipmentId, boolean flag, String newPassword, String oldPassword, Integer organizationId, Integer factoryId) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andIdEqualTo(equipmentId);

		if (flag) {
			criteria.andEquipmentPlainPwdEqualTo(oldPassword);
		}

		List<FaceEquipment> list = equipmentMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return EquipmentConstants.OLD_PASSWORD_ERROR;
		}

		FaceEquipment equipment = list.get(0);
		
		if (flag) {
			String newPasswordDigest = StringDigestUtils.md5(newPassword);
			
			equipment.setEquipmentPlainPwd(newPassword);
			
			equipment.setEquipmentPwd(newPasswordDigest);
		}
		
		if (organizationId != null) {
			equipment.setOrganizationId(organizationId);
		}
		
		if (factoryId != null) {
			equipment.setFactoryId(factoryId);
		}

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

	@Override
	public DeviceLoginResultDTO deviceLogin(String deviceNumber, String devicePassword, String macId) {

		FaceEquipmentExample example = new FaceEquipmentExample();

		Criteria criteria = example.createCriteria();

		criteria.andEquipmentNumberEqualTo(deviceNumber);

		criteria.andEquipmentPlainPwdEqualTo(devicePassword);

		criteria.andStatusEqualTo(EquipmentStatus.ENABLE);

		List<FaceEquipment> list = equipmentMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			throw new UnauthorizedAccessException("Invalid deviceNumber or devicePassword");
		}

		FaceEquipment equipment = list.get(0);

		String dbMacId = equipment.getMacId();

		// 第一次登入
		if (StringUtils.isEmpty(dbMacId) && StringUtils.isEmpty(macId)) {
			// 生产物理Id
			macId = UUID.randomUUID().toString();

			equipment.setMacId(macId);

			// 更新到物理IdDB
			equipmentMapper.updateByPrimaryKey(equipment);

			return new DeviceLoginResultDTO(deviceNumber, equipment.getId(), macId);
		}

		// 不是同一个设备登入
		if (!dbMacId.equals(macId)) {
			return null;
		}

		return new DeviceLoginResultDTO(deviceNumber, equipment.getId(), macId);

	}
}
