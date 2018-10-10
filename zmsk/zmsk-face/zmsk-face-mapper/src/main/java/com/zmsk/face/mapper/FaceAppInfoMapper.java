package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceAppInfo;
import com.zmsk.face.pojo.FaceAppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceAppInfoMapper {
    int countByExample(FaceAppInfoExample example);

    int deleteByExample(FaceAppInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceAppInfo record);

    int insertSelective(FaceAppInfo record);

    List<FaceAppInfo> selectByExample(FaceAppInfoExample example);

    FaceAppInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceAppInfo record, @Param("example") FaceAppInfoExample example);

    int updateByExample(@Param("record") FaceAppInfo record, @Param("example") FaceAppInfoExample example);

    int updateByPrimaryKeySelective(FaceAppInfo record);

    int updateByPrimaryKey(FaceAppInfo record);
}