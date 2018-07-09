package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceAuthenticationInfo;
import com.zmsk.face.pojo.FaceAuthenticationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceAuthenticationInfoMapper {
    int countByExample(FaceAuthenticationInfoExample example);

    int deleteByExample(FaceAuthenticationInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceAuthenticationInfo record);

    int insertSelective(FaceAuthenticationInfo record);

    List<FaceAuthenticationInfo> selectByExample(FaceAuthenticationInfoExample example);

    FaceAuthenticationInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceAuthenticationInfo record, @Param("example") FaceAuthenticationInfoExample example);

    int updateByExample(@Param("record") FaceAuthenticationInfo record, @Param("example") FaceAuthenticationInfoExample example);

    int updateByPrimaryKeySelective(FaceAuthenticationInfo record);

    int updateByPrimaryKey(FaceAuthenticationInfo record);
}