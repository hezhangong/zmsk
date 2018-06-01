package com.zmsk.face.service.role;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.zmsk.face.pojo.FaceRole;

/****
 * 角色操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface RoleService {

	/***
	 * 根据用户Id获取所属角色
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 */
	List<FaceRole> queryRoleListByUserId(int userId);

	/****
	 * 新增角色
	 * 
	 * @param name
	 *            角色名称
	 * @param title
	 *            角色标题
	 * @param description
	 *            描述
	 * @param orders
	 *            排序值
	 * @param organizationId
	 *            组织Id
	 * @param permissionIds
	 *            权限资源列表
	 * @return
	 */
	boolean createRole(String name, String title, String description, int orders, int organizationId, List<Integer> permissionIds);

	/***
	 * 查询角色列表
	 * 
	 * @param search
	 *            查询条件
	 * @return
	 */
	List<FaceRole> queryRoleList(String search);

	/****
	 * 修改角色信息
	 * 
	 * @param id
	 *            主键Id
	 * @param name
	 *            名称
	 * @param title
	 *            标题
	 * @param description
	 *            描述
	 * @param orders
	 *            排序值
	 * @param permissionIds
	 *            权限资源列表
	 * @return
	 */
	boolean updateRole(int id, String name, String title, String description, int orders, List<Integer> permissionIds);

	/***
	 * 删除角色信息
	 * 
	 * @param ids
	 *            角色主键Id列表
	 * @return
	 */
	boolean deleteRole(List<Integer> ids);

	/****
	 * 获取树型的角色权限列表
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	JSONArray queryTreeRolePermissionByRoleId(int roleId);

}
