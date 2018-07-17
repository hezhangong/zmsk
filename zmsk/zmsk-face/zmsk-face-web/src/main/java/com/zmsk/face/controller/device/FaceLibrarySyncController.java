package com.zmsk.face.controller.device;

import java.security.SignatureException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.common.utils.RsaSignatureUtils;
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
	 * @throws SignatureException
	 */
	@RequestMapping(value = "unsync", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO queryUnSyncFaceLibrary(@RequestParam(value = "deviceId") int deviceId, @RequestBody String sign) throws SignatureException {

		if (deviceId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid deviceId ");
		}

		if (StringUtils.isEmpty(sign)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_SIGN, "Invalid sign ");
		}

		boolean signSuccess = RsaSignatureUtils.rsaCheck(deviceId, sign);

		// 签名不对
		if (!signSuccess) {
			return new ServiceResultDTO(BaseResultCode.INVALID_SIGN, "Invalid sign ");
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
	public ServiceResultDTO flagsyncedFaceLibrary(@PathVariable(value = "id") int id, @RequestBody String sign) throws SignatureException {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "invalid Id");
		}

		if (StringUtils.isEmpty(sign)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_SIGN, "Invalid sign ");
		}

		boolean signSuccess = RsaSignatureUtils.rsaCheck(id, sign);

		// 签名不对
		if (!signSuccess) {
			return new ServiceResultDTO(BaseResultCode.INVALID_SIGN, "Invalid sign ");
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

	public static void main(String[] args) throws SignatureException {
		String sign = "QUAZVBhDykCBmy6ovqRoPmmUljyRR2x2RB2kGlEBxeV/gGb2etZYFj1vYU/wa8EkpdkDNFHynqtfP8J1keyekIRnySV2Xr+4U8v/7OKipT/1w+d2rDcH3Az40JBLoefHEJrv+ZolIyJiA1/By84rntNBkFgGboEryGhn1OGKkfU=";
		boolean signSuccess = RsaSignatureUtils.rsaCheck("zd10013", sign);
		System.out.println(signSuccess);
	}
}
