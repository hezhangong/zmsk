package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceSerialNumber;
import com.zmsk.face.pojo.FaceSerialNumberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceSerialNumberMapper {
    int countByExample(FaceSerialNumberExample example);

    int deleteByExample(FaceSerialNumberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceSerialNumber record);

    int insertSelective(FaceSerialNumber record);

    List<FaceSerialNumber> selectByExample(FaceSerialNumberExample example);

    int updateByExampleSelective(@Param("record") FaceSerialNumber record, @Param("example") FaceSerialNumberExample example);

    int updateByExample(@Param("record") FaceSerialNumber record, @Param("example") FaceSerialNumberExample example);
    
    int insertSerialNumber(FaceSerialNumber record);
}