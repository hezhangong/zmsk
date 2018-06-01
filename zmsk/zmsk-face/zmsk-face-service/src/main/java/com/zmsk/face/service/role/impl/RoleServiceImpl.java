package com.zmsk.face.service.role.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zmsk.face.mapper.FaceRoleMapper;
import com.zmsk.face.mapper.FaceUserMapper;
import com.zmsk.face.mapper.custom.role.CustomFaceRoleMapper;
import com.zmsk.face.pojo.FacePermission;
import com.zmsk.face.pojo.FaceRole;
import com.zmsk.face.pojo.FaceRoleExample;
import com.zmsk.face.pojo.FaceRoleExample.Criteria;
import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.service.permission.PermissionService;
import com.zmsk.face.service.permission.constants.PermissionTypeConstants;
import com.zmsk.face.service.role.RolePermissionService;
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
	private RolePermissionService rolePermissionService;

	@Autowired
	private PermissionService permissionService;

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
	public boolean createRole(String name, String title, String description, int orders, int organizationId, List<Integer> permissionIds) {

		FaceRole role = new FaceRole();

		role.setName(name);

		role.setTitle(title);

		role.setDescription(description);

		role.setOrders(orders);

		role.setOrganizationId(organizationId);

		role.setCtime(System.currentTimeMillis() / 1000);

		boolean success = roleMapper.insert(role) > 0;

		if (!success) {
			logger.error("create role error name {} ,organizationId {}", name, organizationId);
			return false;
		}

		// 关联角色和资源
		return rolePermissionService.createRolePermissionRelation(role.getRoleId(), permissionIds);
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
	public boolean updateRole(int id, String name, String title, String description, int orders, List<Integer> permissionIds) {

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

		// 角色权限关联
		rolePermissionService.updateRolePermissionRelation(id, permissionIds);

		return roleMapper.updateByPrimaryKey(role) > 0;
	}

	@Override
	public boolean deleteRole(List<Integer> ids) {

		FaceRoleExample example = new FaceRoleExample();

		Criteria criteria = example.createCriteria();

		criteria.andRoleIdIn(ids);

		// 删除关联的权限资源信息
		rolePermissionService.deleteRolePermissionByRoleIds(ids);

		return roleMapper.deleteByExample(example) > 0;
	}

	@Override
	public JSONArray queryTreeRolePermissionByRoleId(int roleId) {

		// 获取角色Id对应的权限资源信息Id
		List<Integer> rolePermissionIds = rolePermissionService.queryRolePermissionByRoleId(roleId);

		if (rolePermissionIds == null || rolePermissionIds.size() == 0) {
			return null;
		}

		List<FacePermission> permissions = permissionService.queryPermissionList();

		// 目录
		JSONArray folders = convert2Folders(permissions, rolePermissionIds);

		// 菜单
		folders = convert2Menus(folders, permissions, rolePermissionIds);

		return folders;
	}

	/****
	 * 获取角色权限资源目录
	 * 
	 * @param permissions
	 *            资源权限列表
	 * @param rolePermissionIds
	 *            角色对应资源Id列表
	 * @return
	 */
	private JSONArray convert2Folders(List<FacePermission> permissions, List<Integer> rolePermissionIds) {

		// 目录
		JSONArray folders = new JSONArray();

		JSONObject node = null;

		for (FacePermission permission : permissions) {

			int pid = permission.getPid();

			int type = permission.getType();

			int permissionId = permission.getPermissionId();

			if (pid != 0 || type != PermissionTypeConstants.CATALOG) {
				continue;
			}

			node = new JSONObject();

			node.put("id", permission.getPermissionId());

			node.put("name", permission.getName());

			if (rolePermissionIds.contains(permissionId)) {
				node.put("check", true);
			}

			folders.add(node);
		}

		return folders;
	}

	/***
	 * 
	 * @param folders
	 * @param permissions
	 * @param rolePermissionIds
	 * @return
	 */
	private JSONArray convert2Menus(JSONArray folders, List<FacePermission> permissions, List<Integer> rolePermissionIds) {

		// 菜单
		JSONArray menus = null;

		JSONObject node = null;

		for (Object folder : folders) {

			menus = new JSONArray();

			int id = ((JSONObject) folder).getIntValue("id");

			for (FacePermission permission : permissions) {

				int pid = permission.getPid();

				int type = permission.getType();

				int permissionId = permission.getPermissionId();

				if (pid != id || type != PermissionTypeConstants.MENU) {
					continue;
				}

				node = new JSONObject();

				node.put("id", permission.getPermissionId());

				node.put("name", permission.getName());

				if (rolePermissionIds.contains(permissionId)) {
					node.put("check", true);
				}

				menus.add(node);
			}

			if (menus.size() < 0) {
				continue;
			}

			menus = convert2Button(menus, permissions, rolePermissionIds);

			((JSONObject) folder).put("children", menus);
		}

		return folders;
	}

	private JSONArray convert2Button(JSONArray menus, List<FacePermission> permissions, List<Integer> rolePermissionIds) {

		// 按钮
		JSONArray buttons = null;

		JSONObject node = null;

		for (Object menu : menus) {

			int id = ((JSONObject) menu).getIntValue("id");

			buttons = new JSONArray();

			for (FacePermission permission : permissions) {

				int pid = permission.getPid();

				int type = permission.getType();

				int permissionId = permission.getPermissionId();

				if (pid != id || type != PermissionTypeConstants.BUTTON) {
					continue;
				}

				node = new JSONObject();

				node.put("id", permission.getPermissionId());

				node.put("name", permission.getName());

				if (rolePermissionIds.contains(permissionId)) {
					node.put("check", true);
				}

				buttons.add(permission);
			}

			if (buttons.size() > 0) {
				((JSONObject) menu).put("children", buttons);
			}
		}

		return menus;
	}

}
