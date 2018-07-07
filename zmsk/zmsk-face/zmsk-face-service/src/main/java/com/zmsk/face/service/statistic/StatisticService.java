package com.zmsk.face.service.statistic;

import java.util.List;
import java.util.Map;

import com.zmsk.face.dto.authentic.EquipmentDetailAuthenticationDTO;

/****
 * 统计操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface StatisticService {

	/****
	 * 统计组织下所有设置今日认证数据
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	Map<String, Long> todayAuthenticationNumber(int organizationId);

	/****
	 * 统计组织下所有设置昨日认证数据
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	Map<String, Long> yesterdayAuthenticationNumber(int organizationId);

	/****
	 * 统计组织下所有设备今天和昨天的认证记录数
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<EquipmentDetailAuthenticationDTO> queryEquipmentDetailAuthenticationStatistic(int organizationId);
}
