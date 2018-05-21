package com.zmsk.upms.service.permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.upms.mapper.UpmsPermissionMapper;
import com.zmsk.upms.pojo.UpmsPermission;
import com.zmsk.upms.service.permission.PermissionService;

/****
 * 权限操作接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private UpmsPermissionMapper permissionMapper;

	@Override
	public List<UpmsPermission> queryPermissionListByUserId(int userId) {
		return permissionMapper.queryPermissionListByUserId(userId);
	}

	@Override
	public List<UpmsPermission> queryMenuListByUserId(int userId) {
		return permissionMapper.queryMenuListByUserId(userId);
	}

}
