package com.zmsk.face.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.equipment.EquipmentListService;

@Controller
@RequestMapping("device/equipment/list/")
public class EquipmentListController {

	@Autowired
	private EquipmentListService equipmentListService;

	/****
	 * 添加设备绑定信息
	 * 
	 * @param deviceNumber
	 *            设备号
	 * @param deviceIP
	 *            设备IP
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO addEquipmentList(@RequestParam(value = "deviceNumber") String deviceNumber, @RequestParam(value = "deviceIP") String deviceIP) {

		if (StringUtils.isEmpty(deviceNumber)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid deviceNumber");
		}
		
		if (StringUtils.isEmpty(deviceIP)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid deviceIP");
		}
		
		boolean success = equipmentListService.createEquipmentList(deviceNumber, deviceIP);
		
		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "create equipment_list record fail");
		}

		return ServiceResultDTO.success();
	}
}
