package com.zmsk.face.mapper.custom.role;

import java.util.List;

import com.zmsk.face.pojo.FaceRole;

public interface CustomFaceRoleMapper {

	List<FaceRole> queryRoleListByUserId(int userId);
}
