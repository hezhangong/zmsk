package com.zmsk.face.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.common.exception.UnauthorizedAccessException;

@Controller
@RequestMapping("admin/login")
public class AdminLoginController {

	/****
	 * 管理严登入操作
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

		if (StringUtils.isEmpty(username)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid username");
		}

		if (StringUtils.isEmpty(password)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid password");
		}

		if (username.equals("zmskFaceAdmin") && username.equals("zmskFaceAdmin123456")) {
			return ServiceResultDTO.success();
		}

		throw new UnauthorizedAccessException(" Invalid username or password");
	}
}
