package com.zmsk.face.service.authentication.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zmsk.common.pagehelper.PageHelper;
import com.zmsk.common.utils.BeanUtils;
import com.zmsk.common.utils.DateUtils;
import com.zmsk.face.mapper.FaceAuthenticationInfoMapper;
import com.zmsk.face.pojo.FaceAuthenticationInfo;
import com.zmsk.face.pojo.FaceAuthenticationInfoExample;
import com.zmsk.face.pojo.FaceAuthenticationInfoExample.Criteria;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.authentication.AuthenticationInfoService;
import com.zmsk.face.service.authentication.constant.AuthenticResultConstant;
import com.zmsk.face.service.authentication.dto.AuthenticationInfoDTO;
import com.zmsk.face.service.authentication.dto.ExportAuthenticationInfoDTO;
import com.zmsk.face.service.authentication.dto.VisistorInfoDTO;
import com.zmsk.face.service.equipment.EquipmentService;
import com.zmsk.face.service.group.GroupService;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

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

	@Autowired
	private GroupService groupService;

	@Override
	public boolean addAuthenticationInfo(String name, String idNumber, String nation, String address, String avatar, int sex, int type, int result, String deviceNumber, int groupId, long authTimeStamp, String idcardImage, String idcardInfo, String similarDegree) {

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

		authenticationInfo.setCtime(authTimeStamp);

		authenticationInfo.setIdcardImage(idcardImage);

		authenticationInfo.setIdcardInfo(idcardInfo);

		authenticationInfo.setSimilarDegree(similarDegree);

		return authenticationInfoMapper.insert(authenticationInfo) > 0;
	}

	@Override
	public List<AuthenticationInfoDTO> queryAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum) {

		FaceAuthenticationInfoExample example = new FaceAuthenticationInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		if (!StringUtils.isEmpty(search)) {
			criteria.andNameLike("%" + search + "%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andIdNumberLike("%" + search + "%");
			example.or(criteria2);
		}

		example.setOrderByClause(" id DESC ");

		// 分页处理
		PageHelper.startPage(pageNum, pageSize);

		List<FaceAuthenticationInfo> list = authenticationInfoMapper.selectByExample(example);

		return convertAuthenticationInfo2DTO(list);
	}

	@Override
	public List<AuthenticationInfoDTO> queryWarnAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum) {

		FaceAuthenticationInfoExample example = new FaceAuthenticationInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		criteria.andResultEqualTo(AuthenticResultConstant.AUTHENTIC_FAIL);

		if (!StringUtils.isEmpty(search)) {
			criteria.andNameLike("%" + search + "%");
			example.or().andIdNumberLike("%" + search + "%");
		}

		example.setOrderByClause(" id DESC ");

		// 分页处理
		PageHelper.startPage(pageNum, pageSize);

		List<FaceAuthenticationInfo> list = authenticationInfoMapper.selectByExample(example);

		return convertAuthenticationInfo2DTO(list);
	}

	@Override
	public AuthenticationInfoDTO queryAuthenticationInfoById(int id) {

		FaceAuthenticationInfo authentication = authenticationInfoMapper.selectByPrimaryKey(id);

		AuthenticationInfoDTO authenticationInfoDTO = new AuthenticationInfoDTO();

		BeanUtils.copyPropertiesNotNull(authenticationInfoDTO, authentication);

		String groupName = groupService.queryGroupNameById(authentication.getGroupId());

		authenticationInfoDTO.setGroupName(groupName);

		return authenticationInfoDTO;
	}

	@Override
	public boolean registerVisitor(int id, String remark, int groupId) {

		FaceAuthenticationInfo authenticationInfo = new FaceAuthenticationInfo();

		authenticationInfo.setId(id);

		authenticationInfo.setRemark(remark);

		authenticationInfo.setGroupId(groupId);

		authenticationInfo.setRegisterTime(System.currentTimeMillis() / 1000);

		return authenticationInfoMapper.updateByPrimaryKeySelective(authenticationInfo) > 0;
	}

	@Override
	public List<VisistorInfoDTO> queryVisitorByOrganizationId(int organizationId) {

		FaceAuthenticationInfoExample example = new FaceAuthenticationInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		criteria.andRemarkIsNotNull();

		List<FaceAuthenticationInfo> authenticationList = authenticationInfoMapper.selectByExample(example);

		if (authenticationList == null || authenticationList.size() == 0) {
			return Collections.emptyList();
		}

		return convertAuthenticationInfo2VisistorDTO(authenticationList);
	}

	@Override
	public Workbook exportAuthenticationInfo(int organizationId, String dateStr) {

		Date date = DateUtils.convertStr2Date(dateStr);

		FaceAuthenticationInfoExample example = new FaceAuthenticationInfoExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		long startTime = DateUtils.getDateTime(date);

		long endTime = DateUtils.getDateTime(DateUtils.plusDay(date));

		criteria.andCtimeBetween(startTime, endTime);

		List<FaceAuthenticationInfo> list = authenticationInfoMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		List<ExportAuthenticationInfoDTO> reslt = convertAuthenticationInfo2ImportDTO(list);

		ExportParams params = new ExportParams(dateStr + "-认证记录", "认证记录");

		return ExcelExportUtil.exportExcel(params, ExportAuthenticationInfoDTO.class, reslt);
	}

	private List<AuthenticationInfoDTO> convertAuthenticationInfo2DTO(List<FaceAuthenticationInfo> list) {

		if (list == null || list.size() == 0) {
			return Collections.emptyList();
		}

		List<AuthenticationInfoDTO> result = new ArrayList<>(list.size());

		AuthenticationInfoDTO authenticationInfoDTO = null;

		for (FaceAuthenticationInfo authenticationInfo : list) {
			authenticationInfoDTO = new AuthenticationInfoDTO();
			BeanUtils.copyPropertiesNotNull(authenticationInfoDTO, authenticationInfo);
			String groupName = groupService.queryGroupNameById(authenticationInfo.getGroupId());
			authenticationInfoDTO.setGroupName(groupName);
			result.add(authenticationInfoDTO);
		}

		return result;
	}

	private List<VisistorInfoDTO> convertAuthenticationInfo2VisistorDTO(List<FaceAuthenticationInfo> list) {

		List<VisistorInfoDTO> result = new ArrayList<>(list.size());

		VisistorInfoDTO visistorInfo = null;

		for (FaceAuthenticationInfo authenticationInfo : list) {
			visistorInfo = new VisistorInfoDTO();
			BeanUtils.copyPropertiesNotNull(visistorInfo, authenticationInfo);
			String groupName = groupService.queryGroupNameById(authenticationInfo.getGroupId());
			visistorInfo.setGroupName(groupName);
			result.add(visistorInfo);
		}

		return result;
	}

	private List<ExportAuthenticationInfoDTO> convertAuthenticationInfo2ImportDTO(List<FaceAuthenticationInfo> list) {

		List<ExportAuthenticationInfoDTO> result = new ArrayList<>(list.size());

		ExportAuthenticationInfoDTO importAuthentication = null;

		for (FaceAuthenticationInfo authenticationInfo : list) {

			importAuthentication = new ExportAuthenticationInfoDTO();

			BeanUtils.copyPropertiesNotNull(importAuthentication, authenticationInfo);

			String groupName = groupService.queryGroupNameById(authenticationInfo.getGroupId());

			importAuthentication.setGroupName(groupName);

			importAuthentication.setCtime(new Date(authenticationInfo.getCtime() * 1000));

			result.add(importAuthentication);
		}

		return result;
	}

}
