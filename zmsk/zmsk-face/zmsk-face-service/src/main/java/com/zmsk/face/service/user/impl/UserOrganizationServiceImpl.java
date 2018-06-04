package com.zmsk.face.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceUserOrganizationMapper;
import com.zmsk.face.pojo.FaceUserOrganization;
import com.zmsk.face.pojo.FaceUserOrganizationExample;
import com.zmsk.face.pojo.FaceUserOrganizationExample.Criteria;
import com.zmsk.face.service.user.UserOrganizationService;

/***
 * 用户组织操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class UserOrganizationServiceImpl implements UserOrganizationService {

	@Autowired
	private FaceUserOrganizationMapper userOrganizationMapper;

	@Override
	public boolean createUserOrganizationReleation(int userId, int organizationId) {

		FaceUserOrganization userOrganization = new FaceUserOrganization();

		userOrganization.setUserId(userId);

		userOrganization.setOrganizationId(organizationId);

		return userOrganizationMapper.insert(userOrganization) > 0;
	}

	@Override
	public boolean updateUserOrganizationReleation(int userId, int organizationId) {

		FaceUserOrganization userOrganization = new FaceUserOrganization();

		userOrganization.setOrganizationId(organizationId);

		FaceUserOrganizationExample example = new FaceUserOrganizationExample();

		Criteria criteria = example.createCriteria();

		criteria.andUserIdEqualTo(userId);

		return userOrganizationMapper.updateByExampleSelective(userOrganization, example) > 0;
	}

}
