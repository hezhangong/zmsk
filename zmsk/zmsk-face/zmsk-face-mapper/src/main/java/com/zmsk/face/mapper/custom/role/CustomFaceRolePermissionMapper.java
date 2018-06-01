package com.zmsk.face.mapper.custom.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomFaceRolePermissionMapper {

	public List<Integer> queryRolePermissionByRoleId(@Param("roleId") int roleId);
}
