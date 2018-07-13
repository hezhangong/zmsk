package com.zmsk.face.mapper.custom.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.pojo.FacePermission;

public interface CustomFacePermissionMapper {

	List<FacePermission> queryPermissionListByUserId(@Param("userId") int userId);

	List<FacePermission> queryMenuListByUserId(@Param("userId") int userId);
}
