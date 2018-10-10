package com.zmsk.face.service.user;

import java.util.List;

/***
 * 用户组织操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface UserOrganizationService {

	/***
	 * 创建用户组织关系
	 * 
	 * @param userId
	 *            用户Id
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	boolean createUserOrganizationReleation(int userId, int organizationId);

	/****
	 * 修改用户组织关系
	 * 
	 * @param userId
	 *            用户Id
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	boolean updateUserOrganizationReleation(int userId, int organizationId);

	/****
	 * 查询组织下用户Id列表
	 * 
	 * @param organizationId
	 *            组织id
	 * 
	 * @return
	 */
	List<Integer> queryUserIdsByOrganizationId(int organizationId);
}
