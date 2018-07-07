package com.zmsk.face.mapper.custom.authentication;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.dto.authentic.EquipmentDetailAuthenticationDTO;

public interface CustomAuthenticationInfoMapper {

	/****
	 * 获取组织下认证成功的记录数
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param startTime
	 *            开始时间戳
	 * @param endTime
	 *            结束时间戳
	 * @return
	 */
	long countAuthenticationSuccessResult(@Param("organizationId") int organizationId, @Param("startTime") long startTime, @Param("endTime") long endTime);

	/****
	 * 获取组织下认证失败的记录数
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param startTime
	 *            开始时间戳
	 * @param endTime
	 *            结束时间戳
	 * @return
	 */
	long countAuthenticationFailResult(@Param("organizationId") int organizationId, @Param("startTime") long startTime, @Param("endTime") long endTime);

	/****
	 * 获取组织下所有设备认证成功的记录数
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param startTime
	 *            开始时间戳
	 * @param endTime
	 *            结束时间戳
	 * @return
	 */
	List<EquipmentDetailAuthenticationDTO> countEquipmentAuthenticationSuccessResult(@Param("organizationId") int organizationId, @Param("startTime") long startTime, @Param("endTime") long endTime);

	/****
	 * 获取组织下所有设备认证成功的记录数
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param startTime
	 *            开始时间戳
	 * @param endTime
	 *            结束时间戳
	 * @return
	 */
	List<EquipmentDetailAuthenticationDTO> countEquipmentAuthenticationFailResult(@Param("organizationId") int organizationId, @Param("startTime") long startTime, @Param("endTime") long endTime);

}