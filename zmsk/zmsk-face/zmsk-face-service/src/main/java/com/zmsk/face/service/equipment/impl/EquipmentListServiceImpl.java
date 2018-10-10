package com.zmsk.face.service.equipment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceEquipmentListMapper;
import com.zmsk.face.pojo.FaceEquipmentList;
import com.zmsk.face.service.equipment.EquipmentListService;

/****
 * 设备绑定操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class EquipmentListServiceImpl implements EquipmentListService {

	@Autowired
	private FaceEquipmentListMapper equipmentListMapper;

	@Override
	public boolean createEquipmentList(String deviceNumber, String deviceIP) {

		FaceEquipmentList equipmentList = new FaceEquipmentList();

		equipmentList.setDeviceNumber(deviceNumber);

		equipmentList.setDeviceIP(deviceIP);

		equipmentList.setCtime(System.currentTimeMillis() / 1000);

		return equipmentListMapper.insert(equipmentList) > 0;
	}

}
