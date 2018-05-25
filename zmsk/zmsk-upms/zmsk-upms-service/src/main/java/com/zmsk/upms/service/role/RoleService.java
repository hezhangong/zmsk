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

}
