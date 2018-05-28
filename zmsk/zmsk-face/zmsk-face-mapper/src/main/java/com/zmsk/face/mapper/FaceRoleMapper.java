package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceRole;
import com.zmsk.face.pojo.FaceRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceRoleMapper {
    int countByExample(FaceRoleExample example);

    int deleteByExample(FaceRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(FaceRole record);

    int insertSelective(FaceRole record);

    List<FaceRole> selectByExample(FaceRoleExample example);

    FaceRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") FaceRole record, @Param("example") FaceRoleExample example);

    int updateByExample(@Param("record") FaceRole record, @Param("example") FaceRoleExample example);

    int updateByPrimaryKeySelective(FaceRole record);

    int updateByPrimaryKey(FaceRole record);
}