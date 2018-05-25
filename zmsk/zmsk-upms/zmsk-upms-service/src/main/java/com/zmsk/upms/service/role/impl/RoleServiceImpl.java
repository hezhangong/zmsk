package com.zmsk.upms.service.role.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zmsk.upms.mapper.UpmsRoleMapper;
import com.zmsk.upms.mapper.UpmsUserMapper;
import com.zmsk.upms.pojo.UpmsRole;
import com.zmsk.upms.pojo.UpmsRoleExample;
import com.zmsk.upms.pojo.UpmsRoleExample.Criteria;
import com.zmsk.upms.pojo.UpmsUser;
import com.zmsk.upms.service.role.RoleService;
import com.zmsk.upms.service.user.constants.UserLockStatusConstants;

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
	private UpmsRoleMapper roleMapper;

	@Autowired
	private UpmsUserMapper userMapper;

	@Override
	public List<UpmsRole> queryRoleListByUserId(int userId) {

		UpmsUser user = userMapper.selectByPrimaryKey(userId);

		if (user == null || user.getLocked() == UserLockStatusConstants.LOCKED) {
			logger.warn(" user can not find or locked when query permission list by user id {} ", userId);
			return Collections.emptyList();
		}

		return roleMapper.queryRoleListByUserId(userId);
	}

	@Override
	public boolean createRole(String name, String title, String description, long orders) {

		UpmsRole role = new UpmsRole();

		role.setName(name);

		role.setTitle(title);

		role.setDescription(description);

		role.setOrders(orders);

		role.setCtime(System.currentTimeMillis() / 100);

		return roleMapper.insert(role) > 0;
	}

	@Override
	public List<UpmsRole> queryRoleList(String search) {

		UpmsRoleExample example = new UpmsRoleExample();

		if (!StringUtils.isEmpty(search)) {
			example.or().andTitleLike("%" + search + "%");
		}

		return roleMapper.selectByExample(example);
	}

	@Override
	public boolean updateRole(int id, String name, String title, String description, long orders) {

		UpmsRole role = roleMapper.selectByPrimaryKey(id);

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

		UpmsRoleExample example = new UpmsRoleExample();

		Criteria criteria = example.createCriteria();

		criteria.andRoleIdIn(ids);

		return roleMapper.deleteByExample(example) > 0;
	}

}
