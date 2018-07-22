package com.zmsk.face.controller.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceAppInfo;
import com.zmsk.face.service.app.AppInfoService;

@Controller
@RequestMapping(value = "device/app/info/")
public class AppVersionInfoController {

	@Autowired
	private AppInfoService appInfoService;

	/****
	 * 获取最新版本的应用下载地址信息
	 * 
	 * @param version
	 *            当前版本号
	 * @param deviceId
	 *            设备Id
	 * @return
	 */
	@RequestMapping(value = "latest")
	@ResponseBody
	public ServiceResultDTO queryLatestVersionAppInfo(@RequestParam(value = "version") int version, @RequestParam(value = "deviceId", required = false, defaultValue = "0") int deviceId) {

		if (version <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid version");
		}

		FaceAppInfo appInfo = appInfoService.queryLatestVersionAppInfo(version, deviceId);

		if (appInfo == null) {
			return new ServiceResultDTO(BaseResultCode.NOT_CONTENT, "not app version fund");
		}

		return ServiceResultDTO.success(appInfo);
	}

}
