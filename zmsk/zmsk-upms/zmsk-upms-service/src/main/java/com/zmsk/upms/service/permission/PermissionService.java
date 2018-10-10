package com.zmsk.upms.service.permission;

import java.util.List;

import com.zmsk.upms.pojo.UpmsPermission;

/****
 * 权限资源操作接口声明
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

	/****
	 * 创建权限
	 * 
	 * @param systemId
	 *            所属系统Id
	 * @param pid
	 *            所属上级Id
	 * @param name
	 *            名称
	 * @param type
	 *            类型
	 * @param permissionValue
	 *            权限值
	 * @param uri
	 *            路径
	 * @param icon
	 *            图标
	 * @param orders
	 *            排序
	 * @return
	 */
	boolean createPermission(int systemId, int pid, String name, int type, String permissionValue, String uri, String icon, long orders);
}
