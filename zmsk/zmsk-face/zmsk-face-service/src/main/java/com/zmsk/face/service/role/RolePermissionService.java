package com.zmsk.face.service.role;

import java.util.List;

/****
 * 角色权限操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface RolePermissionService {

	/****
	 * 创建角色权限资源关联
	 * 
	 * @param roleId
	 *            角色Id
	 * @param permissionIds
	 *            权限Id列表
	 * @return
	 */
	boolean createRolePermissionRelation(int roleId, List<Integer> permissionIds);

	/****
	 * 修改角色权限资源关联
	 * 
	 * @param roleId
	 *            角色Id
	 * @param permissionIds
	 *            权限Id列表
	 * @return
	 */
	boolean updateRolePermissionRelation(int roleId, List<Integer> permissionIds);

	/****
	 * 获取角色对应的权限资源信息Id
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	List<Integer> queryRolePermissionByRoleId(int roleId);

	/***
	 * 删除角色对应的权限资源信息
	 * 
	 * @param roleIds
	 *            角色Id列表
	 * @return
	 */
	boolean deleteRolePermissionByRoleIds(List<Integer> roleIds);
}
