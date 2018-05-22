package com.zmsk.upms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
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

}
