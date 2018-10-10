package com.zmsk.face.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceEquipmentInfo;
import com.zmsk.face.service.equipment.EquipmentInfoService;

@Controller
@RequestMapping("device/equipment/info/")
public class EquipmentInfoController {

	@Autowired
	private EquipmentInfoService equipmentInfoService;

	/****
	 * 获取设备显示同步信息
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	@RequestMapping(value = "sync", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentInfo(@RequestParam(value = "deviceId") int deviceId) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId");
		}

		FaceEquipmentInfo faceEquipmetInfo = equipmentInfoService.queryEquipmentInfoByDeviceId(deviceId);

		if (faceEquipmetInfo == null) {
			return new ServiceResultDTO(BaseResultCode.NOT_CONTENT, "not fund sync equipment info");
		}

		return ServiceResultDTO.success(faceEquipmetInfo);
	}
}
