package com.zmsk.face.service.role.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zmsk.face.mapper.FaceRoleMapper;
import com.zmsk.face.mapper.FaceUserMapper;
import com.zmsk.face.mapper.custom.role.CustomFaceRoleMapper;
import com.zmsk.face.pojo.FaceRole;
import com.zmsk.face.pojo.FaceRoleExample;
import com.zmsk.face.pojo.FaceRoleExample.Criteria;
import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.service.role.RoleService;
import com.zmsk.face.service.user.constants.UserLockStatusConstants;

/****
 * 角色操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private FaceUserMapper userMapper;

	@Autowired
	private CustomFaceRoleMapper customRoleMapper;

	@Autowired
	private FaceRoleMapper roleMapper;

	@Override
	public List<FaceRole> queryRoleListByUserId(int userId) {

		FaceUser user = userMapper.selectByPrimaryKey(userId);

		if (user == null || user.getLocked() == UserLockStatusConstants.LOCKED) {
			logger.warn(" user can not fund or locked when query role list by user id {}", userId);
			return Collections.emptyList();
		}

		return customRoleMapper.queryRoleListByUserId(userId);
	}

	@Override
	public boolean createRole(String name, String title, String description, int orders) {

		FaceRole role = new FaceRole();

		role.setName(name);

		role.setTitle(title);

		role.setDescription(description);

		role.setOrders(orders);

		role.setCtime(System.currentTimeMillis() / 1000);

		return roleMapper.insert(role) > 0;
	}

	@Override
	public List<FaceRole> queryRoleList(String search) {

		FaceRoleExample example = new FaceRoleExample();

		if (!StringUtils.isEmpty(search)) {
			example.or().andTitleLike("%" + search + "%");
		}

		return roleMapper.selectByExample(example);
	}

	@Override
	public boolean updateRole(int id, String name, String title, String description, int orders) {

		FaceRole role = roleMapper.selectByPrimaryKey(id);

		if (role == null) {
			logger.warn("Invalid roleId {} can not fund role when update role ", id);
			return false;
		}

		if (!StringUtils.isEmpty(name) && !name.equals(role.getName())) {
			role.setName(name);
		}

		if (!StringUtils.isEmpty(title) && !title.equals(role.getTitle())) {
			role.setTitle(title);
		}

		if (!StringUtils.isEmpty(description) && !description.equals(role.getDescription())) {
			role.setDescription(description);
		}

		if (orders != role.getOrders()) {
			role.setOrders(orders);
		}

		return roleMapper.updateByPrimaryKey(role) > 0;
	}

	@Override
	public boolean deleteRole(List<Integer> ids) {

		FaceRoleExample example = new FaceRoleExample();

		Criteria criteria = example.createCriteria();

		criteria.andRoleIdIn(ids);

		return roleMapper.deleteByExample(example) > 0;
	}

}
