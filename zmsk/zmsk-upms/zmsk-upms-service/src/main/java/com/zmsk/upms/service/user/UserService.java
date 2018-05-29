package com.zmsk.upms.service.user;

import com.zmsk.upms.pojo.UpmsUser;

/****
 * 用户服务操作service接口声明
 * 
 * @author warrior
 *
 */
public interface UserService {

	/***
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	UpmsUser queryUserByName(String username);

}
