package com.zmsk.face.service.library.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zmsk.common.pagehelper.PageHelper;
import com.zmsk.common.utils.BeanUtils;
import com.zmsk.common.utils.PageUtils;
import com.zmsk.face.dto.library.EquipmetLibraryDTO;
import com.zmsk.face.dto.library.SyncFaceLibraryDTO;
import com.zmsk.face.mapper.FaceLibraryMapper;
import com.zmsk.face.mapper.custom.library.CustomerEquipmentLibraryMapper;
import com.zmsk.face.mapper.custom.library.CustomerLibraryMapper;
import com.zmsk.face.pojo.FaceLibrary;
import com.zmsk.face.pojo.FaceLibraryExample;
import com.zmsk.face.pojo.FaceLibraryExample.Criteria;
import com.zmsk.face.service.group.GroupService;
import com.zmsk.face.service.library.FaceLibraryEquipmentService;
import com.zmsk.face.service.library.FaceLibraryService;
import com.zmsk.face.service.library.constants.EquipmentLibraryOperationType;
import com.zmsk.face.service.library.constants.EquipmentLibrarySyncStatus;
import com.zmsk.face.service.library.dto.FaceLibraryDTO;

/***
 * 人脸库操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class FaceLibraryServiceImpl implements FaceLibraryService {

	private static final Logger logger = LoggerFactory.getLogger(FaceLibraryServiceImpl.class);

	@Autowired
	private FaceLibraryMapper faceLibraryMapper;

	@Autowired
	private CustomerLibraryMapper customerLibraryMapper;

	@Autowired
	private FaceLibraryEquipmentService libraryEquipmentService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private CustomerEquipmentLibraryMapper customerEquipmentLibraryMapper;

	@Override
	public boolean addFaceLibrary(String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int flag, int organizationId, int groupId, List<Integer> equipmentIds) {

		FaceLibrary library = new FaceLibrary();

		library.setName(name);

		library.setSex(sex);

		library.setIdNumber(idNumber);

		library.setNation(nation);

		library.setAddress(address);

		library.setAvatar(avatar);

		library.setRemark(remark);

		library.setFlag(flag);

		library.setOrganizationId(organizationId);

		library.setGroupId(groupId);

		library.setCtime(System.currentTimeMillis() / 1000);

		library.setEquipmentIds(StringUtils.join(equipmentIds, ","));

		boolean success = faceLibraryMapper.insert(library) > 0;

		// 添加人脸库设备同步数据
		libraryEquipmentService.addLibraryEquipment(library.getId(), equipmentIds);

		return success;
	}

	@Override
	public boolean updateFaceLibrary(int id, String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int flag, int groupId, List<Integer> equipmentIds) {

		FaceLibrary library = faceLibraryMapper.selectByPrimaryKey(id);

		if (library == null) {
			logger.error(" Invalid id {} can not fund face library info when update face library ", id);
			return false;
		}

		if (!StringUtils.isEmpty(name) && !name.equals(library.getName())) {
			library.setName(name);
		}

		if (!StringUtils.isEmpty(name) && !name.equals(library.getName())) {
			library.setName(name);
		}

		if (sex > 0 && sex != library.getSex()) {
			library.setSex(sex);
		}

		if (!StringUtils.isEmpty(idNumber) && !idNumber.equals(library.getIdNumber())) {
			library.setIdNumber(idNumber);
		}

		if (!StringUtils.isEmpty(nation) && !nation.equals(library.getNation())) {
			library.setNation(nation);
		}

		if (!StringUtils.isEmpty(address) && !address.equals(library.getAddress())) {
			library.setAddress(address);
		}

		if (!StringUtils.isEmpty(avatar) && !avatar.equals(library.getAvatar())) {
			library.setAvatar(avatar);
		}

		if (!StringUtils.isEmpty(remark) && !remark.equals(library.getRemark())) {
			library.setRemark(remark);
		}

		if (!StringUtils.isEmpty(address) && !address.equals(library.getAddress())) {
			library.setAddress(address);
		}

		if (flag > 0 && flag != library.getFlag()) {
			library.setFlag(flag);
		}

		if (groupId > 0 && groupId != library.getGroupId()) {
			library.setGroupId(groupId);
		}

		String oldEquipmentIds = library.getEquipmentIds();

		List<Integer> oldEquipmentIdList = null;

		if (!StringUtils.isEmpty(oldEquipmentIds)) {
			oldEquipmentIds = "[" + oldEquipmentIds + "]";
			oldEquipmentIdList = JSON.parseArray(oldEquipmentIds, Integer.class);
		}

		// 人脸库操作
		libraryEquipmentService.updateLibraryEquipment(id, equipmentIds, oldEquipmentIdList);

		library.setEquipmentIds(StringUtils.join(equipmentIds, ","));

		return faceLibraryMapper.updateByPrimaryKey(library) > 0;
	}

	@Override
	public FaceLibraryDTO queryLibraryById(int id) {

		FaceLibrary library = faceLibraryMapper.selectByPrimaryKey(id);

		if (library == null) {
			return null;
		}

		FaceLibraryDTO libraryDTO = new FaceLibraryDTO();

		BeanUtils.copyPropertiesNotForce(libraryDTO, library);

		String groupName = groupService.queryGroupNameById(library.getGroupId());

		libraryDTO.setGroupName(groupName);

		return libraryDTO;
	}

	@Override
	public List<FaceLibraryDTO> queryLibraryList(int organizationId, int flag, int pageSize, int pageNum) {

		FaceLibraryExample example = new FaceLibraryExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		criteria.andFlagEqualTo(flag);

		example.setOrderByClause(" id DESC ");

		// 分页处理
		PageHelper.startPage(pageNum, pageSize);

		List<FaceLibrary> faceLibrarys = faceLibraryMapper.selectByExample(example);

		// PageInfo<FaceLibrary> pageInfo = new PageInfo<>(faceLibrarys);

		return convertFaceLibraryList2DTO(faceLibrarys);
	}

	@Override
	public List<FaceLibraryDTO> queryLibraryListByEquipmentId(int equipmentId, int flag, int pageSize, int pageNum) {

		int pageStart = PageUtils.getPageStart(pageSize, pageNum);

		List<FaceLibrary> list = customerLibraryMapper.queryLibraryListByEquipmentId(equipmentId, flag, pageSize, pageStart);

		// PageInfo<FaceLibrary> pageInfo = new PageInfo<>(list);

		return convertFaceLibraryList2DTO(list);
	}

	@Override
	public List<SyncFaceLibraryDTO> queryUnSyncFaceLibrary(int equipmentId) {

		return customerLibraryMapper.queryUnSyncFaceLibrary(equipmentId);
	}

	@Override
	public FaceLibraryDTO queryLibraryBuIdNumber(String idNumber) {

		FaceLibraryExample example = new FaceLibraryExample();

		Criteria criteria = example.createCriteria();

		criteria.andIdNumberEqualTo(idNumber);

		List<FaceLibrary> libraryList = faceLibraryMapper.selectByExample(example);

		if (libraryList == null || libraryList.size() == 0) {
			return null;
		}

		FaceLibrary faceLibrary = libraryList.get(0);

		FaceLibraryDTO libraryDTO = new FaceLibraryDTO();

		BeanUtils.copyPropertiesNotForce(libraryDTO, faceLibrary);

		String groupName = groupService.queryGroupNameById(faceLibrary.getGroupId());

		libraryDTO.setGroupName(groupName);

		return libraryDTO;
	}

	private FaceLibraryDTO convertFaceLibrary2DTO(FaceLibrary faceLibrary) {

		FaceLibraryDTO libraryDTO = new FaceLibraryDTO();

		BeanUtils.copyPropertiesNotForce(libraryDTO, faceLibrary);

		String groupName = groupService.queryGroupNameById(faceLibrary.getGroupId());

		libraryDTO.setGroupName(groupName);

		List<EquipmetLibraryDTO> equipmentLibraryList = customerEquipmentLibraryMapper.queryEquipmentLibraryByLibraryId(faceLibrary.getId());

		if (equipmentLibraryList == null || equipmentLibraryList.size() == 0) {
			libraryDTO.setSupportDevice("暂无支持设备");
			return libraryDTO;
		}

		// 删除状态的设备Id列表
		List<Integer> deleTypeEquipmentIds = new ArrayList<>();

		for (EquipmetLibraryDTO equipmentLibrary : equipmentLibraryList) {

			int operation = equipmentLibrary.getOperation();

			int equipmentId = equipmentLibrary.getEquipmentId();

			if (operation == EquipmentLibraryOperationType.DELETE_TYPE) {
				deleTypeEquipmentIds.add(equipmentId);
			}
		}

		String equipmentIds = faceLibrary.getEquipmentIds();

		List<Integer> faceEquipmentIds = Collections.emptyList();

		if (!StringUtils.isEmpty(equipmentIds)) {
			equipmentIds = "[" + equipmentIds + "]";
			faceEquipmentIds = JSON.parseArray(equipmentIds, Integer.class);
		}

		libraryDTO = convertEquipmentLibraryList2SupportDevice(equipmentLibraryList, deleTypeEquipmentIds, faceEquipmentIds, libraryDTO);

		return libraryDTO;
	}

	private List<FaceLibraryDTO> convertFaceLibraryList2DTO(List<FaceLibrary> list) {

		if (list == null || list.size() == 0) {
			return Collections.emptyList();
		}

		List<FaceLibraryDTO> libraryList = new ArrayList<>(list.size());

		FaceLibraryDTO libraryDTO = null;

		for (FaceLibrary library : list) {

			libraryDTO = convertFaceLibrary2DTO(library);

			libraryList.add(libraryDTO);
		}

		return libraryList;
	}

	private FaceLibraryDTO convertEquipmentLibraryList2SupportDevice(List<EquipmetLibraryDTO> equipmentLibraryList, List<Integer> deleTypeEquipmentIds, List<Integer> faceEquipmentIds, FaceLibraryDTO libraryDTO) {

		StringBuilder supportDevice = new StringBuilder();

		StringBuilder supportDeviceIds = new StringBuilder();

		for (EquipmetLibraryDTO equipmentLibrary : equipmentLibraryList) {

			String remark = equipmentLibrary.getRemark();

			int syncStatus = equipmentLibrary.getSyncStatus();

			int equipmentId = equipmentLibrary.getEquipmentId();

			int operation = equipmentLibrary.getOperation();

			// 去除删除状态的设备Id
			if (operation == EquipmentLibraryOperationType.DELETE_TYPE && deleTypeEquipmentIds.contains(equipmentId)) {
				continue;
			}

			if (!faceEquipmentIds.contains(equipmentId)) {
				continue;
			}

			supportDeviceIds.append(equipmentLibrary.getEquipmentId());

			supportDevice.append(remark);

			if (syncStatus == EquipmentLibrarySyncStatus.SYNCED) {
				supportDevice.append("(").append("已同步").append(")");
			} else {
				supportDevice.append("(").append("未同步").append(")");
			}

			supportDevice.append(",");
			supportDeviceIds.append(",");
		}

		String supportDeviceStr = supportDevice.toString();

		String deviceIds = supportDeviceIds.toString();

		supportDeviceStr = supportDeviceStr.substring(0, supportDeviceStr.length() - 1);

		deviceIds = deviceIds.substring(0, deviceIds.length() - 1);

		libraryDTO.setSupportDevice(supportDeviceStr);

		libraryDTO.setEquipmentIds(deviceIds);

		return libraryDTO;
	}

}
