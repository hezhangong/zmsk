package com.zmsk.face.service.library.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.common.utils.BeanUtils;
import com.zmsk.face.mapper.FaceVisitorLibraryMapper;
import com.zmsk.face.pojo.FaceVisitorLibrary;
import com.zmsk.face.pojo.FaceVisitorLibraryExample;
import com.zmsk.face.pojo.FaceVisitorLibraryExample.Criteria;
import com.zmsk.face.service.group.GroupService;
import com.zmsk.face.service.library.VisistorLibraryService;
import com.zmsk.face.service.library.dto.FaceVisitorLibraryDTO;

/****
 * 访客人脸库操作服务接口实现
 * 
 * @author warrior
 *
 */
@Transactional
@Service
public class VisistorLibraryServiceImpl implements VisistorLibraryService {

	@Autowired
	private FaceVisitorLibraryMapper visitorLibraryMapper;

	@Autowired
	private GroupService groupService;

	@Override
	public boolean addFaceLibrary(String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int organizationId, int groupId) {

		FaceVisitorLibrary visitorLibrary = new FaceVisitorLibrary();

		visitorLibrary.setName(name);

		visitorLibrary.setSex(sex);

		visitorLibrary.setIdNumber(idNumber);

		visitorLibrary.setNation(nation);

		visitorLibrary.setAddress(address);

		visitorLibrary.setAvatar(avatar);

		visitorLibrary.setRemark(remark);

		visitorLibrary.setOrganizationId(organizationId);

		visitorLibrary.setGroupId(groupId);

		visitorLibrary.setCtime(System.currentTimeMillis() / 1000);

		return visitorLibraryMapper.insert(visitorLibrary) > 0;
	}

	@Override
	public List<FaceVisitorLibraryDTO> queryVisitorLibraryByOrganizationId(int organizationId) {

		FaceVisitorLibraryExample example = new FaceVisitorLibraryExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		List<FaceVisitorLibrary> list = visitorLibraryMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return Collections.emptyList();
		}

		List<FaceVisitorLibraryDTO> result = new ArrayList<>(list.size());

		FaceVisitorLibraryDTO faceVisistoryLibraryDto = null;

		for (FaceVisitorLibrary visitorLibrary : list) {

			faceVisistoryLibraryDto = new FaceVisitorLibraryDTO();

			BeanUtils.copyPropertiesNotNull(faceVisistoryLibraryDto, visitorLibrary);

			String groupName = groupService.queryGroupNameById(visitorLibrary.getGroupId());

			faceVisistoryLibraryDto.setGroupName(groupName);

			result.add(faceVisistoryLibraryDto);
		}

		return result;
	}

	@Override
	public FaceVisitorLibraryDTO queryVisitorLibrary(int id) {

		FaceVisitorLibrary visitorLibrary = visitorLibraryMapper.selectByPrimaryKey(id);

		if (visitorLibrary == null) {
			return null;
		}

		FaceVisitorLibraryDTO faceVisistoryLibraryDto = new FaceVisitorLibraryDTO();

		BeanUtils.copyPropertiesNotNull(faceVisistoryLibraryDto, visitorLibrary);

		String groupName = groupService.queryGroupNameById(visitorLibrary.getGroupId());

		faceVisistoryLibraryDto.setGroupName(groupName);

		return faceVisistoryLibraryDto;
	}

}
