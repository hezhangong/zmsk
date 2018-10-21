package com.zmsk.face.service.statistic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.common.utils.DateUtils;
import com.zmsk.face.dto.authentic.EquipmentDetailAuthenticationDTO;
import com.zmsk.face.mapper.custom.authentication.CustomAuthenticationInfoMapper;
import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.service.equipment.EquipmentService;
import com.zmsk.face.service.statistic.StatisticService;

/****
 * 统计操作服务接口实现
 * 
 * @author warrior
 *
 */
@Transactional
@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	private CustomAuthenticationInfoMapper customAuthenticationMapper;

	@Autowired
	private EquipmentService equiomentService;

	@Override
	public Map<String, Long> todayAuthenticationNumber(int organizationId) {

		long startTime = DateUtils.getCurrentDateTime();

		long endTime = DateUtils.getCurrentNextDaysDateTime(1);

		long successCount = customAuthenticationMapper.countAuthenticationSuccessResult(organizationId, startTime,
				endTime);

		long failCount = customAuthenticationMapper.countAuthenticationFailResult(organizationId, startTime, endTime);

		Map<String, Long> result = new ConcurrentHashMap<>(2);

		result.put("successCount", successCount);

		result.put("failCount", failCount);

		return result;
	}

	@Override
	public Map<String, Long> yesterdayAuthenticationNumber(int organizationId) {

		long startTime = DateUtils.getBeforeDaysDateTime(1);

		long endTime = DateUtils.getCurrentDateTime();

		long successCount = customAuthenticationMapper.countAuthenticationSuccessResult(organizationId, startTime,
				endTime);

		long failCount = customAuthenticationMapper.countAuthenticationFailResult(organizationId, startTime, endTime);

		Map<String, Long> result = new ConcurrentHashMap<>(2);

		result.put("successCount", successCount);

		result.put("failCount", failCount);

		return result;
	}

	@Override
	public List<EquipmentDetailAuthenticationDTO> queryEquipmentDetailAuthenticationStatistic(int organizationId) {

		long startTime = DateUtils.getCurrentDateTime();

		long endTime = DateUtils.getCurrentNextDaysDateTime(1);

		List<FaceEquipment> equipmentList = equiomentService.queryEquipmentList(organizationId, null);

		if (equipmentList == null || equipmentList.size() == 0) {
			return Collections.emptyList();
		}

		List<EquipmentDetailAuthenticationDTO> successList = customAuthenticationMapper
				.countEquipmentAuthenticationSuccessResult(organizationId, startTime, endTime);

		List<EquipmentDetailAuthenticationDTO> failList = customAuthenticationMapper
				.countEquipmentAuthenticationFailResult(organizationId, startTime, endTime);

		List<EquipmentDetailAuthenticationDTO> resultList = new ArrayList<>(equipmentList.size());

		EquipmentDetailAuthenticationDTO equipmentDetailAuthentication = null;

		for (FaceEquipment equipment : equipmentList) {

			int equipmentId = equipment.getId();

			String remark = equipment.getRemark();

			int status = equipment.getStatus();

			equipmentDetailAuthentication = new EquipmentDetailAuthenticationDTO(equipmentId, remark, status);

			for (EquipmentDetailAuthenticationDTO equipmentSuccess : successList) {

				int successId = equipmentSuccess.getEquipmentId();

				if (equipmentId == successId) {
					equipmentDetailAuthentication.setTodaySuccessCount(equipmentSuccess.getTodaySuccessCount());
					break;
				}
			}

			for (EquipmentDetailAuthenticationDTO equipmentFail : failList) {

				int failId = equipmentFail.getEquipmentId();

				if (equipmentId == failId) {
					equipmentDetailAuthentication.setTodayFailCount(equipmentFail.getTodayFailCount());
					break;
				}
			}

			resultList.add(equipmentDetailAuthentication);

		}

		return resultList;
	}

}
