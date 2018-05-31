package com.zmsk.face.mapper.custom.organization;

import org.apache.ibatis.annotations.Param;

public interface CustomerOrganizationMapper {

	int queryOrganizationIdByUsername(@Param("userName") String userName);
}
