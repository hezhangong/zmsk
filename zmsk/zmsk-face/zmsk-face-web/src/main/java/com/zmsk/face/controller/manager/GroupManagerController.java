package com.zmsk.face.controller.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceGroup;
import com.zmsk.face.service.group.GroupService;

/****
 * 所属分组管理操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping(value = "manager/group/")
public class GroupManagerController {

	@Autowired
	private GroupService groupService;

	/****
	 * 创建分组信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param groupName
	 *            分组名称
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO createGroup(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "groupName") String groupName) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		if (StringUtils.isEmpty(groupName)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid group name");
		}

		boolean success = groupService.createGroup(organizationId, groupName);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.GROUP_OPERATION_ERROR, "create group error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 获取组织Id对应的分组信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryGroupByorganizationId(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceGroup> list = groupService.queryGroupByorganizationId(organizationId);

		return ServiceResultDTO.success(list);
	}

}
