package com.zmsk.face.service.library.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.common.pagehelper.PageHelper;
import com.zmsk.common.pagehelper.PageInfo;
import com.zmsk.common.utils.PageUtils;
import com.zmsk.face.mapper.FaceLibraryMapper;
import com.zmsk.face.mapper.custom.library.CustomerLibraryMapper;
import com.zmsk.face.pojo.FaceLibrary;
import com.zmsk.face.pojo.FaceLibraryExample;
import com.zmsk.face.pojo.FaceLibraryExample.Criteria;
import com.zmsk.face.service.library.FaceLibraryEquipmentService;
import com.zmsk.face.service.library.FaceLibraryService;

/***
 * 人脸库操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class FaceLibraryServiceImpl implements FaceLibraryService {

	@Autowired
	private FaceLibraryMapper faceLibraryMapper;

	@Autowired
	private CustomerLibraryMapper customerLibraryMapper;

	@Autowired
	private FaceLibraryEquipmentService libraryEquipmentService;

	@Override
	public boolean addFaceLibrary(String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int flag, int organizationId, List<Integer> equipmentIds) {

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

		library.setCtime(System.currentTimeMillis() / 1000);

		boolean success = faceLibraryMapper.insert(library) > 0;

		if (success) {
			libraryEquipmentService.addLibraryEquipment(library.getId(), equipmentIds);
		}

		return success;
	}

	@Override
	public List<FaceLibrary> queryLibraryList(int organizationId, int flag, int pageSize, int pageNum) {

		FaceLibraryExample example = new FaceLibraryExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		criteria.andFlagEqualTo(flag);

		example.setOrderByClause(" id DESC ");

		// 分页处理
		PageHelper.startPage(pageNum, pageSize);

		List<FaceLibrary> faceLibrarys = faceLibraryMapper.selectByExample(example);

		//PageInfo<FaceLibrary> pageInfo = new PageInfo<>(faceLibrarys);

		return faceLibrarys;
	}

	@Override
	public List<FaceLibrary> queryLibraryListByEquipmentId(int equipmentId, int flag, int pageSize, int pageNum) {

		int pageStart = PageUtils.getPageStart(pageSize, pageNum);

		List<FaceLibrary> list = customerLibraryMapper.queryLibraryListByEquipmentId(equipmentId, flag, pageSize, pageStart);

		//PageInfo<FaceLibrary> pageInfo = new PageInfo<>(list);

		return list;
	}

}
