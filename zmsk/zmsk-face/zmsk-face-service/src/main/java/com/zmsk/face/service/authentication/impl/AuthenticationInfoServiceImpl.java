package com.zmsk.face.service.authentication.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zmsk.common.pagehelper.PageHelper;
import com.zmsk.face.mapper.FaceAuthenticationInfoMapper;
import com.zmsk.face.pojo.FaceAuthenticationInfo;
import com.zmsk.face.pojo.FaceAuthenticationInfoExample;
import com.zmsk.face.pojo.FaceAuthenticationInfoExample.Criteria;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.authentication.AuthenticationInfoService;
import com.zmsk.face.service.authentication.constant.AuthenticResultConstant;
import com.zmsk.face.service.equipment.EquipmentService;

/****
 * 认证信息操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class AuthenticationInfoServiceImpl implements AuthenticationInfoService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInfoServiceImpl.class);

	@Autowired
	private FaceAuthenticationInfoMapper authenticationInfoMapper;

	@Autowired
	private EquipmentService equipmentService;

	@Override
	public boolean addAuthenticationInfo(String name, String idNumber, String nation, String address, String avatar, int sex, int type, int result, String deviceNumber, int groupId) {

		// 获取设备信息
		FaceEquipment equipment = equipmentService.queryEquipmentByNumber(deviceNumber);

		if (equipment == null) {
			logger.warn("can not fund equipment when add authentication info equipment number is {}", deviceNumber);
			return false;
		}

		FaceAuthenticationInfo authenticationInfo = new FaceAuthenticationInfo();

		authenticationInfo.setName(name);

		authenticationInfo.setAvatar(avatar);

		authenticationInfo.setIdNumber(idNumber);

		authenticationInfo.setNation(nation);

		authenticationInfo.setAddress(address);

		authenticationInfo.setOrganizationId(equipment.getOrganizationId());

		authenticationInfo.setEquipmentId(equipment.getId());

		String source = equipment.getRemark();

		if (StringUtils.isEmpty(source)) {
			source = "";
		}

		authenticationInfo.setSource(source);

		authenticationInfo.setSex(sex);

		authenticationInfo.setType(type);

		authenticationInfo.setResult(result);

		authenticationInfo.setGroupId(groupId);

		authenticationInfo.setCtime(System.currentTimeMillis() / 1000);

		return authenticationInfoMapper.insert(authenticationInfo) > 0;
	}

	@Override
	public List<FaceAuthenticationInfo> queryAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum) {

		FaceAuthenticationInfoExample example = new FaceAuthenticationInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		if (!StringUtils.isEmpty(search)) {
			example.or().andNameLike("%" + search + "%");
			example.or().andIdNumberLike("%" + search + "%");
		}

		example.setOrderByClause(" id DESC ");

		// 分页处理
		PageHelper.startPage(pageNum, pageSize);

		List<FaceAuthenticationInfo> list = authenticationInfoMapper.selectByExample(example);

		// PageInfo<FaceAuthenticationInfo> pageInfo = new PageInfo<>(list);

		return list;
	}

	@Override
	public List<FaceAuthenticationInfo> queryWarnAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum) {

		FaceAuthenticationInfoExample example = new FaceAuthenticationInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		criteria.andResultEqualTo(AuthenticResultConstant.AUTHENTIC_FAIL);

		if (!StringUtils.isEmpty(search)) {
			example.or().andNameLike("%" + search + "%");
			example.or().andIdNumberLike("%" + search + "%");
		}

		example.setOrderByClause(" id DESC ");

		// 分页处理
		PageHelper.startPage(pageNum, pageSize);

		return authenticationInfoMapper.selectByExample(example);
	}

}
