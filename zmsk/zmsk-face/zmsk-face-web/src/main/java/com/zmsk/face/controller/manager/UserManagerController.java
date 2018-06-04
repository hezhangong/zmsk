package com.zmsk.face.controller.manager;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.service.user.UserService;
import com.zmsk.face.service.user.constants.UserConstants;

/****
 * 会员操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/user/")
public class UserManagerController {

	@Autowired
	private UserService userService;

	/****
	 * 创建会员操作
	 * 
	 * @param username
	 *            账号
	 * @param password
	 *            密码
	 * @param realName
	 *            真实名
	 * @param phone
	 *            电话号码
	 * @param sex
	 *            性别
	 * @param avatar
	 *            头像地址
	 * @param email
	 *            邮箱
	 * @param orgainzationId
	 *            组织Id
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@RequiresPermissions("upms:user:create")
	@ResponseBody
	public ServiceResultDTO createUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "realName") String realName, @RequestParam(value = "phone") String phone, @RequestParam(value = "sex") int sex, @RequestParam(value = "avatar", required = false, defaultValue = "") String avatar, @RequestParam(value = "email", required = false, defaultValue = "") String email, @RequestParam(value = "organizationId") int orgainzationId, @RequestParam(value = "roleId") int roleId) {

		if (StringUtils.isEmpty(username)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid username ");
		}

		if (StringUtils.isEmpty(password)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid password ");
		}

		if (StringUtils.isEmpty(realName)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid realName ");
		}

		if (StringUtils.isEmpty(phone)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid phone ");
		}

		if (sex <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid sex ");
		}

		if (orgainzationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid orgainzationId ");
		}

		if (roleId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid roleId ");
		}

		boolean success = userService.createUser(username, password, realName, avatar, phone, email, sex, roleId, orgainzationId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.USER_OPERATION_ERROR, "create user fail");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 获取用户列表
	 * 
	 * @param search
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@RequiresPermissions("upms:user:read")
	@ResponseBody
	public ServiceResultDTO queryUserList(@RequestParam(value = "search", defaultValue = "", required = false) String search) {

		List<FaceUser> userList = userService.queryUserList(search);

		return ServiceResultDTO.success(userList);
	}

	/****
	 * 根据Id获取用户信息
	 * 
	 * @param userId
	 *            用户Id
	 * @return
	 */
	@RequestMapping(value = "{userId}", method = RequestMethod.GET)
	@RequiresPermissions("upms:user:read")
	@ResponseBody
	public ServiceResultDTO queryUserById(@PathVariable(value = "userId") int userId) {

		if (userId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid userId ");
		}

		FaceUser user = userService.queryUserById(userId);

		return ServiceResultDTO.success(user);
	}

	/****
	 * 删除用户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.DELETE)
	@RequiresPermissions("upms:user:delete")
	@ResponseBody
	public ServiceResultDTO deleteUser(@PathVariable("ids") String ids) {

		ids = "[" + ids + "]";

		List<Integer> listId = JSON.parseArray(ids, Integer.class);

		boolean success = userService.deleteUser(listId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.USER_OPERATION_ERROR, "删除用户操作失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 修改用户信息
	 * 
	 * @param id
	 *            主键Id
	 * @param realName
	 *            用户真实姓名
	 * @param phone
	 *            电话
	 * @param sex
	 *            性别
	 * @param avatar
	 *            头像地址
	 * @param email
	 *            邮箱
	 * @param roleId
	 *            角色Id
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("upms:user:update")
	@ResponseBody
	public ServiceResultDTO updateUser(@RequestParam(value = "userId") int userId, @RequestParam(value = "realName", required = false, defaultValue = "") String realName, @RequestParam(value = "phone", required = false, defaultValue = "") String phone, @RequestParam(value = "sex", required = false, defaultValue = "0") int sex, @RequestParam(value = "avatar", required = false, defaultValue = "") String avatar, @RequestParam(value = "email", required = false, defaultValue = "") String email, @RequestParam(value = "roleId", required = false, defaultValue = "0") int roleId,
			@RequestParam(value = "organizationId", required = false, defaultValue = "0") int organizationId) {

		if (userId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid user id");
		}

		boolean success = userService.updateUser(userId, realName, phone, sex, avatar, email, roleId, organizationId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.USER_OPERATION_ERROR, "修改用户操作失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 修改用户密码
	 * 
	 * @param userId
	 *            用户Id
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	@RequestMapping(value = "update/password", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO updatePassword(@RequestParam(value = "userId") int userId, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {

		if (userId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid user id");
		}

		if (StringUtils.isEmpty(oldPassword)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid oldPassword");
		}

		if (StringUtils.isEmpty(newPassword)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid newPassword");
		}

		int result = userService.updatePassword(userId, newPassword, oldPassword);

		// 原始密码错误
		if (result == UserConstants.OLD_PASSWORD_ERROR) {
			return new ServiceResultDTO(BaseResultCode.INVALID_OLDPASSWORD, "原始密码错误");
		}

		if (result == UserConstants.FAIL) {
			return new ServiceResultDTO(BaseResultCode.USER_OPERATION_ERROR, "密码修改操作失败");
		}

		return ServiceResultDTO.success();
	}

}
