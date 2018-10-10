package com.zmsk.face.controller.manager;

import org.apache.commons.lang.StringUtils;
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

/****
 * 组织下设备显示信息操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping(value = "manager/equipment/info/")
public class OrganizationEquipmentInfoController {

	@Autowired
	private EquipmentInfoService equipmentInfoService;

	/*****
	 * 新增设备显示信息
	 * 
	 * @param title
	 *            标题
	 * @param logo
	 *            图标地址
	 * @param organizationId
	 *            组织Id
	 * @param telNumber
	 *            电话号码
	 * @param address
	 *            地址
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO addEquipmentInfo(@RequestParam(value = "title") String title, @RequestParam(value = "logo") String logo, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "telNumber", required = false, defaultValue = "") String telNumber, @RequestParam(value = "address", required = false, defaultValue = "") String address) {

		if (StringUtils.isEmpty(title)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid title ");
		}

		if (StringUtils.isEmpty("logo")) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid logo ");
		}

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId ");
		}

		boolean success = equipmentInfoService.addEquipmentInfo(title, logo, telNumber, address, organizationId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "add equipment info error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 根据组织Id获取设备显示信息值
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentInfoByOrganizationId(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId ");
		}

		FaceEquipmentInfo result = equipmentInfoService.queryEquipmentInfoByOrganizationId(organizationId);

		return ServiceResultDTO.success(result);
	}

	/****
	 * 修改组织设备显示信息
	 * 
	 * @param id
	 *            主键Id
	 * @param title
	 *            标题
	 * @param logo
	 *            图标地址
	 * @param telNumber
	 *            电话号码
	 * @param address
	 *            地址
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO updateEquipmentInfo(@RequestParam(value = "id") int id, @RequestParam(value = "title", required = false, defaultValue = "") String title, @RequestParam(value = "logo", required = false, defaultValue = "") String logo, @RequestParam(value = "telNumber", required = false, defaultValue = "") String telNumber, @RequestParam(value = "address", required = false, defaultValue = "") String address) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid id ");
		}

		boolean success = equipmentInfoService.updateEquipmentInfo(id, title, logo, telNumber, address);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.EQUIPMENT_OPERATION_ERROR, "update equipment info error");
		}

		return ServiceResultDTO.success();
	}

}
