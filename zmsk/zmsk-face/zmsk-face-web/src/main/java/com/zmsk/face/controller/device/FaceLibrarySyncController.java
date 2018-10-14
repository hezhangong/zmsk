package com.zmsk.face.controller.device;

import java.security.SignatureException;
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
	 * @param deviceNumber
	 *            设备号
	 * @return
	 */
	@RequestMapping(value = "unsync", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryUnSyncFaceLibrary(@RequestParam(value = "deviceId", required = false) String deviceId,@RequestParam(value = "deviceNumber", required = false) String deviceNumber) {

		if (!StringUtils.isEmpty(deviceId)) {
			int equipmentId=0;
			try {
				equipmentId = Integer.parseInt(deviceId);
			} catch (Exception e) {
				return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId ");
			}
			
			if (equipmentId <= 0) {
				return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId ");
			}

			List<SyncFaceLibraryDTO> result = faceLibraryService.queryUnSyncFaceLibraryById(equipmentId);
			
			return ServiceResultDTO.success(result);
			
		} else if (!StringUtils.isEmpty(deviceNumber)) {

			List<SyncFaceLibraryDTO> result = faceLibraryService.queryUnSyncFaceLibraryByNumber(deviceNumber);
			
			return ServiceResultDTO.success(result);
			
		} else {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId or deviceNumber");
		}
	}

	/****
	 * 标记人脸库同步记录
	 * 
	 * @param id
	 *            主键Id
	 * @param operation
	 *            操作类型
	 * @return
	 * @throws SignatureException
	 */
	@RequestMapping(value = "flag/synced", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO flagsyncedFaceLibrary(@RequestParam(value = "id") int id, @RequestParam(value = "operation") int operation) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid Id");
		}
		
		if (operation <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid operation");
		}

		boolean success = libraryEquipmentService.flagsyncedFaceLibrary(id, operation);

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
	 * @param errorCode
	 *            错误的code码
	 * @return
	 */
	@RequestMapping(value = "flag/enable", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO flagEnableFaceLibrary(@RequestParam(value = "id") int id, @RequestParam(value = "errorCode") int errorCode) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid Id");
		}

		if (errorCode <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid error code");
		}

		boolean success = libraryEquipmentService.flagEnableFaceLibrary(id, errorCode);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.SYNC_FACE_LIBRARY_FAIL, "人脸库无效标记失败");
		}

		return ServiceResultDTO.success();
	}
}
