package com.zmsk.face.service.equipment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceEquipmentTagMapper;
import com.zmsk.face.pojo.FaceEquipmentTag;
import com.zmsk.face.pojo.FaceEquipmentTagExample;
import com.zmsk.face.pojo.FaceEquipmentTagExample.Criteria;
import com.zmsk.face.service.equipment.EquipmentTagService;

/****
 * 设备标签操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class EquipmentTagServiceImpl implements EquipmentTagService {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentTagServiceImpl.class);

	@Autowired
	private FaceEquipmentTagMapper equipmentTagMapper;

	@Override
	public boolean createEquipmentTag(int organizationId, String description) {

		FaceEquipmentTag equipmentTag = new FaceEquipmentTag();

		equipmentTag.setOrganizationId(organizationId);

		equipmentTag.setDescription(description);

		return equipmentTagMapper.insert(equipmentTag) > 0;
	}

	@Override
	public boolean updateEquipmentTag(int id, String description) {

		FaceEquipmentTag equipmentTag = equipmentTagMapper.selectByPrimaryKey(id);

		if (equipmentTag == null) {
			logger.warn("Invalid id {} can not fund equipment tag when update ", id);
			return false;
		}

		equipmentTag.setDescription(description);

		return equipmentTagMapper.updateByPrimaryKey(equipmentTag) > 0;
	}

	@Override
	public boolean deleteEquipmentTag(int id) {
		// TODO 设备标签对应的设备处理
		return equipmentTagMapper.deleteByPrimaryKey(id) > 0;
	}

	@Override
	public List<FaceEquipmentTag> queryEquipmentTagByOrganizationId(int organizationId) {

		FaceEquipmentTagExample example = new FaceEquipmentTagExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		return equipmentTagMapper.selectByExample(example);
	}

}
