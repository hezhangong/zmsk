package com.zmsk.face.mapper.custom.organization;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomerOrganizationMapper {

	int queryOrganizationIdByUsername(@Param("userName") String userName);

	/****
	 * 查询组织下用户Id列表
	 * 
	 * @param organizationId
	 *            组织id
	 * 
	 * @return
	 */
	List<Integer> queryUserIdsByOrganizationId(@Param("organizationId") int organizationId);
}
