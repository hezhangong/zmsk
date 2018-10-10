 package com.zmsk.upms.controller.manager;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.upms.dto.ActiveUserDTO;
import com.zmsk.upms.pojo.UpmsPermission;
import com.zmsk.upms.service.permission.PermissionService;

/****
 * 后台管理入口
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/")
public class ManagerController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO index() {

		// 获取当前登入用户
		Subject subject = SecurityUtils.getSubject();

		ActiveUserDTO activeUser = (ActiveUserDTO) subject.getPrincipal();

		List<UpmsPermission> permissions = permissionService.queryPermissionListByUserId(activeUser.getUserId());

		return ServiceResultDTO.success(permissions);
	}
}
