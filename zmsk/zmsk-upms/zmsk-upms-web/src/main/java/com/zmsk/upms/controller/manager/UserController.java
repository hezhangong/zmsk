package com.zmsk.upms.controller.manager;

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
import com.zmsk.upms.pojo.UpmsUser;
import com.zmsk.upms.service.user.UserService;

/****
 * 会员操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("user/")
public class UserController {

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
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@RequiresPermissions("upms:user:create")
	@ResponseBody
	public ServiceResultDTO createUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "realName") String realName, @RequestParam(value = "phone") String phone, @RequestParam(value = "sex") int sex, @RequestParam(value = "avatar", required = false, defaultValue = "") String avatar, @RequestParam(value = "email", required = false, defaultValue = "") String email) {

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

		if (sex == 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid sex ");
		}

		boolean success = userService.createUser(username, password, realName, avatar, phone, email, sex);

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

		List<UpmsUser> userList = userService.queryUserList(search);

		return ServiceResultDTO.success(userList);
	}

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
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	@RequiresPermissions("upms:user:update")
	@ResponseBody
	public ServiceResultDTO updateUser(@RequestParam(value = "id") int id, @RequestParam(value = "realName") String realName, @RequestParam(value = "phone") String phone, @RequestParam(value = "sex") int sex, @RequestParam(value = "avatar") String avatar, @RequestParam(value = "email") String email) {

		if (id == 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid user id");
		}

		boolean success = userService.updateUser(id, realName, phone, sex, avatar, email);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.USER_OPERATION_ERROR, "修改用户操作失败");
		}

		return ServiceResultDTO.success();
	}

}