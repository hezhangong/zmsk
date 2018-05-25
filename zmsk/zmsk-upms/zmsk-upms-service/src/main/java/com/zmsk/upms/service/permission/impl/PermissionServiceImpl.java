package com.zmsk.upms.service.permission.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.upms.mapper.UpmsPermissionMapper;
import com.zmsk.upms.mapper.UpmsUserMapper;
import com.zmsk.upms.pojo.UpmsPermission;
import com.zmsk.upms.pojo.UpmsUser;
import com.zmsk.upms.service.permission.PermissionService;
import com.zmsk.upms.service.permission.constants.PermissionStatusConstants;
import com.zmsk.upms.service.user.constants.UserLockStatusConstants;

/****
 * 权限操作接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	private UpmsPermissionMapper permissionMapper;

	@Autowired
	private UpmsUserMapper userMapper;

	@Override
	public List<UpmsPermission> queryPermissionListByUserId(int userId) {

		UpmsUser user = userMapper.selectByPrimaryKey(userId);

		if (user == null || user.getLocked() == UserLockStatusConstants.LOCKED) {
			logger.warn(" user can not find or locked when query permission list by user id {} ", userId);
			return Collections.emptyList();
		}
		return permissionMapper.queryPermissionListByUserId(userId);
	}

	@Override
	public List<UpmsPermission> queryMenuListByUserId(int userId) {
		return permissionMapper.queryMenuListByUserId(userId);
	}

	@Override
	public boolean createPermission(int systemId, int pid, String name, int type, String permissionValue, String uri, String icon, long orders) {

		UpmsPermission permission = new UpmsPermission();

		permission.setSystemId(systemId);

		permission.setPid(pid);

		permission.setName(name);

		permission.setType(type);

		permission.setPermissionValue(permissionValue);

		permission.setUri(uri);

		permission.setIcon(icon);

		permission.setOrders(orders);

		permission.setStatus(PermissionStatusConstants.enable);

		permission.setCtime(System.currentTimeMillis() / 1000);

		return permissionMapper.insert(permission) > 0;
	}

}
