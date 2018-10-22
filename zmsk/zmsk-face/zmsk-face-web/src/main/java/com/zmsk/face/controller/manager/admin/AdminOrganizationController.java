package com.zmsk.face.controller.manager.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceOrganization;
import com.zmsk.face.service.organization.OrganizationService;

/****
 * 管理员组织操作
 * 
 */
@Controller
@RequestMapping("manager/admin/organization/")
public class AdminOrganizationController {

	@Autowired
	private OrganizationService organizationService;

	/****
	 * 新增组织
	 * 
	 * @param pid
	 *            父Id
	 * @param name
	 *            名称
	 * @param description
	 *            描述
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO createOrganization(@RequestParam(value = "pid", required = false, defaultValue = "0") int pid, @RequestParam(value = "name") String name, @RequestParam(value = "description") String description) {

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
	 *            父Id
	 * @param name
	 *            名称
	 * @param description
	 *            描述
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
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
	@RequestMapping(value = "delete/{organizationId}", method = RequestMethod.GET)
	@ResponseBody
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

	/****
	 * 获取工厂名称查询工厂列表
	 * 
	 * @param organizationName
	 *            工厂名称
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryOrganizationList(@RequestParam(value = "organizationName", required = false) String organizationName) {
		
		List<FaceOrganization> result = organizationService.queryOrganizationList(organizationName);
		
		return ServiceResultDTO.success(result);
	}

	/****
	 * 根据组织Id获取工厂信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "{organizationId}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryOrganizationById(@PathVariable(value = "organizationId") int organizationId) {

		FaceOrganization organization = organizationService.queryOrganizationById(organizationId);

		return ServiceResultDTO.success(organization);
	}
}
