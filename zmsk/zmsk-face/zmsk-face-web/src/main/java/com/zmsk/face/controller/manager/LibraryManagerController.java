package com.zmsk.face.controller.manager;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.library.FaceLibraryService;
import com.zmsk.face.service.library.constants.LibraryFlagConstants;
import com.zmsk.face.service.library.dto.FaceLibraryDTO;

/***
 * 人脸库操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/library/")
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
	 * 根据Id获取人脸库信息
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryLibraryById(@PathVariable(value = "id") int id) {

		FaceLibraryDTO faceLibrary = faceLibraryService.queryLibraryById(id);

		return ServiceResultDTO.success(faceLibrary);
	}

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
	 * @param groupId
	 *            所属分组Id
	 * @param equipmentIds
	 *            设备Id列表
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO updateFaceLibrary(@RequestParam(value = "id") int id, @RequestParam(value = "name",required=false,defaultValue="") String name, @RequestParam(value = "sex",required=false,defaultValue="0") int sex, @RequestParam(value = "idNumber",required=false,defaultValue="") String idNumber, @RequestParam(value = "nation", required = false, defaultValue = "") String nation, @RequestParam(value = "address", defaultValue = "", required = false) String address, @RequestParam(value = "avatar",required=false,defaultValue="") String avatar, @RequestParam(value = "remark", defaultValue = "", required = false) String remark, @RequestParam(value = "flag",required=false,defaultValue="0") int flag, @RequestParam(value = "groupId",required=false,defaultValue="0") int groupId,
			@RequestParam(value = "equipmentIds") String equipmentIds) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid id");
		}

		List<Integer> equipmentIdList = null;

		if (StringUtils.isEmpty(equipmentIds)) {
			equipmentIdList = Collections.emptyList();
		} else {
			equipmentIds = "[" + equipmentIds + "]";
			equipmentIdList = JSONArray.parseArray(equipmentIds, Integer.class);
		}

		boolean success = faceLibraryService.updateFaceLibrary(id, name, sex, idNumber, nation, address, avatar, remark, flag, groupId, equipmentIdList);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.LIBRARY_OPERATION_ERROR, "修改人脸库失败");
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

		List<FaceLibraryDTO> pageInfo = faceLibraryService.queryLibraryList(organizationId, LibraryFlagConstants.WHITE_FLAG, pageSize, pageNum);

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

		List<FaceLibraryDTO> pageInfo = faceLibraryService.queryLibraryList(organizationId, LibraryFlagConstants.BLACK_FLAG, pageSize, pageNum);

		return ServiceResultDTO.success(pageInfo);
	}

	/****
	 * 获取访客名单列表
	 * 
	 * @param organizationId
	 *            组织id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "vistor/list")
	@ResponseBody
	public ServiceResultDTO queryLibraryVistorList(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<FaceLibraryDTO> pageInfo = faceLibraryService.queryLibraryList(organizationId, LibraryFlagConstants.VISTOR_FLAG, pageSize, pageNum);

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

		List<FaceLibraryDTO> pageInfo = faceLibraryService.queryLibraryListByEquipmentId(equipmentId, LibraryFlagConstants.WHITE_FLAG, pageSize, pageNum);

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

		List<FaceLibraryDTO> pageInfo = faceLibraryService.queryLibraryListByEquipmentId(equipmentId, LibraryFlagConstants.BLACK_FLAG, pageSize, pageNum);

		return ServiceResultDTO.success(pageInfo);
	}

	/****
	 * 根据身份证Id获取人脸库信息
	 * 
	 * @param idNumber
	 *            身份证Id
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryLibraryBuIdNumber(@RequestParam(value = "idNumber") String idNumber) {

		if (StringUtils.isEmpty(idNumber)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid idNumber ");
		}

		FaceLibraryDTO library = faceLibraryService.queryLibraryBuIdNumber(idNumber);

		if (library == null) {
			return new ServiceResultDTO(BaseResultCode.NOT_CONTENT, "没有找到数据");
		}

		return ServiceResultDTO.success(library);
	}

}
