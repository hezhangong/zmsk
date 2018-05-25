package com.zmsk.upms.controller.manager;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.upms.service.permission.PermissionService;

/****
 * 权限操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("permission/")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	/****
	 * 创建权限
	 * 
	 * @param systemId
	 *            所属系统Id
	 * @param pid
	 *            所属上级Id
	 * @param name
	 *            名称
	 * @param type
	 *            类型
	 * @param permissionValue
	 *            权限值
	 * @param uri
	 *            路径
	 * @param icon
	 *            图标
	 * @param orders
	 *            排序
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@RequiresPermissions("upms:permission:create")
	@ResponseBody
	public ServiceResultDTO createPermission(@RequestParam(value = "systemId") int systemId, @RequestParam(value = "pid", defaultValue = "0", required = false) int pid, @RequestParam(value = "name") String name, @RequestParam(value = "type") int type, @RequestParam(value = "permissionValue", required = false, defaultValue = "") String permissionValue, @RequestParam(value = "uri", required = false, defaultValue = "") String uri, @RequestParam(value = "icon", required = false, defaultValue = "") String icon, @RequestParam(value = "orders", defaultValue = "0", required = false) int orders) {

		if (systemId == 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " Invalid system id");
		}

		if (StringUtils.isEmpty(name)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid name");
		}

		if (type == 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid type");
		}

		if (StringUtils.isEmpty(permissionValue)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid permissionValue");
		}

		boolean success = permissionService.createPermission(systemId, pid, name, type, permissionValue, uri, icon, orders);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.PERMISSION_OPERATION_ERROR, "创建权失败");
		}

		return ServiceResultDTO.success();
	}

}
