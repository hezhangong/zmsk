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
import com.zmsk.face.dto.equipment.EquipmentRemarkDTO;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.equipment.EquipmentService;
import com.zmsk.face.service.library.FaceLibraryService;
import com.zmsk.face.service.library.dto.FaceLibraryDTO;
import com.zmsk.face.service.user.constants.UserConstants;

/****
 * 设备操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/equipment/")
public class EquipmentManagerController {

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private FaceLibraryService faceLibraryService;
	
	/****
	 * 校验设备有效性
	 * 
	 * @param macId
	 *            设备物理Id
	 * @param equipmentNumber
	 *            登入账号
	 * @return
	 */
	@RequestMapping(value = "check", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO checkEquipment(@RequestParam(value = "macId") String macId, @RequestParam(value = "equipmentNumber") String equipmentNumber) {
		
		if (StringUtils.isEmpty(macId)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid macId");
		}

		if (StringUtils.isEmpty(equipmentNumber)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipmentNumber");
		}

		List<FaceEquipment> result = equipmentService.checkEquipment(macId, equipmentNumber);

		if (result != null && result.size() > 0) {
			return ServiceResultDTO.success(true);
		} else {
			return ServiceResultDTO.success(false);
		}
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

	/***
	 * 获取组织下设备备注信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "remark", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentRemarkByorganizationId(@RequestParam(value = "organizationId") int organizationId) {
		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}
		List<EquipmentRemarkDTO> list = equipmentService.queryEquipmentRemarkByorganizationId(organizationId);

		return ServiceResultDTO.success(list);
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

	/****
	 * 获取设备的人脸库信息
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "face/library", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentLibrary(@RequestParam(value = "equipmentId") int equipmentId, @RequestParam(value = "organizationId") int organizationId) {

		if (equipmentId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipment id");
		}

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceLibraryDTO> list = faceLibraryService.queryEquipmentLibrary(organizationId, equipmentId);

		return ServiceResultDTO.success(list);
	}
}
