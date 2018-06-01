package com.zmsk.face.service.user;

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
}
