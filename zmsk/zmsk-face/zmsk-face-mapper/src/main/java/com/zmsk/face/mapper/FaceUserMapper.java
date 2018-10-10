package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.pojo.FaceUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceUserMapper {
    int countByExample(FaceUserExample example);

    int deleteByExample(FaceUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(FaceUser record);

    int insertSelective(FaceUser record);

    List<FaceUser> selectByExample(FaceUserExample example);

    FaceUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") FaceUser record, @Param("example") FaceUserExample example);

    int updateByExample(@Param("record") FaceUser record, @Param("example") FaceUserExample example);

    int updateByPrimaryKeySelective(FaceUser record);

    int updateByPrimaryKey(FaceUser record);
}