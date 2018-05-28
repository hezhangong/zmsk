package com.zmsk.face.service.permission;

import java.util.List;

import com.zmsk.face.pojo.FacePermission;

/****
 * 资源操作服务接口声明
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
	List<FacePermission> queryPermissionListByUserId(int userId);

	/****
	 * 获取用户Id对用的菜单列表
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 */
	List<FacePermission> queryMenuListByUserId(int userId);

	/****
	 * 创建权限
	 * 
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
	 * @param orders
	 *            排序
	 * @return
	 */
	boolean createPermission(int pid, String name, int type, String permissionValue, String uri, int orders);

}
