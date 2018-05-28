package com.zmsk.face.service.permission.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FacePermissionMapper;
import com.zmsk.face.mapper.FaceUserMapper;
import com.zmsk.face.mapper.custom.permission.CustomFacePermissionMapper;
import com.zmsk.face.pojo.FacePermission;
import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.service.permission.PermissionService;
import com.zmsk.face.service.user.constants.UserLockStatusConstants;

/****
 * 权限资源操作接口实现
 * 
 * @author warrior
 *
 */
@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {

	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	private FaceUserMapper userMapper;

	@Autowired
	private CustomFacePermissionMapper customPermissionMapper;

	@Autowired
	private FacePermissionMapper permissionMapper;

	@Override
	public List<FacePermission> queryPermissionListByUserId(int userId) {

		FaceUser user = userMapper.selectByPrimaryKey(userId);

		if (user == null || user.getLocked() == UserLockStatusConstants.LOCKED) {
			logger.warn(" user can not fund or locked when query permission list by user id {}", userId);
			return Collections.emptyList();
		}

		return customPermissionMapper.queryPermissionListByUserId(userId);
	}

	@Override
	public List<FacePermission> queryMenuListByUserId(int userId) {

		FaceUser user = userMapper.selectByPrimaryKey(userId);

		if (user == null || user.getLocked() == UserLockStatusConstants.LOCKED) {
			logger.warn(" user can not fund or locked when query permission list by user id {}", userId);
			return Collections.emptyList();
		}

		return customPermissionMapper.queryMenuListByUserId(userId);
	}

	@Override
	public boolean createPermission(int pid, String name, int type, String permissionValue, String uri, int orders) {

		FacePermission permission = new FacePermission();

		permission.setPid(pid);

		permission.setName(name);

		permission.setType(type);

		permission.setPermissionValue(permissionValue);

		permission.setUri(uri);

		permission.setOrders(orders);

		permission.setCtime(System.currentTimeMillis() / 1000);

		return permissionMapper.insert(permission) > 0;
	}

}
