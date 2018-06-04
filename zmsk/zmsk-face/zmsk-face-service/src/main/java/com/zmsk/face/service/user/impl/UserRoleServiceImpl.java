package com.zmsk.face.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceUserRoleMapper;
import com.zmsk.face.pojo.FaceUserRole;
import com.zmsk.face.pojo.FaceUserRoleExample;
import com.zmsk.face.pojo.FaceUserRoleExample.Criteria;
import com.zmsk.face.service.user.UserRoleService;

/****
 * 用户角色操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private FaceUserRoleMapper userRoleMapper;

	@Override
	public boolean createUserRoleReleation(int userId, int roleId) {

		FaceUserRole userRole = new FaceUserRole();

		userRole.setUserId(userId);

		userRole.setRoleId(roleId);

		return userRoleMapper.insert(userRole) > 0;
	}

	@Override
	public boolean updateUserRoleReleation(int userId, int roleId) {

		FaceUserRoleExample example = new FaceUserRoleExample();

		FaceUserRole userRole = new FaceUserRole();

		userRole.setRoleId(roleId);

		Criteria criteria = example.createCriteria();

		criteria.andUserIdEqualTo(userId);

		return userRoleMapper.updateByExampleSelective(userRole, example) > 0;
	}

}
