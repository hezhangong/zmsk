package com.zmsk.face.controller.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceLibrary;
import com.zmsk.face.service.library.FaceLibraryEquipmentService;
import com.zmsk.face.service.library.FaceLibraryService;

@Controller
@RequestMapping("device/face/library/")
public class FaceLibrarySyncController {

	@Autowired
	private FaceLibraryService faceLibraryService;

	@Autowired
	private FaceLibraryEquipmentService libraryEquipmentService;

	/****
	 * 获取设备对应未同步的人脸库
	 * 
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	@RequestMapping(value = "unsync", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryUnSyncFaceLibrary(@RequestParam(value = "deviceId") int deviceId) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId ");
		}

		List<FaceLibrary> result = faceLibraryService.queryUnSyncFaceLibrary(deviceId);

		return ServiceResultDTO.success(result);
	}

	/****
	 * 标记人脸库同步记录
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	@RequestMapping(value = "flag/synced/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO flagsyncedFaceLibrary(@PathVariable(value = "id") int id) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid Id");
		}

		boolean success = libraryEquipmentService.flagsyncedFaceLibrary(id);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.SYNC_FACE_LIBRARY_FAIL, "人脸库同步标记失败");
		}

		return ServiceResultDTO.success();
	}

}