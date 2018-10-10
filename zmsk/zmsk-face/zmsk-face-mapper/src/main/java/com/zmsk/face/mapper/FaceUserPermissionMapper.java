package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceUserPermission;
import com.zmsk.face.pojo.FaceUserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceUserPermissionMapper {
    int countByExample(FaceUserPermissionExample example);

    int deleteByExample(FaceUserPermissionExample example);

    int deleteByPrimaryKey(Integer userPermissionId);

    int insert(FaceUserPermission record);

    int insertSelective(FaceUserPermission record);

    List<FaceUserPermission> selectByExample(FaceUserPermissionExample example);

    FaceUserPermission selectByPrimaryKey(Integer userPermissionId);

    int updateByExampleSelective(@Param("record") FaceUserPermission record, @Param("example") FaceUserPermissionExample example);

    int updateByExample(@Param("record") FaceUserPermission record, @Param("example") FaceUserPermissionExample example);

    int updateByPrimaryKeySelective(FaceUserPermission record);

    int updateByPrimaryKey(FaceUserPermission record);
}