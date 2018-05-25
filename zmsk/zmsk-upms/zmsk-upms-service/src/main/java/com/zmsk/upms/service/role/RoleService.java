package com.zmsk.upms.service.role;

import java.util.List;

import com.zmsk.upms.pojo.UpmsRole;

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
	List<UpmsRole> queryRoleListByUserId(int userId);

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
	 * @return
	 */
	boolean createRole(String name, String title, String description, long orders);

	/***
	 * 查询角色列表
	 * 
	 * @param search
	 *            查询条件
	 * @return
	 */
	List<UpmsRole> queryRoleList(String search);

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
	 * @return
	 */
	boolean updateRole(int id, String name, String title, String description, long orders);

	/***
	 * 删除角色信息
	 * 
	 * @param ids
	 *            角色主键Id列表
	 * @return
	 */
	boolean deleteRole(List<Integer> ids);

}
