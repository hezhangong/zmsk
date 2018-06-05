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
import com.zmsk.face.service.user.constants.UserConstants;

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
	 * 根据设备Id获取设备信息
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	@RequestMapping(value = "{deviceId}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentById(@PathVariable(value = "deviceId") int deviceId) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid device id");
		}

		FaceEquipment equipment = equipmentService.queryEquipmentById(deviceId);

		return ServiceResultDTO.success(equipment);
	}

	/****
	 * 修改设备登入密码
	 * 
	 * @param deviceId
	 *            设备Id
	 * @param newPassword
	 *            新密码
	 * @param oldPassword
	 *            旧密码
	 * @return
	 */
	@RequestMapping(value = "update/password", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO updateEquipmentPassword(@RequestParam(value = "deviceId") int deviceId, @RequestParam(value = "newPassword") String newPassword, @RequestParam(value = "oldPassword") String oldPassword) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid device id");
		}

		if (StringUtils.isEmpty(newPassword)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid new password");
		}

		if (StringUtils.isEmpty(oldPassword)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid old password");
		}

		int result = equipmentService.updateEquipmentPassword(deviceId, newPassword, oldPassword);

		// 原始密码错误
		if (result == UserConstants.OLD_PASSWORD_ERROR) {
			return new ServiceResultDTO(BaseResultCode.INVALID_OLDPASSWORD, "原始密码错误");
		}

		if (result == UserConstants.FAIL) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "密码修改操作失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 修改设备备注信息
	 * 
	 * @param deviceId
	 *            设备Id
	 * @param remark
	 *            备注信息
	 * @return
	 */
	@RequestMapping(value = "update/remark", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO updateEquipmentRemark(@RequestParam(value = "deviceId") int deviceId, @RequestParam(value = "remark") String remark) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid device id");
		}

		if (StringUtils.isEmpty(remark)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid remark");
		}

		boolean success = equipmentService.updateEquipmentRemarkById(deviceId, remark);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "修改设备备注信息操作失败");
		}

		return ServiceResultDTO.success();
	}
}
