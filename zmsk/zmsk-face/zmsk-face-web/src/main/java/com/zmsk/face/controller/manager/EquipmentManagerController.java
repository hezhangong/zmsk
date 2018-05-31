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
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.equipment.equipmentService;

/****
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/equipment/")
public class EquipmentManagerController {

	@Autowired
	private equipmentService equipmentService;

	/****
	 * 创建设备
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param count
	 *            数量
	 * @param password
	 *            初始密码
	 * @param type
	 *            类型
	 * @param renewalFee
	 *            续费值
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO createEquipment(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "count") int count, @RequestParam(value = "password") String password, @RequestParam(value = "type") int type, @RequestParam(value = "renewalFee") int renewalFee) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		if (count <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid count ");
		}

		if (StringUtils.isEmpty(password)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid password ");
		}

		if (type <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid type ");
		}

		boolean success = equipmentService.createEquipment(organizationId, count, password, type, renewalFee);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "create equipment error");
		}

		return ServiceResultDTO.success();
	}

	/***
	 * 激活设备
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	@RequestMapping(value = "activate/{deviceId}", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO activateEquipment(@PathVariable(value = "deviceId") int deviceId) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid device id");
		}

		boolean success = equipmentService.activateEquipment(deviceId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "activate equipment error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 获取组织对应的设备列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "list/{organizationId}")
	@ResponseBody
	public ServiceResultDTO queryEquipmentByOrganizationId(@PathVariable(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceEquipment> result = equipmentService.queryEquipmentByOrganizationId(organizationId);

		return ServiceResultDTO.success(result);
	}

	/****
	 * 绑定设备标签
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @param tagId
	 *            标签Id
	 * @return
	 */
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO bindEquipmentTag(@RequestParam(value = "equipmentId") int equipmentId, @RequestParam(value = "tagId") int tagId) {

		if (equipmentId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipment id");
		}

		if (tagId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid tag id");
		}

		boolean success = equipmentService.bindEquipmentTag(equipmentId, tagId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "bind equipment tag error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 绑定设备标签
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @return
	 */
	@RequestMapping(value = "unbind/{equipmentId}")
	@ResponseBody
	public ServiceResultDTO unbindEquipmentTag(@PathVariable(value = "equipmentId") int equipmentId) {

		if (equipmentId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipment id");
		}

		boolean success = equipmentService.unbindEquipmentTag(equipmentId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "unbind equipment tag error");
		}

		return ServiceResultDTO.success();
	}

}
