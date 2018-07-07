package com.zmsk.face.controller.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.service.authentication.AuthenticationInfoService;
import com.zmsk.face.service.authentication.dto.VisistorInfoDTO;

/****
 * 访客操作controller
 * 
 * @author warrior
 *
 */
@RequestMapping("manager/visitor/")
@Controller
public class VisitorController {

	@Autowired
	private AuthenticationInfoService authenticationInfoService;

	/****
	 * 登记成访客
	 * 
	 * @param id
	 *            主键Id
	 * @param remark
	 *            备注信息
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO registerVisitor(@RequestParam(value = "id") int id, @RequestParam(value = "remark") String remark) {

		if (id <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid  id");
		}

		if (StringUtils.isEmpty(remark)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid  remark");
		}

		boolean success = authenticationInfoService.registerVisitor(id, remark);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.VISITOR_LIBRARY_OPERATION_ERROR, "登记访客操作失败");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 获取访客列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryVisitorByOrganizationId(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<VisistorInfoDTO> list = authenticationInfoService.queryVisitorByOrganizationId(organizationId);

		return ServiceResultDTO.success(list);
	}
}
