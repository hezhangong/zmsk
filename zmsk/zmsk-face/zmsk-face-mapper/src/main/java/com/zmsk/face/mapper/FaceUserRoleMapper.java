package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceUserRole;
import com.zmsk.face.pojo.FaceUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceUserRoleMapper {
    int countByExample(FaceUserRoleExample example);

    int deleteByExample(FaceUserRoleExample example);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(FaceUserRole record);

    int insertSelective(FaceUserRole record);

    List<FaceUserRole> selectByExample(FaceUserRoleExample example);

    FaceUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByExampleSelective(@Param("record") FaceUserRole record, @Param("example") FaceUserRoleExample example);

    int updateByExample(@Param("record") FaceUserRole record, @Param("example") FaceUserRoleExample example);

    int updateByPrimaryKeySelective(FaceUserRole record);

    int updateByPrimaryKey(FaceUserRole record);
}