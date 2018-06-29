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
import com.zmsk.face.service.library.VisistorLibraryService;
import com.zmsk.face.service.library.dto.FaceVisitorLibraryDTO;

/****
 * 访客人脸库操作controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/visistor/library/")
public class VisitorLibraryController {

	@Autowired
	private VisistorLibraryService vistorLibraryService;

	/***
	 * 新增人脸库
	 * 
	 * @param name
	 *            姓名
	 * @param sex
	 *            性别
	 * @param idNumber
	 *            身份证号
	 * @param nation
	 *            民族
	 * @param address
	 *            地址
	 * @param avatar
	 *            头像
	 * @param remark
	 *            备注
	 * @param organizationId
	 *            组织Id
	 * @param groupId
	 *            所属分组Id
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO addVisitorFaceLibrary(@RequestParam(value = "name") String name, @RequestParam(value = "sex") int sex, @RequestParam(value = "idNumber") String idNumber, @RequestParam(value = "nation", required = false, defaultValue = "") String nation, @RequestParam(value = "address", defaultValue = "", required = false) String address, @RequestParam(value = "avatar") String avatar, @RequestParam(value = "remark", defaultValue = "", required = false) String remark, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "groupId") int groupId) {

		if (StringUtils.isEmpty(name)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid name");
		}

		if (sex <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid sex");
		}

		if (StringUtils.isEmpty(idNumber)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid idNumber");
		}

		if (StringUtils.isEmpty(avatar)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid avatar");
		}

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId");
		}

		if (groupId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid groupId");
		}

		boolean success = vistorLibraryService.addFaceLibrary(name, sex, idNumber, nation, address, avatar, remark, organizationId, groupId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.VISITOR_LIBRARY_OPERATION_ERROR, "新增访客人脸库失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 获取组织下访客人脸库
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryVisitorLibraryByOrganizationId(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId");
		}

		List<FaceVisitorLibraryDTO> list = vistorLibraryService.queryVisitorLibraryByOrganizationId(organizationId);

		return ServiceResultDTO.success(list);
	}

	/****
	 * 获取Id对应的访客人脸库信息
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryVisitorLibrary(@PathVariable(value = "id") int id) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid id");
		}

		FaceVisitorLibraryDTO visitoryLibrary = vistorLibraryService.queryVisitorLibrary(id);

		return ServiceResultDTO.success(visitoryLibrary);
	}

}
