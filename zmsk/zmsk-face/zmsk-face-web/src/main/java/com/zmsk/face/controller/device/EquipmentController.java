package com.zmsk.face.controller.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.equipment.EquipmentService;

/****
 * 设备操作controller
 * 
 * @author warrior
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
}
