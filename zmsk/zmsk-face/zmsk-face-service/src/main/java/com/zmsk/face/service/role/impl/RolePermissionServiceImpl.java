package com.zmsk.face.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceRolePermissionMapper;
import com.zmsk.face.mapper.custom.role.CustomFaceRolePermissionMapper;
import com.zmsk.face.pojo.FaceRolePermission;
import com.zmsk.face.pojo.FaceRolePermissionExample;
import com.zmsk.face.pojo.FaceRolePermissionExample.Criteria;
import com.zmsk.face.service.role.RolePermissionService;

/****
 * 角色权限操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private FaceRolePermissionMapper rolePermissionMapper;

	@Autowired
	private CustomFaceRolePermissionMapper customRolePermissionMapper;

	@Override
	public boolean createRolePermissionRelation(int roleId, List<Integer> permissionIds) {

		FaceRolePermission rolePermission = null;

		for (int permissionId : permissionIds) {

			rolePermission = new FaceRolePermission();

			rolePermission.setRoleId(roleId);

			rolePermission.setPermissionId(permissionId);

			rolePermissionMapper.insert(rolePermission);
		}

		return true;
	}

	@Override
	public boolean updateRolePermissionRelation(int roleId, List<Integer> permissionIds) {

		FaceRolePermissionExample example = new FaceRolePermissionExample();

		Criteria criteria = example.createCriteria();

		criteria.andRoleIdEqualTo(roleId);

		// 先删除角色对应权限资源
		rolePermissionMapper.deleteByExample(example);

		// 再创建相关资源权限
		return createRolePermissionRelation(roleId, permissionIds);
	}

	@Override
	public List<Integer> queryRolePermissionByRoleId(int roleId) {
		return customRolePermissionMapper.queryRolePermissionByRoleId(roleId);
	}

	@Override
	public boolean deleteRolePermissionByRoleIds(List<Integer> roleIds) {

		FaceRolePermissionExample example = new FaceRolePermissionExample();

		Criteria criteria = example.createCriteria();

		criteria.andRoleIdIn(roleIds);

		return rolePermissionMapper.deleteByExample(example) > 0;
	}
}
