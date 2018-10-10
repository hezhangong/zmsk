package com.zmsk.face.service.user;

import java.util.List;

/****
 * 用户角色操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface UserRoleService {

	/***
	 * 创建用户角色关联关系
	 * 
	 * @param userId
	 *            用户Id
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	boolean createUserRoleReleation(int userId, int roleId);

	/***
	 * 修改用户角色关联关系
	 * 
	 * @param userId
	 *            用户Id
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	boolean updateUserRoleReleation(int userId, int roleId);

	/****
	 * 删除用户角色关联关系
	 * 
	 * @param roleIds
	 *            角色Ids
	 * @return
	 */
	boolean deleteUserRoleReleation(List<Integer> roleIds);
}
