package com.zmsk.face.mapper.custom.permission;

import java.util.List;

import com.zmsk.face.pojo.FacePermission;

public interface CustomFacePermissionMapper {

	List<FacePermission> queryPermissionListByUserId(int userId);

	List<FacePermission> queryMenuListByUserId(int userId);
}
