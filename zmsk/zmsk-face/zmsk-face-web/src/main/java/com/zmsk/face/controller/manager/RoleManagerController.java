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
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@RequiresPermissions("upms:role:create")
	@ResponseBody
	public ServiceResultDTO createRole(@RequestParam(value = "name") String name, @RequestParam(value = "title") String title, @RequestParam(value = "description") String description, @RequestParam(value = "orders", required = false, defaultValue = "0") int orders) {

		if (StringUtils.isEmpty(name)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid name ");
		}

		if (StringUtils.isEmpty(title)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid title ");
		}

		if (StringUtils.isEmpty(description)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid description ");
		}

		boolean success = roleService.createRole(name, title, description, orders);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ROLE_OPERATION_ERROR, "新增角色失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 查询角色列表
	 * 
	 * @param search
	 *            查询条件
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@RequiresPermissions("upms:role:read")
	@ResponseBody
	public ServiceResultDTO queryRoleList(@RequestParam(value = "search", defaultValue = "", required = false) String search) {

		List<FaceRole> roleList = roleService.queryRoleList(search);

		return ServiceResultDTO.success(roleList);
	}

	/****
	 * 修改角色信息
	 * 
	 * @param id
	 *            主键Id
	 * @param name
	 *            名称
	 * @param title
	 *            标题
	 * @param description
	 *            描述
	 * @param orders
	 *            排序值
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	@RequiresPermissions("upms:role:update")
	@ResponseBody
	public ServiceResultDTO updateRole(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name, @RequestParam(value = "title") String title, @RequestParam(value = "description") String description, @RequestParam(value = "orders") int orders) {

		if (id == 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid role id");
		}

		boolean success = roleService.updateRole(id, name, title, description, orders);

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
	@RequestMapping(value = "delete/{ids}", method = RequestMethod.DELETE)
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
}
