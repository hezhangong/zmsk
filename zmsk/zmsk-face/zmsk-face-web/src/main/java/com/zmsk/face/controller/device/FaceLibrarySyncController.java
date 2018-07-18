package com.zmsk.face.controller.device;

import java.security.SignatureException;
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
import com.zmsk.face.dto.library.SyncFaceLibraryDTO;
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
	 * @param sign
	 *            签名内容
	 * @return
	 */
	@RequestMapping(value = "unsync", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO queryUnSyncFaceLibrary(@RequestParam(value = "deviceId") int deviceId) {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId ");
		}

		List<SyncFaceLibraryDTO> result = faceLibraryService.queryUnSyncFaceLibrary(deviceId);

		return ServiceResultDTO.success(result);
	}

	/****
	 * 标记人脸库同步记录
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 * @throws SignatureException
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

	/****
	 * 标记无效人脸库
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	@RequestMapping(value = "flag/enable/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO flagEnableFaceLibrary(@PathVariable(value = "id") int id) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid Id");
		}

		boolean success = libraryEquipmentService.flagEnableFaceLibrary(id);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.SYNC_FACE_LIBRARY_FAIL, "人脸库无效标记失败");
		}

		return ServiceResultDTO.success();
	}
}
