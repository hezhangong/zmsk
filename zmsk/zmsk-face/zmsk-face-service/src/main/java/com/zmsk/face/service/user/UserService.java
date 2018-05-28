package com.zmsk.face.service.user;

import java.util.List;

import com.zmsk.face.pojo.FaceUser;

/****
 * 用户操作服务接口声明
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
	FaceUser queryUserByName(String username);

	/****
	 * 创建用户
	 * 
	 * @param username
	 *            账号
	 * @param password
	 *            密码
	 * @param realName
	 *            用户真实名称
	 * @param avatar
	 *            头像地址
	 * @param phone
	 *            电话号码
	 * @param email
	 *            邮箱
	 * @param sex
	 *            性别
	 * @return
	 */
	boolean createUser(String username, String password, String realName, String avatar, String phone, String email, int sex);

	/****
	 * 查询用户信息
	 * 
	 * @param search
	 *            查询条件
	 * @return
	 */
	List<FaceUser> queryUserList(String search);

	/****
	 * 删除用户
	 * 
	 * @param ids
	 *            主键Id列表
	 * @return
	 */
	boolean deleteUser(List<Integer> ids);

	/****
	 * 修改会员信息
	 * 
	 * @param id
	 *            用户Id
	 * @param realName
	 *            真实名称
	 * @param phone
	 *            电话号码
	 * @param sex
	 *            性别
	 * @param avatar
	 *            头像地址
	 * @param email
	 *            邮箱
	 * @return
	 */
	boolean updateUser(int id, String realName, String phone, int sex, String avatar, String email);

}
