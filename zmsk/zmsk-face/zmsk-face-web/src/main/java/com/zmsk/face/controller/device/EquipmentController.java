package com.zmsk.face.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.equipment.EquipmentService;

/****
 * 设备操作controller
 * 
 */
@Controller
@RequestMapping("device/equipment/")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	
	/****
	 * 校验设备有效性
	 * 
	 * @param macId
	 *            设备物理Id
	 * @param equipmentNumber
	 *            登入账号
	 * @param version
	 *            版本号
	 * @return
	 */
	@RequestMapping(value = "check", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO checkEquipment(@RequestParam(value = "macId") String macId, @RequestParam(value = "equipmentNumber") String equipmentNumber, @RequestParam(value = "version") String version) {
		
		boolean result = equipmentService.checkEquipment(macId, equipmentNumber, version);

		return ServiceResultDTO.success(result);
	}
}
