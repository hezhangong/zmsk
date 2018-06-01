package com.zmsk.face.controller.manager;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.user.dto.ActiveUserDTO;

/***
 * 后台首页controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/")
public class ManagerController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO index() {

		// 获取当前登入用户
		Subject subject = SecurityUtils.getSubject();

		ActiveUserDTO activeUser = (ActiveUserDTO) subject.getPrincipal();

		return ServiceResultDTO.success(activeUser);
	}
}
