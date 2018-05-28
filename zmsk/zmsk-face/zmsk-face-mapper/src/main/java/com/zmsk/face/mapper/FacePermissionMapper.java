package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FacePermission;
import com.zmsk.face.pojo.FacePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FacePermissionMapper {
    int countByExample(FacePermissionExample example);

    int deleteByExample(FacePermissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(FacePermission record);

    int insertSelective(FacePermission record);

    List<FacePermission> selectByExample(FacePermissionExample example);

    FacePermission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") FacePermission record, @Param("example") FacePermissionExample example);

    int updateByExample(@Param("record") FacePermission record, @Param("example") FacePermissionExample example);

    int updateByPrimaryKeySelective(FacePermission record);

    int updateByPrimaryKey(FacePermission record);
}