package com.zmsk.face.controller.manager;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.common.exception.ExprotExcelException;
import com.zmsk.common.utils.DataTablePageUtil;
import com.zmsk.face.dto.authentic.AuthenticationInfoDTO;
import com.zmsk.face.service.authentication.AuthenticationInfoService;

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
	 * 查询认证信息记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param start
	 *            开始的数据行数
	 * @param length
	 *            每页的数据数
	 * @param draw
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryAuthenticationInfo(@RequestParam(value = "search", required = false, defaultValue = "") String search, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "length", defaultValue = "10", required = false) int length, @RequestParam(value = "start", required = false, defaultValue = "1") int start, @RequestParam(value = "draw") int draw) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		DataTablePageUtil<AuthenticationInfoDTO> list = authenticationInfoService.queryAuthenticationInfo(search, organizationId, start, length, draw);

		return ServiceResultDTO.success(list);
	}

	/****
	 * 获取组织对应警告认证记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param start
	 *            开始的数据行数
	 * @param length
	 *            每页的数据数数
	 * @param draw
	 * @return
	 */
	@RequestMapping(value = "warn/list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryWarnAuthenticationInfo(@RequestParam(value = "search", required = false, defaultValue = "") String search, @RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "length", defaultValue = "10", required = false) int length, @RequestParam(value = "start", required = false, defaultValue = "1") int start, @RequestParam(value = "draw") int draw) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		DataTablePageUtil<AuthenticationInfoDTO> list = authenticationInfoService.queryWarnAuthenticationInfo(search, organizationId, start, length, draw);

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

		AuthenticationInfoDTO authenticationInfo = authenticationInfoService.queryAuthenticationInfoById(id);

		return ServiceResultDTO.success(authenticationInfo);
	}

	/****
	 * 导出认证记录
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param dateStr
	 *            日期字符串
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@ResponseBody
	private ServiceResultDTO exportAuthenticationInfo(@RequestParam(value = "organizationId") int organizationId, @RequestParam(value = "dateStr") String dateStr, HttpServletResponse response) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		if (StringUtils.isEmpty(dateStr)) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid date str");
		}

		Workbook workbook = authenticationInfoService.exportAuthenticationInfo(organizationId, dateStr);

		if (workbook == null) {
			throw new ExprotExcelException("未查询到导出数据");
		}

		String filename = dateStr + "认证记录";

		// 指定下载的文件名--设置响应头
		response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 写出数据输出流到页面
		try {
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			workbook.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
			output.close();
		} catch (IOException e) {
			throw new ExprotExcelException(e.getMessage(), e);
		}

		return ServiceResultDTO.success();
	}
}
