package com.zmsk.face.controller.device;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.common.exception.constants.ExceptionCodeConstants;
import com.zmsk.face.service.equipment.EquipmentService;
import com.zmsk.face.service.equipment.dto.DeviceLoginResultDTO;

/*****
 * 设备登入操作
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("device/login")
public class DeviceLoginController {

	@Autowired
	private EquipmentService equipmentService;

	/****
	 * 设备登入
	 * 
	 * @param deviceNumber
	 *            设备登入账号
	 * @param devicePassword
	 *            设备登入密码
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO login(@RequestParam(value = "deviceNumber") String deviceNumber, @RequestParam(value = "devicePassword") String devicePassword) {

		if (StringUtils.isEmpty(deviceNumber)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "无效的账号");
		}

		if (StringUtils.isEmpty(devicePassword)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "无效的密码");
		}

		DeviceLoginResultDTO deviceLoginResult = equipmentService.deviceLogin(deviceNumber, devicePassword);

		if (deviceLoginResult == null) {
			return new ServiceResultDTO(ExceptionCodeConstants.UNAUTH_CODE, "无效的账号密码或设备未激活");
		}

		return ServiceResultDTO.success(deviceLoginResult);
	}

}