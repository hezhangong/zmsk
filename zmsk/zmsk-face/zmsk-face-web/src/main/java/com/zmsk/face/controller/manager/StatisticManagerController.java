package com.zmsk.face.controller.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.dto.authentic.EquipmentDetailAuthenticationDTO;
import com.zmsk.face.service.statistic.StatisticService;

@Controller
@RequestMapping("manager/statistic/")
public class StatisticManagerController {

	@Autowired
	private StatisticService statisticService;

	/****
	 * 统计组织下所有今日认证数据
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "today/authentication/num", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO todayAuthenticationNumber(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		Map<String, Long> result = statisticService.todayAuthenticationNumber(organizationId);

		return ServiceResultDTO.success(result);
	}

	/****
	 * 统计组织下所有昨日认证数据
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "yesterday/authentication/num", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO yesterdayAuthenticationNumber(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		Map<String, Long> result = statisticService.yesterdayAuthenticationNumber(organizationId);

		return ServiceResultDTO.success(result);
	}

	/****
	 * 统计组织下所有设备今天的认证记录数
	 * 
	 * @param organizationId
	 * @return
	 */
	@RequestMapping(value = "equipment/authentication/num", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryEquipmentDetailAuthenticationStatistic(@RequestParam(value = "organizationId") int organizationId) {

		if (organizationId <= 0) {
			return new ServiceResultDTO(BaseResultCode.INVALID_PARAM, "Invalid organization id");
		}

		List<EquipmentDetailAuthenticationDTO> list = statisticService.queryEquipmentDetailAuthenticationStatistic(organizationId);

		return ServiceResultDTO.success(list);
	}

}
