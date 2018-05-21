package com.zmsk.upms.service.permission;

import java.util.List;

import com.zmsk.upms.pojo.UpmsPermission;

/****
 * 权限操作接口声明
 * 
 * @author warrior
 *
 */
public interface PermissionService {

	/****
	 * 获取用户Id对应的权限信息列表
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	List<UpmsPermission> queryPermissionListByUserId(int userId);

	/****
	 * 获取用户Id对用的菜单列表
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 */
	List<UpmsPermission> queryMenuListByUserId(int userId);

}
