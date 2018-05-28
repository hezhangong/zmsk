package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceRolePermission;
import com.zmsk.face.pojo.FaceRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceRolePermissionMapper {
    int countByExample(FaceRolePermissionExample example);

    int deleteByExample(FaceRolePermissionExample example);

    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(FaceRolePermission record);

    int insertSelective(FaceRolePermission record);

    List<FaceRolePermission> selectByExample(FaceRolePermissionExample example);

    FaceRolePermission selectByPrimaryKey(Integer rolePermissionId);

    int updateByExampleSelective(@Param("record") FaceRolePermission record, @Param("example") FaceRolePermissionExample example);

    int updateByExample(@Param("record") FaceRolePermission record, @Param("example") FaceRolePermissionExample example);

    int updateByPrimaryKeySelective(FaceRolePermission record);

    int updateByPrimaryKey(FaceRolePermission record);
}