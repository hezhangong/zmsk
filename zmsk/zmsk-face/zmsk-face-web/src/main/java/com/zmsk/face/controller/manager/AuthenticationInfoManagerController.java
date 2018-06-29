package com.zmsk.face.controller.manager;

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
import com.zmsk.face.pojo.FaceAuthenticationInfo;
import com.zmsk.face.service.authentication.AuthenticationInfoService;
import com.zmsk.face.service.authentication.dto.AuthenticationInfoDTO;

/****
 * 认证记录后台操作Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("manager/authertication/record/")
public class AuthenticationInfoManagerController {

	@Autowired
	private AuthenticationInfoService authenticationInfoService;

	/****
	 * 获取组织对应的认证记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryAuthenticationInfo(@RequestParam(value = "search", required = false, defaultValue = "") String search, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<AuthenticationInfoDTO> list = authenticationInfoService.queryAuthenticationInfo(search, organizationId, pageSize, pageNum);

		return ServiceResultDTO.success(list);
	}

	/****
	 * 获取组织对应警告认证记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	@RequestMapping(value = "warn/list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryWarnAuthenticationInfo(@RequestParam(value = "search", required = false, defaultValue = "") String search, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<AuthenticationInfoDTO> list = authenticationInfoService.queryWarnAuthenticationInfo(search, organizationId, pageSize, pageNum);

		return ServiceResultDTO.success(list);
	}

	/****
	 * 根据Id获取认证信息
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryAuthenticationInfoById(@PathVariable(value = "id") int id) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid  id");
		}

		FaceAuthenticationInfo authenticationInfo = authenticationInfoService.queryAuthenticationInfoById(id);

		return ServiceResultDTO.success(authenticationInfo);
	}
}
