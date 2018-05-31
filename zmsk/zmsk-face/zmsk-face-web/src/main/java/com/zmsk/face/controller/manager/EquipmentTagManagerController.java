package com.zmsk.face.controller.manager;

import java.util.List;

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
import com.zmsk.face.pojo.FaceEquipmentTag;
import com.zmsk.face.service.equipment.EquipmentTagService;

/****
 * 设备标签操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/equipment/tag/")
public class EquipmentTagManagerController {

	@Autowired
	private EquipmentTagService equipmentTagService;

	/****
	 * 创建设备标签
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param description
	 *            描述
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO createEquipmentTag(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "description") String description) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		if (StringUtils.isEmpty(description)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid description");
		}

		boolean success = equipmentTagService.createEquipmentTag(organizationId, description);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_TAG_OPERATION_ERROR, "create equipment tag error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 修改设备标签
	 * 
	 * @param id
	 *            主键
	 * @param description
	 *            描述
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	@ResponseBody
	public ServiceResultDTO updateEquipmentTag(@RequestParam(value = "id") int id, @RequestParam(value = "description") String description) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		if (StringUtils.isEmpty(description)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid description");
		}

		boolean success = equipmentTagService.updateEquipmentTag(id, description);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_TAG_OPERATION_ERROR, "update equipment tag error");
		}

		return ServiceResultDTO.success();
	}

	/***
	 * 删除设备标签
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ServiceResultDTO deleteEquipmentTag(@PathVariable(value = "id") int id) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		boolean success = equipmentTagService.deleteEquipmentTag(id);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_TAG_OPERATION_ERROR, "delete equipment tag error");
		}

		return ServiceResultDTO.success();
	}

	/***
	 * 获取对应组织下的设备标签列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentTagByOrganizationId(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceEquipmentTag> list = equipmentTagService.queryEquipmentTagByOrganizationId(organizationId);

		return ServiceResultDTO.success(list);
	}

}
