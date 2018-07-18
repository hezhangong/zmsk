package com.zmsk.face.controller.device;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.authentication.AuthenticationInfoService;

/****
 * 认证记录信息设备操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("device/authentication/record/")
public class AuthenticationInfoDeviceController {

	@Autowired
	private AuthenticationInfoService authenticationServiceInfo;

	/****
	 * 添加认证记录
	 * 
	 * @param name
	 *            名称
	 * @param idNumber
	 *            身份证号
	 * @param nation
	 *            民族
	 * @param address
	 *            身份证地址
	 * @param avatar
	 *            头像
	 * @param sex
	 *            性别
	 * @param type
	 *            类型
	 * @param result
	 *            结果
	 * @param deviceNumber
	 *            设备登入账号
	 * @param groupId
	 *            所属分组Id
	 * @param authTimeStamp
	 *            认证时间戳
	 * @param idcardImage
	 *            身份证图片
	 * @param idcardInfo
	 *            身份证基本信息
	 * @param similarDegree
	 *            相似度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO addAuthenticationRecord(@RequestParam(value = "name") String name, @RequestParam(value = "idNumber", required = false, defaultValue = "") String idNumber, @RequestParam(value = "nation", defaultValue = "", required = false) String nation, @RequestParam(value = "address", required = false, defaultValue = "") String address, @RequestParam(value = "avatar", required = false, defaultValue = "") String avatar, @RequestParam(value = "sex") int sex, @RequestParam(value = "type") int type, @RequestParam(value = "result") int result,
			@RequestParam(value = "deviceNumber") String deviceNumber, @RequestParam(value = "groupId", defaultValue = "0", required = false) int groupId, @RequestParam(value = "authTimeStamp") long authTimeStamp, @RequestParam(value = "idcardImage", defaultValue = "", required = false) String idcardImage, @RequestParam(value = "idcardInfo", required = false, defaultValue = "") String idcardInfo, @RequestParam(value = "similarDegree", defaultValue = "", required = false) String similarDegree) throws UnsupportedEncodingException, SignatureException {

		if (StringUtils.isEmpty(name)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid name");
		}

		if (StringUtils.isEmpty(deviceNumber)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid deviceNumber");
		}

		if (sex <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid sex");
		}

		if (type <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid type");
		}

		if (result < 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid result");
		}

		if (groupId < 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, " invalid groupId");
		}

		if (StringUtils.isEmpty(avatar)) {
			avatar = URLDecoder.decode(avatar, "UTF-8");
		}

		boolean success = authenticationServiceInfo.addAuthenticationInfo(name, idNumber, nation, address, avatar, sex, type, result, deviceNumber, groupId, authTimeStamp, idcardImage, idcardInfo, similarDegree);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.AUTHENTICATION_INFO_OPERATION_ERROR, "create authentication record fail");
		}

		return ServiceResultDTO.success();
	}
}
