package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceGroup;
import com.zmsk.face.pojo.FaceGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceGroupMapper {
    int countByExample(FaceGroupExample example);

    int deleteByExample(FaceGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceGroup record);

    int insertSelective(FaceGroup record);

    List<FaceGroup> selectByExample(FaceGroupExample example);

    FaceGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceGroup record, @Param("example") FaceGroupExample example);

    int updateByExample(@Param("record") FaceGroup record, @Param("example") FaceGroupExample example);

    int updateByPrimaryKeySelective(FaceGroup record);

    int updateByPrimaryKey(FaceGroup record);
}