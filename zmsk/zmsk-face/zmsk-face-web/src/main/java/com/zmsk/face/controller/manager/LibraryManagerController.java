package com.zmsk.face.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceLibrary;
import com.zmsk.face.service.library.FaceLibraryService;
import com.zmsk.face.service.library.constants.LibraryFlagConstants;

/***
 * 人脸库操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping(value = "manager/library/")
public class LibraryManagerController {

	@Autowired
	private FaceLibraryService faceLibraryService;

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
	 * @param flag
	 *            黑白名单标识
	 * @param organizationId
	 *            组织Id
	 * @param groupId
	 *            所属分组Id
	 * @param equipmentId
	 *            设备Id列表
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO addFaceLibrary(@RequestParam(value = "name") String name, @RequestParam(value = "sex") int sex, @RequestParam(value = "idNumber") String idNumber, @RequestParam(value = "nation", required = false, defaultValue = "") String nation, @RequestParam(value = "address", defaultValue = "", required = false) String address, @RequestParam(value = "avatar") String avatar, @RequestParam(value = "remark", defaultValue = "", required = false) String remark, @RequestParam(value = "flag") int flag, @RequestParam(value = "organizationId") int organizationId,
			@RequestParam(value = "groupId") int groupId, @RequestParam(value = "equipmentId") String equipmentId) {

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

		if (flag <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid flag");
		}

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organizationId");
		}

		if (groupId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid groupId");
		}

		if (StringUtils.isEmpty(equipmentId)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipmentId");
		}

		List<Integer> equipmentIds = JSON.parseArray("[" + equipmentId + "]", Integer.class);

		boolean success = faceLibraryService.addFaceLibrary(name, sex, idNumber, nation, address, avatar, remark, flag, organizationId, groupId, equipmentIds);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.LIBRARY_OPERATION_ERROR, "新增人脸库失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 查看白名单列表
	 * 
	 * @param organizationId
	 *            组织id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "white/list")
	@ResponseBody
	public ServiceResultDTO queryLibraryWhiteList(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceLibrary> pageInfo = faceLibraryService.queryLibraryList(organizationId, LibraryFlagConstants.WHITE_FLAG, pageSize, pageNum);

		return ServiceResultDTO.success(pageInfo);
	}

	/****
	 * 查看黑名单列表
	 * 
	 * @param organizationId
	 *            组织id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "black/list")
	@ResponseBody
	public ServiceResultDTO queryLibraryBlackList(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceLibrary> pageInfo = faceLibraryService.queryLibraryList(organizationId, LibraryFlagConstants.BLACK_FLAG, pageSize, pageNum);

		return ServiceResultDTO.success(pageInfo);
	}

	/***
	 * 查询设备对应的白名单列表
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "equipment/white/list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryLibraryWhiteListByEquipmentId(@RequestParam(value = "equipmentId") int equipmentId, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (equipmentId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipment id");
		}

		List<FaceLibrary> pageInfo = faceLibraryService.queryLibraryListByEquipmentId(equipmentId, LibraryFlagConstants.WHITE_FLAG, pageSize, pageNum);

		return ServiceResultDTO.success(pageInfo);
	}

	/***
	 * 查询设备对应的黑名单列表
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "equipment/black/list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryLibraryBlackListByEquipmentId(@RequestParam(value = "equipmentId") int equipmentId, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (equipmentId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid equipment id");
		}

		List<FaceLibrary> pageInfo = faceLibraryService.queryLibraryListByEquipmentId(equipmentId, LibraryFlagConstants.BLACK_FLAG, pageSize, pageNum);

		return ServiceResultDTO.success(pageInfo);
	}
}
