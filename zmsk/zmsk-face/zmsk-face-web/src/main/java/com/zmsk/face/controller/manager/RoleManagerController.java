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
import com.alibaba.fastjson.JSONArray;
import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceRole;
import com.zmsk.face.service.role.RoleService;

/****
 * 角色操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/role/")
public class RoleManagerController {

	@Autowired
	private RoleService roleService;

	/****
	 * 新增角色
	 * 
	 * @param name
	 *            角色名称
	 * @param title
	 *            角色标题
	 * @param description
	 *            描述
	 * @param orders
	 *            排序值
	 * @param organizationId
	 *            组织Id
	 * @param permissionIdStr
	 *            权限Id字符串
	 * 
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@RequiresPermissions("upms:role:create")
	@ResponseBody
	public ServiceResultDTO createRole(@RequestParam(value = "name") String name, @RequestParam(value = "title") String title, @RequestParam(value = "description") String description, @RequestParam(value = "orders", required = false, defaultValue = "0") int orders, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "permissionIdStr") String permissionIdStr) {

		if (StringUtils.isEmpty(name)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid name ");
		}

		if (StringUtils.isEmpty(title)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid title ");
		}

		if (StringUtils.isEmpty(description)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid description ");
		}

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid organizationId ");
		}

		if (StringUtils.isEmpty(permissionIdStr)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid permissionIdStr ");
		}

		permissionIdStr = "[" + permissionIdStr + "]";

		List<Integer> permissionIds = JSON.parseArray(permissionIdStr, Integer.class);

		boolean success = roleService.createRole(name, title, description, orders, organizationId, permissionIds);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ROLE_OPERATION_ERROR, "新增角色失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 查询组织下角色列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@RequiresPermissions("upms:role:read")
	@ResponseBody
	public ServiceResultDTO queryOrganizationRoleList(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId");
		}

		List<FaceRole> roleList = roleService.queryOrganizationRoleList(organizationId);

		return ServiceResultDTO.success(roleList);
	}

	/****
	 * 根据角色Id获取角色信息
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	@RequestMapping(value = "{roleId}", method = RequestMethod.GET)
	@RequiresPermissions("upms:role:read")
	@ResponseBody
	public ServiceResultDTO queryRoleById(@PathVariable(value = "roleId") int roleId) {

		if (roleId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid roleId");
		}

		FaceRole role = roleService.queryRoleById(roleId);

		return ServiceResultDTO.success(role);
	}

	/****
	 * 修改角色信息
	 * 
	 * @param roleId
	 *            主键Id
	 * @param name
	 *            名称
	 * @param title
	 *            标题
	 * @param description
	 *            描述
	 * @param orders
	 *            排序值
	 * @param permissionIdStr
	 *            权限字符串
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@RequiresPermissions("upms:role:update")
	@ResponseBody
	public ServiceResultDTO updateRole(@RequestParam(value = "roleId") int roleId, @RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "title", required = false, defaultValue = "") String title, @RequestParam(value = "description", required = false, defaultValue = "") String description, @RequestParam(value = "orders", required = false, defaultValue = "0") int orders, @RequestParam(value = "permissionIdStr", required = false, defaultValue = "") String permissionIdStr) {

		if (roleId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid role id");
		}

		if (StringUtils.isEmpty(permissionIdStr)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid permissionIdStr ");
		}

		permissionIdStr = "[" + permissionIdStr + "]";

		List<Integer> permissionIds = JSON.parseArray(permissionIdStr, Integer.class);

		boolean success = roleService.updateRole(roleId, name, title, description, orders, permissionIds);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ROLE_OPERATION_ERROR, "修改角色信息操作失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 删除角色信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.POST)
	@RequiresPermissions("upms:role:delete")
	@ResponseBody
	public ServiceResultDTO deleteRole(@PathVariable(value = "ids") String ids) {

		ids = "[" + ids + "]";

		List<Integer> listId = JSON.parseArray(ids, Integer.class);

		boolean success = roleService.deleteRole(listId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ROLE_OPERATION_ERROR, "删除角色信息操作失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 查询角色拥有的权限树型列表
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "permission", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryTreeRolePermissionByRoleId(@RequestParam(value = "roleId") int roleId) {

		if (roleId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid roleId ");
		}

		JSONArray jsonArray = roleService.queryTreeRolePermissionByRoleId(roleId);

		return ServiceResultDTO.success(jsonArray);
	}
}
