package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceFactory;
import com.zmsk.face.pojo.FaceFactoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceFactoryMapper {
    int countByExample(FaceFactoryExample example);

    int deleteByExample(FaceFactoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceFactory record);

    int insertSelective(FaceFactory record);

    List<FaceFactory> selectByExample(FaceFactoryExample example);

    FaceFactory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceFactory record, @Param("example") FaceFactoryExample example);

    int updateByExample(@Param("record") FaceFactory record, @Param("example") FaceFactoryExample example);

    int updateByPrimaryKeySelective(FaceFactory record);

    int updateByPrimaryKey(FaceFactory record);
}