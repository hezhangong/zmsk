package com.zmsk.upms.service.role.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.upms.mapper.UpmsRoleMapper;
import com.zmsk.upms.mapper.UpmsUserMapper;
import com.zmsk.upms.pojo.UpmsRole;
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

}
