package com.zmsk.face.controller.manager;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.organization.OrganizationService;

/****
 * 组织操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/organization")
public class OrganizationManagerController {

	@Autowired
	private OrganizationService organizationService;

	/****
	 * 新增组织
	 * 
	 * @param pid
	 *            产品Id
	 * @param name
	 *            名称
	 * @param description
	 *            描述
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions("upms:organization:create")
	public ServiceResultDTO createOrganization(@RequestParam(value = "pid", required = false, defaultValue = "0") int pid, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description) {

		if (StringUtils.isEmpty(name)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid name");
		}

		if (StringUtils.isEmpty(description)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid description");
		}

		boolean success = organizationService.createOrganization(pid, name, description);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ORGANIZATION_OPERATION_ERROR, "create organization error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 修改组织
	 * 
	 * @param organizationId
	 *            组织名称
	 * @param pid
	 *            产品Id
	 * @param name
	 *            名称
	 * @param description
	 *            描述
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	@ResponseBody
	@RequiresPermissions("upms:organization:update")
	public ServiceResultDTO updateOrganization(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "pid", required = false, defaultValue = "0") int pid, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId");
		}

		boolean success = organizationService.updateOrganization(organizationId, pid, name, description);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ORGANIZATION_OPERATION_ERROR, "update organization error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 删除组织
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "delete/{organizationId}", method = RequestMethod.DELETE)
	@ResponseBody
	@RequiresPermissions("upms:organization:delete")
	public ServiceResultDTO deleteOrganization(@PathVariable(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId");
		}

		boolean success = organizationService.deleteOrganization(organizationId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ORGANIZATION_OPERATION_ERROR, "delete organization fail");
		}

		return ServiceResultDTO.success();
	}

}
